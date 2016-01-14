/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.andmebaasid.auth;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author iljad
 */
@Service
public class SessionService{
    Logger log = LoggerFactory.logger(SessionService.class);
    
    private Map<String,Session> sessions = new HashMap<>();
    
    public void addSession(String token, Session session){
        sessions.put(token, session);
    }
    
    public Session findSession(String token){
        log.debug("Sessions size is = " + sessions.size() + " searching token " + token);
        return sessions.get(token);
    }
    
    public void deleteSession (String token){
        sessions.remove(token);
        log.debug("Trying to remove " + token);
        log.debug("Sessions size after deleting is = " + sessions.size());
    }
}
