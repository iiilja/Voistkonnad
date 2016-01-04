package ee.andmebaasid.service;

import ee.andmebaasid.entity.Riik;
import ee.andmebaasid.entity.Spordiala;
import ee.andmebaasid.entity.VTootaja;
import ee.andmebaasid.entity.VVoistkond;
import ee.andmebaasid.entity.VoistkonnaSeisundiLiik;
import java.util.List;
import javax.persistence.ParameterMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.ResultSetOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class VoistkonnadServiceImpl implements VoistkonnadService {

    public VoistkonnadServiceImpl() {
        System.out.println("\nVoistkonnadServiceImpl\n");
    }
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public List<VVoistkond> getAllVoistkonds() {
        Session session = sessionFactory.getCurrentSession();
        return session.getNamedQuery("VVoistkond.findAll").list();
    }

    @Override
    public VVoistkond findVoistkondById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (VVoistkond) session.getNamedQuery("VVoistkond.findByVoistkondId")
                .setParameter("voistkondId", id).uniqueResult();
    }

    @Override
    public List<Riik> getRiiks() {
        Session session = sessionFactory.getCurrentSession();
        return session.getNamedQuery("Riik.findAll").list();
    }

    @Override
    public List<Riik> getRiiksByVoistkond(VVoistkond voistkond) {
        List<Riik> riiks = getRiiks();
        for (Riik riik : riiks) {
            if (riik.getNimetus().equals(voistkond.getRiikNimetus())) {
                riik.setSelected(true);
            }
        }
        return riiks;
    }
    

    @Override
    public List<VoistkonnaSeisundiLiik> getSeisundiLiiks() {
        Session session = sessionFactory.getCurrentSession();
        return session.getNamedQuery("VoistkonnaSeisundiLiik.findAll").list();
    }

    @Override
    public List<VoistkonnaSeisundiLiik> getSeisundiLiiksByVoistkond(VVoistkond voistkond) {
        List<VoistkonnaSeisundiLiik> seisundiLiiks = getSeisundiLiiks();
        for (VoistkonnaSeisundiLiik seisundiLiik : seisundiLiiks) {
            if (seisundiLiik.getNimetus().equals(voistkond.getVoistkonnaSeisundiLiik())) {
                seisundiLiik.setSelected(true);
            }
        }
        return seisundiLiiks;
    }

    @Override
    public List<Spordiala> getSpordialas() {
        Session session = sessionFactory.getCurrentSession();
        return session.getNamedQuery("Spordiala.findAll").list();
    }

    @Override
    public List<Spordiala> getSpordialasByVoistkond(VVoistkond voistkond) {
        List<Spordiala> spordialas = getSpordialas();
        for (Spordiala spordiala : spordialas) {
            if (spordiala.getNimetus().equals(voistkond.getSpordialaNimetus())) {
                spordiala.setSelected(true);
            }
        }
        return spordialas;
    }

    @Override
    public boolean updateVoistkond(int id, short seisundiLiik, String nimetus, short spordialaKood, String riikKood, int tootajaId, String email, String kirjeldus) {
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
    public Integer createVoistkond(String nimetus, short spordialaKood, String riikKood, int tootajaId, String email, String kirjeldus) {
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
    public VTootaja getTootajaByNickName(String login) {
        Session session = sessionFactory.getCurrentSession();
        return (VTootaja) session.getNamedQuery("VTootaja.findByKasutajanimi").setParameter("kasutajanimi", login);
    }
    
}
