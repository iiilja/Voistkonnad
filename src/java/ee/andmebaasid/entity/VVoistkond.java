/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.andmebaasid.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author iljad
 */
@Entity
@Table(name = "v_voistkond")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VVoistkond.findAll", query = "SELECT v FROM VVoistkond v"),
    @NamedQuery(name = "VVoistkond.findByVoistkondId", query = "SELECT v FROM VVoistkond v WHERE v.voistkondId = :voistkondId"),
    @NamedQuery(name = "VVoistkond.findBySpordialaNimetus", query = "SELECT v FROM VVoistkond v WHERE v.spordialaNimetus = :spordialaNimetus"),
    @NamedQuery(name = "VVoistkond.findByRiikNimetus", query = "SELECT v FROM VVoistkond v WHERE v.riikNimetus = :riikNimetus"),
    @NamedQuery(name = "VVoistkond.findByNimetus", query = "SELECT v FROM VVoistkond v WHERE v.nimetus = :nimetus"),
    @NamedQuery(name = "VVoistkond.findByVoistkonnaSeisundiLiik", query = "SELECT v FROM VVoistkond v WHERE v.voistkonnaSeisundiLiik = :voistkonnaSeisundiLiik"),
    @NamedQuery(name = "VVoistkond.findByEMail", query = "SELECT v FROM VVoistkond v WHERE v.eMail = :eMail"),
    @NamedQuery(name = "VVoistkond.findByKirjeldus", query = "SELECT v FROM VVoistkond v WHERE v.kirjeldus = :kirjeldus"),
    @NamedQuery(name = "VVoistkond.findByRegAeg", query = "SELECT v FROM VVoistkond v WHERE v.regAeg = :regAeg")})
public class VVoistkond implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "voistkond_id")
    @Id
    private Integer voistkondId;
    @Basic(optional = false)
    @Column(name = "spordiala_nimetus")
    private String spordialaNimetus;
    @Basic(optional = false)
    @Column(name = "riik_nimetus")
    private String riikNimetus;
    @Column(name = "nimetus")
    private String nimetus;
    @Basic(optional = false)
    @Column(name = "voistkonna_seisundi_liik")
    private String voistkonnaSeisundiLiik;
    @Column(name = "e_mail")
    private String eMail;
    @Column(name = "kirjeldus")
    private String kirjeldus;
    @Column(name = "muutja")
    private String muutja;
    @Column(name = "reg_aeg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date regAeg;

    public VVoistkond() {
    }

    public Integer getVoistkondId() {
        return voistkondId;
    }

    public void setVoistkondId(Integer voistkondId) {
        this.voistkondId = voistkondId;
    }

    public String getSpordialaNimetus() {
        return spordialaNimetus;
    }

    public void setSpordialaNimetus(String spordialaNimetus) {
        this.spordialaNimetus = spordialaNimetus;
    }

    public String getRiikNimetus() {
        return riikNimetus;
    }

    public void setRiikNimetus(String riikNimetus) {
        this.riikNimetus = riikNimetus;
    }

    public String getNimetus() {
        return nimetus;
    }

    public void setNimetus(String nimetus) {
        this.nimetus = nimetus;
    }

    public String getVoistkonnaSeisundiLiik() {
        return voistkonnaSeisundiLiik;
    }

    public void setVoistkonnaSeisundiLiik(String voistkonnaSeisundiLiik) {
        this.voistkonnaSeisundiLiik = voistkonnaSeisundiLiik;
    }

    public String getEMail() {
        return eMail;
    }
    
    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getMuutja() {
        return muutja;
    }

    public void setMuutja(String muutja) {
        this.muutja = muutja;
    }

    public String getKirjeldus() {
        return kirjeldus;
    }

    public void setKirjeldus(String kirjeldus) {
        this.kirjeldus = kirjeldus;
    }

    public Date getRegAeg() {
        return regAeg;
    }

    public void setRegAeg(Date regAeg) {
        this.regAeg = regAeg;
    }
    
}
