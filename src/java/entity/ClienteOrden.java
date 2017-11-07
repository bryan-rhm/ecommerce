/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "cliente_orden")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteOrden.findAll", query = "SELECT c FROM ClienteOrden c")
    , @NamedQuery(name = "ClienteOrden.findByIdclienteOrden", query = "SELECT c FROM ClienteOrden c WHERE c.idclienteOrden = :idclienteOrden")
    , @NamedQuery(name = "ClienteOrden.findByCantidad", query = "SELECT c FROM ClienteOrden c WHERE c.cantidad = :cantidad")
    , @NamedQuery(name = "ClienteOrden.findByFecha", query = "SELECT c FROM ClienteOrden c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "ClienteOrden.findByCliente", query = "SELECT c FROM ClienteOrden c WHERE c.cliente = :cliente")})
public class ClienteOrden implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcliente_orden")
    private Integer idclienteOrden;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private BigDecimal cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cliente")
    private int cliente;
    public ClienteOrden() throws ClassNotFoundException {
         String Query = "select * from cliente_orden order by idcliente_orden desc limit 1";
            Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ecommerce","root","abc123**")) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(Query);
            while (rs.next()){
                int id = (rs.getInt("idcliente_orden"))+1;
                idclienteOrden = id;
            }
           
        }catch (Exception e){
            System.out.println(e);
        }       
    }

    public ClienteOrden(Integer idclienteOrden) {
        this.idclienteOrden = idclienteOrden;
    }

    public ClienteOrden(Integer idclienteOrden, BigDecimal cantidad, Date fecha, int cliente) {
        this.idclienteOrden = idclienteOrden;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.cliente = cliente;
    }

    public Integer getIdclienteOrden() {
        return idclienteOrden;
    }

    public void setIdclienteOrden(Integer idclienteOrden) {
        this.idclienteOrden = idclienteOrden;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idclienteOrden != null ? idclienteOrden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClienteOrden)) {
            return false;
        }
        ClienteOrden other = (ClienteOrden) object;
        if ((this.idclienteOrden == null && other.idclienteOrden != null) || (this.idclienteOrden != null && !this.idclienteOrden.equals(other.idclienteOrden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ClienteOrden[ idclienteOrden=" + idclienteOrden + " ]";
    }
    
}
