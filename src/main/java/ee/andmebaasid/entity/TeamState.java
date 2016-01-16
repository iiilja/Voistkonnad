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
    @NamedQuery(name = "TeamState.findAll", query = "SELECT v FROM TeamState v"),
    @NamedQuery(name = "TeamState.findBystateCode", query = "SELECT v FROM TeamState v WHERE v.stateCode = :stateCode")})
public class TeamState implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "voistkonna_seisundi_liik_kood")
    private Short stateCode;
    @Basic(optional = false)
    @Column(name = "nimetus")
    private String stateName;
    @Column(name = "kirjeldus")
    private String description;
    
    @Transient
    private boolean selected;

    public TeamState() {
    }

    public TeamState(Short stateCode) {
        this.stateCode = stateCode;
    }

    public TeamState(Short stateCode, String stateName) {
        this.stateCode = stateCode;
        this.stateName = stateName;
    }

    public Short getStateCode() {
        return stateCode;
    }

    public void setStateCode(Short stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
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
        hash += (stateCode != null ? stateCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TeamState)) {
            return false;
        }
        TeamState other = (TeamState) object;
        if ((this.stateCode == null && other.stateCode != null) || (this.stateCode != null && !this.stateCode.equals(other.stateCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ee.idu0020.entity.TeamState[ stateCode=" + stateCode + " ]";
    }
    
}
