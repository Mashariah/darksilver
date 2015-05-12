/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 1:52:08 PM  : Mar 14, 2015
 */

package domain.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kelli
 */
@Entity
@Table(name = "authors")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Author.findAll", query = "SELECT a FROM Author a"),
    @NamedQuery(name = "Author.findByAuthorId", query = "SELECT a FROM Author a WHERE a.authorId = :authorId"),
    @NamedQuery(name = "Author.findBySurname", query = "SELECT a FROM Author a WHERE a.surname = :surname"),
    @NamedQuery(name = "Author.findByInitials", query = "SELECT a FROM Author a WHERE a.initials = :initials"),
    @NamedQuery(name = "Author.findByAffiliation", query = "SELECT a FROM Author a WHERE a.affiliation = :affiliation"),
    @NamedQuery(name = "Author.findByEmail", query = "SELECT a FROM Author a WHERE a.email = :email"),
    @NamedQuery(name = "Author.findByPhone", query = "SELECT a FROM Author a WHERE a.phone = :phone")})
public class Author implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "authorId")
    private Collection<Manuscript> manuscriptCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "author_id")
    private Integer authorId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "surname")
    private String surname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "initials")
    private String initials;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "affiliation")
    private String affiliation;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 20)
    @Column(name = "email")
    private String email;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 15)
    @Column(name = "phone")
    private String phone;

    public Author() {
    }

    public Author(Integer authorId) {
        this.authorId = authorId;
    }

    public Author(Integer authorId, String surname, String initials, String affiliation) {
        this.authorId = authorId;
        this.surname = surname;
        this.initials = initials;
        this.affiliation = affiliation;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (authorId != null ? authorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Author)) {
            return false;
        }
        Author other = (Author) object;
        if ((this.authorId == null && other.authorId != null) || (this.authorId != null && !this.authorId.equals(other.authorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.entities.Author[ authorId=" + authorId + " ]";
    }

    @XmlTransient
    public Collection<Manuscript> getManuscriptCollection() {
        return manuscriptCollection;
    }

    public void setManuscriptCollection(Collection<Manuscript> manuscriptCollection) {
        this.manuscriptCollection = manuscriptCollection;
    }
    
}
