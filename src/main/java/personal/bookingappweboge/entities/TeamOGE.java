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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author emeka
 */
@Entity
@Table(name = "team", catalog = "bookings", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name"})})

@NamedQueries({
    @NamedQuery(name = "TeamOGE.findAll", query = "SELECT t FROM TeamOGE t"),
    @NamedQuery(name = "TeamOGE.findById", query = "SELECT t FROM TeamOGE t WHERE t.id = :id"),
    @NamedQuery(name = "TeamOGE.findByName", query = "SELECT t FROM TeamOGE t WHERE t.name = :name")})
public class TeamOGE implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer id;
    @Size(max = 45)
    @Column(length = 45)
    private String name;

    public TeamOGE() {
    }

    public TeamOGE(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof TeamOGE)) {
            return false;
        }
        TeamOGE other = (TeamOGE) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "personal.bookingappweboge.entities.TeamOGE[ id=" + id + " ]";
    }
    
}
