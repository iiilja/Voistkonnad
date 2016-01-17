/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.andmebaasid.controller;

import ee.andmebaasid.auth.Session;
import ee.andmebaasid.auth.SessionService;
import ee.andmebaasid.entity.TeamActive;
import ee.andmebaasid.entity.VTootaja;
import ee.andmebaasid.service.TeamsService;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author iljad
 */
@RequestMapping(value = "user")
@Controller()
public class UserController {
    Logger log = LoggerFactory.logger(UserController.class);
    
    @Autowired
    private TeamsService teamsService;
    
    
    @Autowired
    private SessionService sessionService;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public @ResponseBody VTootaja login(@RequestParam String login, @RequestParam String password,
            HttpServletResponse response,HttpSession session) throws IOException{
        boolean loginOk = teamsService.login(login, password);
        if (loginOk) {
            VTootaja tootaja = teamsService.getWorkerByLogin(login);
            String token = UUID.randomUUID().toString();
            tootaja.setToken(token);
            sessionService.addSession(token, new Session(tootaja));
            session.setAttribute("token", token);
            return tootaja;
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public @ResponseBody String check(HttpServletResponse response, HttpSession session) throws IOException{
        Session userSession = (Session) session.getAttribute("userSession");
        if (userSession != null) {
            VTootaja tootaja = userSession.getTootaja();
            return tootaja.getEesnimi() + " " + tootaja.getPerenimi();
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }
    

    
    @ModelAttribute(value = "voistkondsActive")
    private List<TeamActive> getVoistkondsActive(){
        log.debug("Called for active voistkonds");
        return teamsService.getAllActiveTeams();
    }

    @ExceptionHandler
    private void handleException(Exception e){
        log.debug(e.getMessage());
        e.printStackTrace();
    }
    
}
