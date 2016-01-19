package ee.andmebaasid.controller;

import ee.andmebaasid.auth.Session;
import ee.andmebaasid.auth.SessionService;
import ee.andmebaasid.dto.TeamDTO;
import ee.andmebaasid.entity.*;
import ee.andmebaasid.service.TeamsService;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RequestMapping(value = "teams")
@Controller
public class TeamsController {
    Logger log = LoggerFactory.logger(TeamsController.class);


    public TeamsController() {
        log.debug("AA");
    }
    
    
    @Autowired(required=true)
    private TeamsService teamsService;
    
    @Autowired SessionService sessionService;


    @RequestMapping(value = "active")
    public @ResponseBody List<TeamActive> getAllActive(){
        return teamsService.getAllActiveTeams();
    }

    @RequestMapping(value = "all")
    public @ResponseBody List<TeamFull> getAllFull(){
        return teamsService.getAllTeams();
    }

    @RequestMapping(value = "states")
    public @ResponseBody List<TeamState> getAllStates(){
        return teamsService.getTeamStates();
    }
    @RequestMapping(value = "sports")
    public @ResponseBody List<Sport> getAllSports(){
        return teamsService.getSports();
    }
    @RequestMapping(value = "countries")
    public @ResponseBody List<Country> getAllSCountries(){
        return teamsService.getCountries();
    }

    @RequestMapping(value = "{teamId}/state" ,method = RequestMethod.POST)
    public @ResponseBody TeamFull getAllStates(
            @PathVariable Integer teamId, @RequestParam short teamStateCode,
            HttpSession httpSession){
        Session session =  sessionService.findSession((String) httpSession.getAttribute("token"));
        VTootaja tootaja = session.getTootaja();
        boolean resultOK = teamsService.updateTeamStatus(teamId, teamStateCode, tootaja.getTootajaId());
        if (resultOK){
            return teamsService.findTeamById(teamId);
        }
        return null;
    }

    @RequestMapping(value = "{teamId}" ,method = RequestMethod.GET)
    public @ResponseBody TeamFull getTeamById(
            @PathVariable Integer teamId){
        return teamsService.findTeamById(teamId);
    }

    @RequestMapping(value = "{teamId}" ,method = RequestMethod.POST)
    public @ResponseBody TeamFull updateTeam(
            @PathVariable Integer teamId, @RequestBody TeamDTO team,
            HttpSession httpSession){
        Session session =  sessionService.findSession((String) httpSession.getAttribute("token"));
        VTootaja tootaja = session.getTootaja();
        short notActive = 2;
        log.info("Updating team " + team);
        boolean updateOk = teamsService.updateTeam(team.getTeamId(), notActive, team.getName(), team.getSportCode(),
                team.getCountryCode(), tootaja.getTootajaId(), team.getEmail(), team.getDescription());
        if (updateOk){
            return teamsService.findTeamById(team.getTeamId());
        }
        return null;
    }

    @RequestMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Integer createTeam(
            @RequestBody TeamDTO team,
            HttpSession httpSession){
        log.info(team);
        Session session =  sessionService.findSession((String) httpSession.getAttribute("token"));
        VTootaja tootaja = session.getTootaja();
        return teamsService.createTeam(team.getName(), team.getSportCode(), team.getCountryCode(),
                tootaja.getTootajaId(), team.getEmail(), team.getDescription());
    }


    @ExceptionHandler(Exception.class)
    public void handleAllException(HttpServletResponse response, Exception ex) throws IOException {
        response.sendError(HttpServletResponse.SC_CONFLICT, ex.getMessage());
        ex.printStackTrace();
    }
}
