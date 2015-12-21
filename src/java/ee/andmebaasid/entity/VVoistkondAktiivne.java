/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.andmebaasid.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author iljad
 */
@Entity
@Table(name = "v_voistkond_aktiivne")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VVoistkondAktiivne.findAll", query = "SELECT v FROM VVoistkondAktiivne v"),
    @NamedQuery(name = "VVoistkondAktiivne.findBySpordialaNimetus", query = "SELECT v FROM VVoistkondAktiivne v WHERE v.spordialaNimetus = :spordialaNimetus"),
    @NamedQuery(name = "VVoistkondAktiivne.findByRiikNimetus", query = "SELECT v FROM VVoistkondAktiivne v WHERE v.riikNimetus = :riikNimetus"),
    @NamedQuery(name = "VVoistkondAktiivne.findByNimetus", query = "SELECT v FROM VVoistkondAktiivne v WHERE v.nimetus = :nimetus"),
    @NamedQuery(name = "VVoistkondAktiivne.findByKirjeldus", query = "SELECT v FROM VVoistkondAktiivne v WHERE v.kirjeldus = :kirjeldus"),
    @NamedQuery(name = "VVoistkondAktiivne.findByEMail", query = "SELECT v FROM VVoistkondAktiivne v WHERE v.eMail = :eMail")})
public class VVoistkondAktiivne implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "spordiala_nimetus")
    private String spordialaNimetus;
    @Basic(optional = false)
    @Column(name = "riik_nimetus")
    private String riikNimetus;
    @Column(name = "nimetus")
    private String nimetus;
    @Column(name = "kirjeldus")
    private String kirjeldus;
    @Column(name = "e_mail")
    @Id
    private String eMail;

    public VVoistkondAktiivne() {
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

    public String getKirjeldus() {
        return kirjeldus;
    }

    public void setKirjeldus(String kirjeldus) {
        this.kirjeldus = kirjeldus;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }
    
}
