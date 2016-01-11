/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.andmebaasid.auth;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author iljad
 */
@Service
public class SessionService{
    
    private Map<String,Session> sessions = new HashMap<>();
    
    public void addSession(String token, Session session){
        sessions.put(token, session);
    }
    
    public Session findSession(String token){
        System.out.println("Sessions size is = " + sessions.size() + " searching token " + token);
        return sessions.get(token);
    }
    
    public void deleteSession (String token){
        sessions.remove(token);
        System.out.println("Trying to remove " + token);
        System.out.println("Sessions size after deleting is = " + sessions.size());
    }
}
