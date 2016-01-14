package ee.andmebaasid.controller;

import ee.andmebaasid.auth.Session;
import ee.andmebaasid.auth.SessionService;
import ee.andmebaasid.entity.VTootaja;
import ee.andmebaasid.entity.TeamFull;
import ee.andmebaasid.entity.TeamActive;
import ee.andmebaasid.service.TeamsService;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "teams")
@Controller()
public class TeamsController {
    Logger log = LoggerFactory.logger(TeamsController.class);


    public TeamsController() {
        log.debug("AA");
    }
    
    
    @Autowired(required=true)
    private TeamsService teamsService;
    
    @Autowired SessionService sessionService;
    
    
    @RequestMapping(value = "s", method = RequestMethod.GET)
    public ModelAndView getAll(@RequestParam(required = false) Integer id) {
        if (id != null) {
            log.debug("byId " + id);
            Map<String, Object> model = new HashMap<>();
            TeamFull voistkond = teamsService.findTeamById(id);
            model.put("voistkond", voistkond);
            model.put("spordialas", teamsService.getSportsForTeam(voistkond));
            model.put("riiks", teamsService.getCountriesForTeam(voistkond));
            model.put("seisundiLiiks", teamsService.getStatesForTeam(voistkond));
            return new ModelAndView("voistkond", model);//getWithID(id);
        }
        List<TeamFull> voistkonds = teamsService.getAllTeams();
        Map<String, Object> model = new HashMap<>();
        model.put("voistkonds", voistkonds);
        return new ModelAndView("voistkonds", model);
    }
    
    @RequestMapping(value = "s", method = RequestMethod.POST)
    public ModelAndView doSomeAction(
            @RequestParam String action,
            @RequestParam String nimetus,
            @RequestParam short spordialaKood,
            @RequestParam String riikKood,
            @RequestParam(required = false) Short seisundiLiik,
            @RequestParam String email,
            @RequestParam String kirjeldus,
            @RequestParam(required = false) Integer id,
            HttpServletRequest request) throws JSONException{
        
        if (action != null) {
            Session session = (Session) request.getAttribute("userSession");
            VTootaja tootaja = session.getTootaja();
            Map<String, Object> model = new HashMap<>();
            String message = "";
            boolean ok = true;
            switch (action){
                case "update":
                    try {
                        ok = teamsService.updateTeam(id, seisundiLiik, nimetus, spordialaKood, riikKood, tootaja.getTootajaId(), email, kirjeldus);
                    } catch ( Exception e){
                        message = e.getCause().getLocalizedMessage();
                        message = message.substring(0, message.indexOf("Where"));
                    }
                    TeamFull voistkond = teamsService.findTeamById(id);
                    model.put("voistkond", voistkond);
                    model.put("spordialas", teamsService.getSportsForTeam(voistkond));
                    model.put("riiks", teamsService.getCountriesForTeam(voistkond));
                    model.put("seisundiLiiks", teamsService.getStatesForTeam(voistkond));
                    model.put("error" , ok ? message : "not found");
                    return new ModelAndView("voistkond", model);//getWithID(id);
                case "create":   
                    try{
                        id = teamsService.createTeam(nimetus, spordialaKood, riikKood, tootaja.getTootajaId(), email, kirjeldus);
                    } catch (Exception e){
                        message = e.getCause().getLocalizedMessage();
                        message = message.substring(0, message.indexOf("Where"));
                        e.printStackTrace();
                    }
                    if (id != null) {
                        voistkond = teamsService.findTeamById(id);
                    } else {
                        voistkond = new TeamFull();
                    }
                    model.put("voistkond", voistkond);
                    model.put("spordialas", teamsService.getSportsForTeam(voistkond));
                    model.put("riiks", teamsService.getCountriesForTeam(voistkond));
                    model.put("seisundiLiiks", teamsService.getStatesForTeam(voistkond));
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
        model.put("voistkond", new TeamFull());
        model.put("spordialas", teamsService.getSports());
        model.put("riiks", teamsService.getCountries());
        model.put("seisundiLiiks", Collections.EMPTY_LIST);
        return new ModelAndView("voistkond_new", model);//getWithID(id);
    }


    @RequestMapping(value = "active")
    public @ResponseBody List<TeamActive> getAllActive(){
        return teamsService.getAllActiveTeams();
    }

    @RequestMapping(value = "all")
    public @ResponseBody List<TeamFull> getAllFull(){
        return teamsService.getAllTeams();
    }


    @ExceptionHandler(Exception.class)
    public void handleAllException(Exception ex) {
        ex.printStackTrace();
    }
}