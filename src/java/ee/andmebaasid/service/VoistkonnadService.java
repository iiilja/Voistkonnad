/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.andmebaasid.service;

import ee.andmebaasid.entity.Riik;
import ee.andmebaasid.entity.Spordiala;
import ee.andmebaasid.entity.VTootaja;
import ee.andmebaasid.entity.VVoistkond;
import ee.andmebaasid.entity.VoistkonnaSeisundiLiik;
import java.util.List;

/**
 *
 * @author iljad
 */
public interface VoistkonnadService {
    public List<VVoistkond> getAllVoistkonds();
    public VVoistkond findVoistkondById(int id);
    
    public List<Riik> getRiiks();
    public List<Riik> getRiiksByVoistkond(VVoistkond voistkond);
    
    public List<VoistkonnaSeisundiLiik> getSeisundiLiiks();
    public List<VoistkonnaSeisundiLiik> getSeisundiLiiksByVoistkond(VVoistkond voistkond);
    
    public List<Spordiala> getSpordialas();
    public List<Spordiala> getSpordialasByVoistkond(VVoistkond voistkond);

    public boolean updateVoistkond(int id, short seisundiLiik, String nimetus, short spordialaKood, String riikKood, int tootajaId, String email, String kirjeldus);
    public Integer createVoistkond(String nimetus, short spordialaKood, String riikKood, int i, String email, String kirjeldus);
    
    public boolean login(String name, String password);
    public VTootaja getTootajaByNickName(String login);
    
}
