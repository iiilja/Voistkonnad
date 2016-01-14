/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.andmebaasid.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author iljad
 */
@Entity
@Table(name = "v_voistkond_aktiivne")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TeamActive.findAll", query = "SELECT v FROM TeamActive v")})
public class TeamActive implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "spordiala_nimetus")
    private String sport;
    @Basic(optional = false)
    @Column(name = "riik_nimetus")
    private String country;
    @Column(name = "nimetus")
    private String name;
    @Column(name = "kirjeldus")
    private String description;
    @Column(name = "e_mail")
    @Id
    private String eMail;

    public TeamActive() {
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

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }
    
}
