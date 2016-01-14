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
@Table(name = "amet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Amet.findAll", query = "SELECT a FROM Amet a"),
    @NamedQuery(name = "Amet.findByAmetKood", query = "SELECT a FROM Amet a WHERE a.ametKood = :ametKood"),
    @NamedQuery(name = "Amet.findByNimetus", query = "SELECT a FROM Amet a WHERE a.nimetus = :nimetus"),
    @NamedQuery(name = "Amet.findByKirjeldus", query = "SELECT a FROM Amet a WHERE a.kirjeldus = :kirjeldus")})
public class Amet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "amet_kood")
    private Short ametKood;
    @Basic(optional = false)
    @Column(name = "nimetus")
    private String nimetus;
    @Column(name = "kirjeldus")
    private String kirjeldus;

    public Amet() {
    }

    public Amet(Short ametKood) {
        this.ametKood = ametKood;
    }

    public Amet(Short ametKood, String nimetus) {
        this.ametKood = ametKood;
        this.nimetus = nimetus;
    }

    public Short getAmetKood() {
        return ametKood;
    }

    public void setAmetKood(Short ametKood) {
        this.ametKood = ametKood;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ametKood != null ? ametKood.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Amet)) {
            return false;
        }
        Amet other = (Amet) object;
        if ((this.ametKood == null && other.ametKood != null) || (this.ametKood != null && !this.ametKood.equals(other.ametKood))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ee.andmebaasid.entity.Amet[ ametKood=" + ametKood + " ]";
    }
    
}
