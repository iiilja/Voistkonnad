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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author iljad
 */
@Entity
@Table(name = "v_tootaja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VTootaja.findAll", query = "SELECT v FROM VTootaja v"),
    @NamedQuery(name = "VTootaja.findByTootajaId", query = "SELECT v FROM VTootaja v WHERE v.tootajaId = :tootajaId"),
    @NamedQuery(name = "VTootaja.findByEMail", query = "SELECT v FROM VTootaja v WHERE v.eMail = :eMail"),
    @NamedQuery(name = "VTootaja.findByKasutajanimi", query = "SELECT v FROM VTootaja v WHERE v.kasutajanimi = :kasutajanimi"),
    @NamedQuery(name = "VTootaja.findBySeisundiLiik", query = "SELECT v FROM VTootaja v WHERE v.seisundiLiik = :seisundiLiik"),
    @NamedQuery(name = "VTootaja.findByAmet", query = "SELECT v FROM VTootaja v WHERE v.amet = :amet")})
public class VTootaja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "tootaja_id")
    @Id
    private Integer tootajaId;
    @Column(name = "e_mail")
    private String eMail;
    @Column(name = "kasutajanimi")
    private String kasutajanimi;
    @Column(name = "eesnimi")
    private String firstName;
    @Column(name = "perenimi")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "seisundi_liik")
    private String seisundiLiik;
    @Basic(optional = false)
    @Column(name = "amet")
    private String amet;
    
    @Transient
    private String token;

    public VTootaja() {
    }

    public Integer getTootajaId() {
        return tootajaId;
    }

    public void setTootajaId(Integer tootajaId) {
        this.tootajaId = tootajaId;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSeisundiLiik() {
        return seisundiLiik;
    }

    public void setSeisundiLiik(String seisundiLiik) {
        this.seisundiLiik = seisundiLiik;
    }

    public String getAmet() {
        return amet;
    }

    public void setAmet(String amet) {
        this.amet = amet;
    }

    public String getKasutajanimi() {
        return kasutajanimi;
    }

    public void setKasutajanimi(String kasutajanimi) {
        this.kasutajanimi = kasutajanimi;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
