/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.andmebaasid.controller;

import ee.andmebaasid.auth.Session;
import ee.andmebaasid.auth.SessionService;
import ee.andmebaasid.entity.VTootaja;
import ee.andmebaasid.service.VoistkonnadService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    
    @Autowired
    private VoistkonnadService voistkonnadService;
    
    @Autowired
    private VoistkondadeController voistkondadeController;
    
    @Autowired
    private SessionService sessionService;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam String login, @RequestParam String password){
        boolean loginOk = voistkonnadService.login(login, password);
        if (loginOk) {
            VTootaja tootaja = voistkonnadService.getTootajaByNickName(login);
            String token = UUID.randomUUID().toString();
            tootaja.setToken(token);
            sessionService.addSession(token, new Session(tootaja));
            return voistkondadeController.getAll(null);
        } else {
            return null;
        }
    }
    
}
