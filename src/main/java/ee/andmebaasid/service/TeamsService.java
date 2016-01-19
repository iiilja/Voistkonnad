/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.andmebaasid.service;

import ee.andmebaasid.entity.*;
import ee.andmebaasid.entity.TeamFull;

import java.util.List;

/**
 *
 * @author iljad
 */
public interface TeamsService {
    public List<TeamFull> getAllTeams();
    public TeamFull findTeamById(int id);
   
    public List<TeamActive> getAllActiveTeams();
    
    public List<Country> getCountries();
    public List<Country> getCountriesForTeam(TeamFull teamFull);
    
    public List<TeamState> getTeamStates();
    public List<TeamState> getTeamStatesForTeam(TeamFull teamFull);
    
    public List<Sport> getSports();
    public List<Sport> getSportsForTeam(TeamFull teamFull);

    public boolean updateTeam(int id, short stateCode, String name, short sportCode, String countryCode, int changerWorker, String email, String description);
    public boolean updateTeamStatus(int teamId, short stateCode, int changerWorker);
    public Integer createTeam(String name, short sportCode, String countryCode, int changerWorker, String email, String description);
    
    public boolean login(String name, String password);
    public VTootaja getWorkerByLogin(String login);

    public List<VTootaja> getWorkers();
}
