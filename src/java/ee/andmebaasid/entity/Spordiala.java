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
@Table(name = "spordiala")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Spordiala.findAll", query = "SELECT s FROM Spordiala s"),
    @NamedQuery(name = "Spordiala.findBySpordialaKood", query = "SELECT s FROM Spordiala s WHERE s.spordialaKood = :spordialaKood"),
    @NamedQuery(name = "Spordiala.findByNimetus", query = "SELECT s FROM Spordiala s WHERE s.nimetus = :nimetus"),
    @NamedQuery(name = "Spordiala.findByKirjeldus", query = "SELECT s FROM Spordiala s WHERE s.kirjeldus = :kirjeldus")})
public class Spordiala implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "spordiala_kood")
    private Short spordialaKood;
    @Basic(optional = false)
    @Column(name = "nimetus")
    private String nimetus;
    @Column(name = "kirjeldus")
    private String kirjeldus;
    
    @Transient
    private boolean selected;

    public Spordiala() {
    }

    public Spordiala(Short spordialaKood) {
        this.spordialaKood = spordialaKood;
    }

    public Spordiala(Short spordialaKood, String nimetus) {
        this.spordialaKood = spordialaKood;
        this.nimetus = nimetus;
    }

    public Short getSpordialaKood() {
        return spordialaKood;
    }

    public void setSpordialaKood(Short spordialaKood) {
        this.spordialaKood = spordialaKood;
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
    
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (spordialaKood != null ? spordialaKood.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Spordiala)) {
            return false;
        }
        Spordiala other = (Spordiala) object;
        if ((this.spordialaKood == null && other.spordialaKood != null) || (this.spordialaKood != null && !this.spordialaKood.equals(other.spordialaKood))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ee.idu0020.entity.Spordiala[ spordialaKood=" + spordialaKood + " ]";
    }
    
}
