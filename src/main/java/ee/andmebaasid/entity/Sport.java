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
    @NamedQuery(name = "Sport.findAll", query = "SELECT s FROM Sport s")})
public class Sport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "spordiala_kood")
    private Short sportCode;
    @Basic(optional = false)
    @Column(name = "nimetus")
    private String spotrName;
    @Column(name = "kirjeldus")
    private String description;
    
    @Transient
    private boolean selected;

    public Sport() {
    }

    public Sport(Short sportCode) {
        this.sportCode = sportCode;
    }

    public Sport(Short sportCode, String spotrName) {
        this.sportCode = sportCode;
        this.spotrName = spotrName;
    }

    public Short getSportCode() {
        return sportCode;
    }

    public void setSportCode(Short sportCode) {
        this.sportCode = sportCode;
    }

    public String getSpotrName() {
        return spotrName;
    }

    public void setSpotrName(String spotrName) {
        this.spotrName = spotrName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash += (sportCode != null ? sportCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sport)) {
            return false;
        }
        Sport other = (Sport) object;
        if ((this.sportCode == null && other.sportCode != null) || (this.sportCode != null && !this.sportCode.equals(other.sportCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ee.idu0020.entity.Sport[ sportCode=" + sportCode + " ]";
    }
    
}
