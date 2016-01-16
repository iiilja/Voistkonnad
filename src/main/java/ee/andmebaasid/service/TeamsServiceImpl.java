package ee.andmebaasid.service;

import ee.andmebaasid.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.ResultSetOutput;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.ParameterMode;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
public class TeamsServiceImpl implements TeamsService {

    Logger log = LoggerFactory.logger(TeamsServiceImpl.class);

    public TeamsServiceImpl() {
        log.info("\nTeamsServiceImpl\n");
    }
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public List<TeamFull> getAllTeams() {
        Session session = sessionFactory.getCurrentSession();
        return Collections.checkedList(session.getNamedQuery("TeamFull.findAll").list(), TeamFull.class);
    }

    @Override
    public TeamFull findTeamById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (TeamFull) session.getNamedQuery("TeamFull.findByVoistkondId")
                .setParameter("voistkondId", id).uniqueResult();
    }
    
    @Override
    public List<TeamActive> getAllActiveTeams(){
        Session session = sessionFactory.getCurrentSession();
        return Collections.checkedList(session.getNamedQuery("TeamActive.findAll").list(), TeamActive.class);
    }

    @Override
    public List<Riik> getCountries() {
        Session session = sessionFactory.getCurrentSession();
        return Collections.checkedList(session.getNamedQuery("Riik.findAll").list(), Riik.class);
    }

    @Override
    public List<Riik> getCountriesForTeam(TeamFull voistkond) {
        List<Riik> riiks = getCountries();
        for (Riik riik : riiks) {
            if (riik.getNimetus().equals(voistkond.getCountry())) {
                riik.setSelected(true);
            }
        }
        return riiks;
    }
    

    @Override
    public List<TeamState> getTeamStates() {
        Session session = sessionFactory.getCurrentSession();
        return Collections.checkedList(session.getNamedQuery("TeamState.findAll").list(), TeamState.class);
    }

    @Override
    public List<TeamState> getTeamStatesForTeam(TeamFull voistkond) {
        List<TeamState> seisundiLiiks = getTeamStates();
        for (TeamState seisundiLiik : seisundiLiiks) {
            if (seisundiLiik.getStateName().equals(voistkond.getState())) {
                seisundiLiik.setSelected(true);
            }
        }
        return seisundiLiiks;
    }

    @Override
    public List<Sport> getSports() {
        Session session = sessionFactory.getCurrentSession();
        return Collections.checkedList(session.getNamedQuery("Sport.findAll").list(), Sport.class);
    }

    @Override
    public List<Sport> getSportsForTeam(TeamFull voistkond) {
        List<Sport> spordialas = getSports();
        for (Sport spordiala : spordialas) {
            if (spordiala.getSpotrName().equals(voistkond.getSport())) {
                spordiala.setSelected(true);
            }
        }
        return spordialas;
    }

    @Override
    public boolean updateTeam(int id, short seisundiLiik, String nimetus, short spordialaKood, String riikKood, int tootajaId, String email, String kirjeldus) {
        Session session = sessionFactory.getCurrentSession();
        ProcedureCall procedureCall = session.createStoredProcedureCall("muuda_voistkonna_andmeid");
        procedureCall.registerParameter(1, Integer.TYPE, ParameterMode.IN).bindValue(id);
        procedureCall.registerParameter(2, Short.TYPE, ParameterMode.IN).bindValue(seisundiLiik);
        procedureCall.registerParameter(3, String.class, ParameterMode.IN).bindValue(nimetus);
        procedureCall.registerParameter(4, Short.class, ParameterMode.IN).bindValue(spordialaKood);
        procedureCall.registerParameter(5, String.class, ParameterMode.IN).bindValue(riikKood);
        procedureCall.registerParameter(6, Integer.class, ParameterMode.IN).bindValue(tootajaId);
        procedureCall.registerParameter(7, String.class, ParameterMode.IN).bindValue(email);
        procedureCall.registerParameter(8, String.class, ParameterMode.IN).bindValue(kirjeldus);

        ResultSetOutput out = (ResultSetOutput) procedureCall
                .getOutputs().getCurrent();
        return (boolean) out.getSingleResult();
    }

    @Override
    public boolean updateTeamStatus(int teamId, short stateCode, int changerWorker) {
        Session session = sessionFactory.getCurrentSession();
        ProcedureCall procedureCall = session.createStoredProcedureCall("muuda_voistkonna_seisundi_liik");
        procedureCall.registerParameter(1, Integer.TYPE, ParameterMode.IN).bindValue(teamId);
        procedureCall.registerParameter(2, Short.TYPE, ParameterMode.IN).bindValue(stateCode);
        procedureCall.registerParameter(6, Integer.TYPE, ParameterMode.IN).bindValue(changerWorker);

        ResultSetOutput out = (ResultSetOutput) procedureCall
                .getOutputs().getCurrent();
        return (boolean) out.getSingleResult();
    }

    @Override
    public Integer createTeam(String nimetus, short spordialaKood, String riikKood, int tootajaId, String email, String kirjeldus) {
        Session session = sessionFactory.getCurrentSession();
        ProcedureCall procedureCall = session.createStoredProcedureCall("lisa_voistkond");
        procedureCall.registerParameter(1, String.class, ParameterMode.IN).bindValue(nimetus);
        procedureCall.registerParameter(2, Short.class, ParameterMode.IN).bindValue(spordialaKood);
        procedureCall.registerParameter(3, String.class, ParameterMode.IN).bindValue(riikKood);
        procedureCall.registerParameter(4, Integer.class, ParameterMode.IN).bindValue(tootajaId);
        procedureCall.registerParameter(5, String.class, ParameterMode.IN).bindValue(email);
        procedureCall.registerParameter(6, String.class, ParameterMode.IN).bindValue(kirjeldus);

        ResultSetOutput out = (ResultSetOutput) procedureCall
                .getOutputs().getCurrent();
        return (Integer) out.getSingleResult();
    }

    @Override
    public boolean login(String name, String password) {
        Session session = sessionFactory.getCurrentSession();
        ProcedureCall procedureCall = session.createStoredProcedureCall("login_mangude_haldur");
        procedureCall.registerParameter(1, String.class, ParameterMode.IN).bindValue(name);
        procedureCall.registerParameter(2, String.class, ParameterMode.IN).bindValue(password);

        ResultSetOutput out = (ResultSetOutput) procedureCall
                .getOutputs().getCurrent();
        return (boolean) out.getSingleResult();
    }

    @Override
    public VTootaja getWorkerByLogin(String login) {
        Session session = sessionFactory.getCurrentSession();
        return (VTootaja) session.getNamedQuery("VTootaja.findByKasutajanimi").setParameter("kasutajanimi", login).uniqueResult();
    }
    
}
