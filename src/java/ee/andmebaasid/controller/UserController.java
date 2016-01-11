/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.andmebaasid.controller;

import ee.andmebaasid.auth.Session;
import ee.andmebaasid.auth.SessionService;
import ee.andmebaasid.entity.VTootaja;
import ee.andmebaasid.entity.VVoistkondAktiivne;
import ee.andmebaasid.service.VoistkonnadService;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author iljad
 */
@RequestMapping(value = "user")
@Controller()
public class UserController {
    Logger log = LoggerFactory.logger(UserController.class);
    
    @Autowired
    private VoistkonnadService voistkonnadService;
    
    
    @Autowired
    private SessionService sessionService;
    
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String check(@RequestParam String login, @RequestParam String password,
            HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException{
        boolean loginOk = voistkonnadService.login(login, password);
        if (loginOk) {
            VTootaja tootaja = voistkonnadService.getTootajaByNickName(login);
            String token = UUID.randomUUID().toString();
            tootaja.setToken(token);
            sessionService.addSession(token, new Session(tootaja));
            session.setAttribute("token", token);
            response.sendRedirect(request.getContextPath() + "/voistkonnad/s");
            return "voistkonds";
        } else {
            return "login";
        }
    }
    
    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }
    
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("token");
        sessionService.deleteSession(token);
        return "login";
    }
    
    @ModelAttribute(value = "voistkondsActive")
    private List<VVoistkondAktiivne> getVoistkondsActive(){
        log.debug("Called for active voistkonds");
        return voistkonnadService.getAllActiveVoistkonds();
    }
    
    @ExceptionHandler
    private void handleException(Exception e){
        System.err.println(e.getMessage());
        e.printStackTrace();
    }
    
}
