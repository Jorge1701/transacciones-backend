package transacciones.transaccionesbackend.repo.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "transacciones", name = "transaccion")
public class Transaccion {
    
    @Id
    private Long id;

    @Column
    private Date fecha;

    @Column
    private String descripcion;

    @Column
    private String nro_doc;

    @Column
    private String asunto;

    @Column
    private Double debito;

    @Column
    private Double credito;

    public Transaccion() {}

    public Long getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNro_doc() {
        return nro_doc;
    }

    public String getAsunto() {
        return asunto;
    }

    public Double getDebito() {
        return debito;
    }

    public Double getCredito() {
        return credito;
    }
}
