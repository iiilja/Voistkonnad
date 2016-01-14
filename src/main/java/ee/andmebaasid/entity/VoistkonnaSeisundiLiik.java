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
@Table(name = "voistkonna_seisundi_liik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VoistkonnaSeisundiLiik.findAll", query = "SELECT v FROM VoistkonnaSeisundiLiik v"),
    @NamedQuery(name = "VoistkonnaSeisundiLiik.findByVoistkonnaSeisundiLiikKood", query = "SELECT v FROM VoistkonnaSeisundiLiik v WHERE v.voistkonnaSeisundiLiikKood = :voistkonnaSeisundiLiikKood"),
    @NamedQuery(name = "VoistkonnaSeisundiLiik.findByNimetus", query = "SELECT v FROM VoistkonnaSeisundiLiik v WHERE v.nimetus = :nimetus"),
    @NamedQuery(name = "VoistkonnaSeisundiLiik.findByKirjeldus", query = "SELECT v FROM VoistkonnaSeisundiLiik v WHERE v.kirjeldus = :kirjeldus")})
public class VoistkonnaSeisundiLiik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "voistkonna_seisundi_liik_kood")
    private Short voistkonnaSeisundiLiikKood;
    @Basic(optional = false)
    @Column(name = "nimetus")
    private String nimetus;
    @Column(name = "kirjeldus")
    private String kirjeldus;
    
    @Transient
    private boolean selected;

    public VoistkonnaSeisundiLiik() {
    }

    public VoistkonnaSeisundiLiik(Short voistkonnaSeisundiLiikKood) {
        this.voistkonnaSeisundiLiikKood = voistkonnaSeisundiLiikKood;
    }

    public VoistkonnaSeisundiLiik(Short voistkonnaSeisundiLiikKood, String nimetus) {
        this.voistkonnaSeisundiLiikKood = voistkonnaSeisundiLiikKood;
        this.nimetus = nimetus;
    }

    public Short getVoistkonnaSeisundiLiikKood() {
        return voistkonnaSeisundiLiikKood;
    }

    public void setVoistkonnaSeisundiLiikKood(Short voistkonnaSeisundiLiikKood) {
        this.voistkonnaSeisundiLiikKood = voistkonnaSeisundiLiikKood;
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
        hash += (voistkonnaSeisundiLiikKood != null ? voistkonnaSeisundiLiikKood.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VoistkonnaSeisundiLiik)) {
            return false;
        }
        VoistkonnaSeisundiLiik other = (VoistkonnaSeisundiLiik) object;
        if ((this.voistkonnaSeisundiLiikKood == null && other.voistkonnaSeisundiLiikKood != null) || (this.voistkonnaSeisundiLiikKood != null && !this.voistkonnaSeisundiLiikKood.equals(other.voistkonnaSeisundiLiikKood))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ee.idu0020.entity.VoistkonnaSeisundiLiik[ voistkonnaSeisundiLiikKood=" + voistkonnaSeisundiLiikKood + " ]";
    }
    
}
