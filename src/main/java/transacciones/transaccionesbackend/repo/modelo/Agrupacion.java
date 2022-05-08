package transacciones.transaccionesbackend.repo.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Agrupacion {
    @Id
    @Column
    private String categoria;
    @Column
    private Double valor;
    @Column
    private Integer cantidad;

    public Agrupacion() {}

    public Agrupacion(String categoria, Double valor, Integer cantidad) {
        this.categoria = categoria;
        this.valor = valor;
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public Double getValor() {
        return valor;
    }

    public Integer getCantidad() {
        return cantidad;
    }
}
