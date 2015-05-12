/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 11:42:00 AM  : Mar 29, 2015
 */

package domain.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.Transient;
/**
 *
 * @author kelli
 */
@Entity
@Table(name = "manuscripts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Manuscript.findAll", query = "SELECT m FROM Manuscript m order by m.manuscriptId desc"),
    @NamedQuery(name = "Manuscript.findByManuscriptId", query = "SELECT m FROM Manuscript m WHERE m.manuscriptId = :manuscriptId"),
    @NamedQuery(name = "Manuscript.findByManuscriptRefNumber", query = "SELECT m FROM Manuscript m WHERE m.manuscriptRefNumber = :manuscriptRefNumber"),
//    @NamedQuery(name = "Manuscript.findByTitle", query = "SELECT m FROM Manuscript m WHERE FUNC('MATCH', m.title)  FUNC ('AGAINST',  :title)"),
    @NamedQuery(name = "Manuscript.findByDateSubmitted", query = "SELECT m FROM Manuscript m WHERE m.dateSubmitted = :dateSubmitted"),
    @NamedQuery(name = "Manuscript.findByStatus", query = "SELECT m FROM Manuscript m WHERE m.status = :status"),
    @NamedQuery(name = "Manuscript.findByAffiliation", query = "SELECT m FROM Manuscript m WHERE m.authorId.affiliation=:affiliation order by m.manuscriptId desc"),
    @NamedQuery(name = "Manuscript.findByAuthor", query = "SELECT m FROM Manuscript m WHERE m.authorId.surname= :surname")})
@NamedNativeQuery(name="Manuscript.nativeFindByTitle", query="select * from manuscripts where match(title) against ( ? in natural language mode)",resultClass=Manuscript.class )
public class Manuscript implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "manuscript_id")
    private Integer manuscriptId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "manuscript_ref_number")
    private String manuscriptRefNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_submitted")
    @Temporal(TemporalType.DATE)
    private Date dateSubmitted;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    @ManyToOne(optional = false)
    private Author authorId;
    @Transient
    private String year;

    public Manuscript() {
    }

    public Manuscript(Integer manuscriptId) {
        this.manuscriptId = manuscriptId;
    }

    public Manuscript(Integer manuscriptId, String manuscriptRefNumber, String title, Date dateSubmitted, String status) {
        this.manuscriptId = manuscriptId;
        this.manuscriptRefNumber = manuscriptRefNumber;
        this.title = title;
        this.dateSubmitted = dateSubmitted;
        this.status = status;
    }

    public Integer getManuscriptId() {
        return manuscriptId;
    }

    public void setManuscriptId(Integer manuscriptId) {
        this.manuscriptId = manuscriptId;
    }

    public String getManuscriptRefNumber() {
        return manuscriptRefNumber;
    }

    public void setManuscriptRefNumber(String manuscriptRefNumber) {
        this.manuscriptRefNumber = manuscriptRefNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }
    
 public void  setYear(String year){
     this.year = year;
 }
 
 public String  getYear(){
     return year;
 }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Author getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Author authorId) {
        this.authorId = authorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (manuscriptId != null ? manuscriptId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Manuscript)) {
            return false;
        }
        Manuscript other = (Manuscript) object;
        if ((this.manuscriptId == null && other.manuscriptId != null) || (this.manuscriptId != null && !this.manuscriptId.equals(other.manuscriptId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.entities.Manuscript[ manuscriptId=" + manuscriptId + " ]";
    }
    
}
