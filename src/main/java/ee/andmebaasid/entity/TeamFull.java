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
    @NamedQuery(name = "TeamFull.findAll", query = "SELECT v FROM TeamFull v"),
    @NamedQuery(name = "TeamFull.findByTeamId", query = "SELECT v FROM TeamFull v WHERE v.teamId = :teamId")})
public class TeamFull implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "voistkond_id")
    @Id
    private Integer teamId;
    @Basic(optional = false)
    @Column(name = "spordiala_nimetus")
    private String sport;
    @Basic(optional = false)
    @Column(name = "riik_nimetus")
    private String country;
    @Column(name = "nimetus")
    private String name;
    @Basic(optional = false)
    @Column(name = "voistkonna_seisundi_liik")
    private String state;
    @Column(name = "e_mail")
    private String eMail;
    @Column(name = "kirjeldus")
    private String description;
    @Column(name = "muutja")
    private String changer;
    @Column(name = "reg_aeg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    public TeamFull() {
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEMail() {
        return eMail;
    }
    
    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getChanger() {
        return changer;
    }

    public void setChanger(String changer) {
        this.changer = changer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    
}
