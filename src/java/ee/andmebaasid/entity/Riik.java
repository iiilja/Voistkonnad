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
@Table(name = "riik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Riik.findAll", query = "SELECT r FROM Riik r"),
    @NamedQuery(name = "Riik.findByRiikKood", query = "SELECT r FROM Riik r WHERE r.riikKood = :riikKood"),
    @NamedQuery(name = "Riik.findByNimetus", query = "SELECT r FROM Riik r WHERE r.nimetus = :nimetus"),
    @NamedQuery(name = "Riik.findByKirjeldus", query = "SELECT r FROM Riik r WHERE r.kirjeldus = :kirjeldus")})
public class Riik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "riik_kood")
    private String riikKood;
    @Basic(optional = false)
    @Column(name = "nimetus")
    private String nimetus;
    @Column(name = "kirjeldus")
    private String kirjeldus;
    
    @Transient
    private boolean selected;

    public Riik() {
    }

    public Riik(String riikKood) {
        this.riikKood = riikKood;
    }

    public Riik(String riikKood, String nimetus) {
        this.riikKood = riikKood;
        this.nimetus = nimetus;
    }

    public String getRiikKood() {
        return riikKood;
    }

    public void setRiikKood(String riikKood) {
        this.riikKood = riikKood;
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
        hash += (riikKood != null ? riikKood.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Riik)) {
            return false;
        }
        Riik other = (Riik) object;
        if ((this.riikKood == null && other.riikKood != null) || (this.riikKood != null && !this.riikKood.equals(other.riikKood))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ee.idu0020.entity.Riik[ riikKood=" + riikKood + " ]";
    }
    
}
