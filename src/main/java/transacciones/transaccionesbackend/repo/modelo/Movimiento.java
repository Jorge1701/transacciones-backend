package transacciones.transaccionesbackend.repo.modelo;

import java.util.Date;

public interface Movimiento {
    Date getFecha();
    Double getBalance();
    Integer getMovimientos();
    Double getTotal();
}
