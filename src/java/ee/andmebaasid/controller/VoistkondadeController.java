package ee.andmebaasid.controller;

import ee.andmebaasid.auth.Session;
import ee.andmebaasid.auth.SessionService;
import ee.andmebaasid.entity.VTootaja;
import ee.andmebaasid.entity.VVoistkond;
import ee.andmebaasid.service.VoistkonnadService;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Dmitri
 */
@RequestMapping(value = "voistkonnad")
@Controller()
public class VoistkondadeController {

    public VoistkondadeController() {
        System.out.println("\n\nAA\n\n");
    }
    
    
    @Autowired(required=true)
    private VoistkonnadService voistkonnadService;
    
    @Autowired SessionService sessionService;
    
    
    @RequestMapping(value = "/s", method = RequestMethod.GET)
    public ModelAndView getAll(@RequestParam(required = false) Integer id) {
        System.out.println("getAll");
        if (id != null) {
            System.out.println("byId " + id);
            Map<String, Object> model = new HashMap<>();
            VVoistkond voistkond = voistkonnadService.findVoistkondById(id);
            model.put("voistkond", voistkond);
            model.put("spordialas", voistkonnadService.getSpordialasByVoistkond(voistkond));
            model.put("riiks", voistkonnadService.getRiiksByVoistkond(voistkond));
            model.put("seisundiLiiks", voistkonnadService.getSeisundiLiiksByVoistkond(voistkond));
            return new ModelAndView("voistkond", model);//getWithID(id);
        }
        List<VVoistkond> voistkonds = voistkonnadService.getAllVoistkonds();
        Map<String, Object> model = new HashMap<>();
        model.put("voistkonds", voistkonds);
        return new ModelAndView("voistkonds", model);
    }
    
    @RequestMapping(value = "/s", method = RequestMethod.POST)
    public ModelAndView doSomeAction(
            @RequestParam String action,
            @RequestParam String nimetus,
            @RequestParam short spordialaKood,
            @RequestParam String riikKood,
            @RequestParam(required = false) Short seisundiLiik,
            @RequestParam String email,
            @RequestParam String kirjeldus,
            @RequestParam(required = false) Integer id,
            HttpServletRequest request,
            HttpServletResponse response) throws JSONException{
        
        if (action != null) {
            Map<String, Object> model = new HashMap<>();
            String message = "";
            boolean ok = true;
            switch (action){
                case "update":
                    try {
                        ok = voistkonnadService.updateVoistkond(id,seisundiLiik, nimetus, spordialaKood,riikKood, 1, email, kirjeldus);
                    } catch ( Exception e){
                        message = e.getCause().getLocalizedMessage();
                        message = message.substring(0, message.indexOf("Where"));
                    }
                    VVoistkond voistkond = voistkonnadService.findVoistkondById(id);
                    model.put("voistkond", voistkond);
                    model.put("spordialas", voistkonnadService.getSpordialasByVoistkond(voistkond));
                    model.put("riiks", voistkonnadService.getRiiksByVoistkond(voistkond));
                    model.put("seisundiLiiks", voistkonnadService.getSeisundiLiiksByVoistkond(voistkond));
                    model.put("error" , ok ? message : "not found");
                    return new ModelAndView("voistkond", model);//getWithID(id);
                case "create":   
                    try{
                        id = voistkonnadService.createVoistkond(nimetus, spordialaKood, riikKood, 1, email, kirjeldus);
                    } catch (Exception e){
                        message = e.getCause().getLocalizedMessage();
                        message = message.substring(0, message.indexOf("Where"));
                        e.printStackTrace();
                    }
                    if (id != null) {
                        voistkond = voistkonnadService.findVoistkondById(id);
                    } else {
                        voistkond = new VVoistkond();
                    }
                    model.put("voistkond", voistkond);
                    model.put("spordialas", voistkonnadService.getSpordialasByVoistkond(voistkond));
                    model.put("riiks", voistkonnadService.getRiiksByVoistkond(voistkond));
                    model.put("seisundiLiiks", voistkonnadService.getSeisundiLiiksByVoistkond(voistkond));
                    model.put("error" , id!=null ? message : "not found");
                    return new ModelAndView("voistkond", model);//getWithID(id);
            }
            return null;
        }
        return null;
    }
    
    @RequestMapping(value="new")
    public ModelAndView createNewVoistkond(){
        Map<String, Object> model = new HashMap<>();
        model.put("voistkond", new VVoistkond());
        model.put("spordialas", voistkonnadService.getSpordialas());
        model.put("riiks", voistkonnadService.getRiiks());
        model.put("seisundiLiiks", Collections.EMPTY_LIST);
        return new ModelAndView("voistkond_new", model);//getWithID(id);
    }

    
    @ExceptionHandler(Exception.class)
    public void handleAllException(Exception ex) {
        ex.printStackTrace();
    }
}
