package Clases;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author andre
 */
@Entity
@Table(name = "asigna_paquete")
@NamedQueries({
    @NamedQuery(name = "AsignaPaquete.findAll", query = "SELECT a FROM AsignaPaquete a"),
    @NamedQuery(name = "AsignaPaquete.findByIdAsignaPaquete", query = "SELECT a FROM AsignaPaquete a WHERE a.idAsignaPaquete = :idAsignaPaquete"),
    @NamedQuery(name = "AsignaPaquete.findByEstado", query = "SELECT a FROM AsignaPaquete a WHERE a.estado = :estado"),
    @NamedQuery(name = "AsignaPaquete.findByFechaAsignada", query = "SELECT a FROM AsignaPaquete a WHERE a.fechaAsignada = :fechaAsignada")})
public class AsignaPaquete implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAsignaPaquete")
    private Integer idAsignaPaquete;
    @Column(name = "estado")
    private String estado;
    @Basic(optional = false)
    @Column(name = "fechaAsignada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAsignada;
    @JoinColumn(name = "paquete", referencedColumnName = "codigo_unico")
    @ManyToOne(optional = false)
    private Paquete paquete;
    @JoinColumn(name = "repartidor", referencedColumnName = "idRepartidor")
    @ManyToOne(optional = false)
    private Repartidor repartidor;

    public AsignaPaquete() {
    }

    public AsignaPaquete(Integer idAsignaPaquete) {
        this.idAsignaPaquete = idAsignaPaquete;
    }

    public AsignaPaquete(Integer idAsignaPaquete, Date fechaAsignada) {
        this.idAsignaPaquete = idAsignaPaquete;
        this.fechaAsignada = fechaAsignada;
    }

    public Integer getIdAsignaPaquete() {
        return idAsignaPaquete;
    }

    public void setIdAsignaPaquete(Integer idAsignaPaquete) {
        this.idAsignaPaquete = idAsignaPaquete;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaAsignada() {
        return fechaAsignada;
    }

    public void setFechaAsignada(Date fechaAsignada) {
        this.fechaAsignada = fechaAsignada;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsignaPaquete != null ? idAsignaPaquete.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignaPaquete)) {
            return false;
        }
        AsignaPaquete other = (AsignaPaquete) object;
        if ((this.idAsignaPaquete == null && other.idAsignaPaquete != null) || (this.idAsignaPaquete != null && !this.idAsignaPaquete.equals(other.idAsignaPaquete))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.AsignaPaquete[ idAsignaPaquete=" + idAsignaPaquete + " ]";
    }

}
