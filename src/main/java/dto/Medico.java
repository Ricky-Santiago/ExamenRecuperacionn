/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RICKY SANTIAGO
 */
@Entity
@Table(name = "medico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medico.findAll", query = "SELECT m FROM Medico m"),
    @NamedQuery(name = "Medico.findByCodiMedi", query = "SELECT m FROM Medico m WHERE m.codiMedi = :codiMedi"),
    @NamedQuery(name = "Medico.findByNdniMedi", query = "SELECT m FROM Medico m WHERE m.ndniMedi = :ndniMedi"),
    @NamedQuery(name = "Medico.findByAppaMedi", query = "SELECT m FROM Medico m WHERE m.appaMedi = :appaMedi"),
    @NamedQuery(name = "Medico.findByApmaMedi", query = "SELECT m FROM Medico m WHERE m.apmaMedi = :apmaMedi"),
    @NamedQuery(name = "Medico.findByNombMedi", query = "SELECT m FROM Medico m WHERE m.nombMedi = :nombMedi"),
    @NamedQuery(name = "Medico.findByFechNaciMedi", query = "SELECT m FROM Medico m WHERE m.fechNaciMedi = :fechNaciMedi"),
    @NamedQuery(name = "Medico.findByLogiMedi", query = "SELECT m FROM Medico m WHERE m.logiMedi = :logiMedi"),
    @NamedQuery(name = "Medico.validar", query = "SELECT m FROM Medico m WHERE m.ndniMedi = :ndniMedi and m.passMedi = :passMedi"),
    @NamedQuery(name = "Medico.findByPassMedi", query = "SELECT m FROM Medico m WHERE m.passMedi = :passMedi")})
public class Medico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codiMedi")
    private Integer codiMedi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "ndniMedi")
    private String ndniMedi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "appaMedi")
    private String appaMedi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "apmaMedi")
    private String apmaMedi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombMedi")
    private String nombMedi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechNaciMedi")
    @Temporal(TemporalType.DATE)
    private Date fechNaciMedi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "logiMedi")
    private String logiMedi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "passMedi")
    private String passMedi;

    public Medico() {
    }

    public Medico(Integer codiMedi) {
        this.codiMedi = codiMedi;
    }

    public Medico(Integer codiMedi, String ndniMedi, String appaMedi, String apmaMedi, String nombMedi, Date fechNaciMedi, String logiMedi, String passMedi) {
        this.codiMedi = codiMedi;
        this.ndniMedi = ndniMedi;
        this.appaMedi = appaMedi;
        this.apmaMedi = apmaMedi;
        this.nombMedi = nombMedi;
        this.fechNaciMedi = fechNaciMedi;
        this.logiMedi = logiMedi;
        this.passMedi = passMedi;
    }

    public Integer getCodiMedi() {
        return codiMedi;
    }

    public void setCodiMedi(Integer codiMedi) {
        this.codiMedi = codiMedi;
    }

    public String getNdniMedi() {
        return ndniMedi;
    }

    public void setNdniMedi(String ndniMedi) {
        this.ndniMedi = ndniMedi;
    }

    public String getAppaMedi() {
        return appaMedi;
    }

    public void setAppaMedi(String appaMedi) {
        this.appaMedi = appaMedi;
    }

    public String getApmaMedi() {
        return apmaMedi;
    }

    public void setApmaMedi(String apmaMedi) {
        this.apmaMedi = apmaMedi;
    }

    public String getNombMedi() {
        return nombMedi;
    }

    public void setNombMedi(String nombMedi) {
        this.nombMedi = nombMedi;
    }

    public Date getFechNaciMedi() {
        return fechNaciMedi;
    }

    public void setFechNaciMedi(Date fechNaciMedi) {
        this.fechNaciMedi = fechNaciMedi;
    }

    public String getLogiMedi() {
        return logiMedi;
    }

    public void setLogiMedi(String logiMedi) {
        this.logiMedi = logiMedi;
    }

    public String getPassMedi() {
        return passMedi;
    }

    public void setPassMedi(String passMedi) {
        this.passMedi = passMedi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codiMedi != null ? codiMedi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medico)) {
            return false;
        }
        Medico other = (Medico) object;
        if ((this.codiMedi == null && other.codiMedi != null) || (this.codiMedi != null && !this.codiMedi.equals(other.codiMedi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Medico[ codiMedi=" + codiMedi + " ]";
    }
    
}
