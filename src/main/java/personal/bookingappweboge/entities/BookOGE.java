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

/**
 *
 * @author emeka
 */
@Entity
@Table(name = "book", catalog = "bookings", schema = "")
@NamedQueries({
    @NamedQuery(name = "BookOGE.findAll", query = "SELECT b FROM BookOGE b"),
    @NamedQuery(name = "BookOGE.findById", query = "SELECT b FROM BookOGE b WHERE b.id = :id"),
    @NamedQuery(name = "BookOGE.findByTeacherid", query = "SELECT b FROM BookOGE b WHERE b.teacherid = :teacherid"),
    @NamedQuery(name = "BookOGE.findByStudentid", query = "SELECT b FROM BookOGE b WHERE b.studentid = :studentid"),
    @NamedQuery(name = "BookOGE.findByTeamid", query = "SELECT b FROM BookOGE b WHERE b.teamid = :teamid"),
    @NamedQuery(name = "BookOGE.findByClassrid", query = "SELECT b FROM BookOGE b WHERE b.classrid = :classrid"),
    @NamedQuery(name = "BookOGE.findByOffenceid", query = "SELECT b FROM BookOGE b WHERE b.offenceid = :offenceid"),
    @NamedQuery(name = "BookOGE.findByDate", query = "SELECT b FROM BookOGE b WHERE b.dateofbooking = :dateofbooking")})
public class BookOGE implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer id;
    private Integer teacherid;
    private Integer studentid;
    private Integer teamid;
    private Integer classrid;
    private Integer offenceid;
    private String dateofbooking;

    public BookOGE() {
    }

    public BookOGE(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public Integer getTeamid() {
        return teamid;
    }

    public void setTeamid(Integer teamid) {
        this.teamid = teamid;
    }

    public Integer getClassrid() {
        return classrid;
    }

    public void setClassrid(Integer classrid) {
        this.classrid = classrid;
    }

    public Integer getOffenceid() {
        return offenceid;
    }

    public void setOffenceid(Integer offenceid) {
        this.offenceid = offenceid;
    }

    public String getDateofbooking() {
        return dateofbooking;
    }

    public void setDateofbooking(String dateofbooking) {
        this.dateofbooking = dateofbooking;
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
        if (!(object instanceof BookOGE)) {
            return false;
        }
        BookOGE other = (BookOGE) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "personal.bookingappweboge.entities.BookOGE[ id=" + id + " ]";
    }
    
}
