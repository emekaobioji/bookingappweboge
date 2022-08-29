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
@Table(name = "offence", catalog = "bookings", schema = "")
@NamedQueries({
    @NamedQuery(name = "OffenceOGE.findAll", query = "SELECT o FROM OffenceOGE o"),
    @NamedQuery(name = "OffenceOGE.findById", query = "SELECT o FROM OffenceOGE o WHERE o.id = :id"),
    @NamedQuery(name = "OffenceOGE.findByDescription", query = "SELECT o FROM OffenceOGE o WHERE o.description = :description")})
public class OffenceOGE implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer id;
    @Size(max = 45)
    @Column(length = 45)
    private String description;

    public OffenceOGE() {
    }

    public OffenceOGE(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof OffenceOGE)) {
            return false;
        }
        OffenceOGE other = (OffenceOGE) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "personal.bookingappweboge.entities.OffenceOGE[ id=" + id + " ]";
    }
    
}
