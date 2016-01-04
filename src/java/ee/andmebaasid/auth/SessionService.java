/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.andmebaasid.auth;

import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author iljad
 */
@Service
public class SessionService{
    
    private Map<String,Session> sessions;
    
    public void addSession(String token, Session session){
        sessions.put(token, session);
    }
    
    public Session findSession(String token){
        Session s = sessions.get(token);
        return s;
    }
}
