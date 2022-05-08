package transacciones.transaccionesbackend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import transacciones.transaccionesbackend.repo.modelo.Balance;
import transacciones.transaccionesbackend.repo.modelo.Movimiento;
import transacciones.transaccionesbackend.repo.modelo.Transaccion;

@Repository
public interface TransaccionesRepo extends JpaRepository<Transaccion, Long> {

    @Query(value = "select " +
            "(select sum(-1 * coalesce(t2.debito, 0) + coalesce(t2.credito, 0)) " +
            "from transacciones.transaccion t2 " +
            "where t2.fecha <= t.fecha) as balance " +
        "from transacciones.transaccion t " +
        "order by t.fecha desc " +
        "limit 1",
        nativeQuery = true
    )
    Double balanceActual();

    @Query(value = "select " +
            "distinct t.fecha as fecha, " +
            "(select sum(-1 * coalesce(t2.debito, 0) + coalesce(t2.credito, 0)) " +
            "from transacciones.transaccion t2 " +
            "where t2.fecha <= t.fecha) as balance " +
        "from transacciones.transaccion t " +
        "where t.fecha >= cast(:desde as date) " +
        "and t.fecha <= cast(:hasta as date) " +
        "order by t.fecha asc",
        nativeQuery = true
    )
    List<Balance> calcularBalances(
        @Param("desde") String desde,
        @Param("hasta") String hasta
    );

    @Query(value = "select " +
            "t.fecha as fecha, " +
            "t2.balance as balance, " +
            "count(*) as movimientos, " +
            "sum(-1 * coalesce(t.debito, 0) + coalesce(t.credito, 0)) as total " +
        "from transacciones.transaccion t " +
        "join ( " +
                "select " +
                "distinct t.fecha as fecha, " +
                "(select sum(-1 * coalesce(t2.debito, 0) + coalesce(t2.credito, 0)) " +
                "from transacciones.transaccion t2 " +
                "where t2.fecha <= t.fecha) as balance " +
            "from transacciones.transaccion t " +
            "order by t.fecha desc " +
        ") t2 on t2.fecha = t.fecha " +
        "where t.fecha >= cast(:desde as date) " +
        "and t.fecha <= cast(:hasta as date) " +
        "group by t.fecha, t2.balance " +
        "order by t.fecha asc",
        nativeQuery = true
    )
    List<Movimiento> calcularMovimientos(
        @Param("desde") String desde,
        @Param("hasta") String hasta
    );

    @Query(
        "SELECT t FROM Transaccion t " +
        "WHERE t.fecha >= cast(:desde AS date) " +
        "AND t.fecha <= cast(:hasta AS date) " +
        "ORDER BY t.fecha asc"
    )
    List<Transaccion> listarOrdenado(
        @Param("desde") String desde,
        @Param("hasta") String hasta
    );
}
