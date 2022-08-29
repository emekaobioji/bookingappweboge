/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personal.bookingappweboge.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author emeka
 */
@Entity
@Table(name = "classr")
@NamedQueries({
    @NamedQuery(name = "ClassrOGE.findAll", query = "SELECT c FROM ClassrOGE c"),
    @NamedQuery(name = "ClassrOGE.findById", query = "SELECT c FROM ClassrOGE c WHERE c.id = :id"),
    @NamedQuery(name = "ClassrOGE.findBySchool", query = "SELECT c FROM ClassrOGE c WHERE c.school = :school"),
    @NamedQuery(name = "ClassrOGE.findByLevel", query = "SELECT c FROM ClassrOGE c WHERE c.level = :level"),
    @NamedQuery(name = "ClassrOGE.findByArm", query = "SELECT c FROM ClassrOGE c WHERE c.arm = :arm")})
public class ClassrOGE implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 2)
    @Column(name = "school")
    private String school;
    @Column(name = "level")
    private Integer level;
    @Size(max = 1)
    @Column(name = "arm")
    private String arm;

    public ClassrOGE() {
    }

    public ClassrOGE(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getArm() {
        return arm;
    }

    public void setArm(String arm) {
        this.arm = arm;
    }
    
    public String getClassr(){
        return school+level+arm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClassrOGE)) {
            return false;
        }
        ClassrOGE other = (ClassrOGE) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "personal.bookingappweboge.entities.ClassrOGE[ id=" + id + " ]";
    }
    
}
