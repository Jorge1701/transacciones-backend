package transacciones.transaccionesbackend.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import transacciones.transaccionesbackend.repo.modelo.Agrupacion;

@Service
public class TransaccionesServicio {
    
    private final EntityManager entityManager;

    public TransaccionesServicio(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Agrupacion> listarCategorias(String columna, String desde, String hasta) {
        Query query = entityManager.createNativeQuery(String.format(
            "select t.%s as categoria, sum(-1 * coalesce(t.debito, 0) + coalesce(t.credito, 0)) as valor, count(*) cantidad " +
            "from transacciones.transaccion t " +
            "where t.fecha >= cast(:desde as date) " +
            "and t.fecha <= cast(:hasta as date) " +
            "group by t.%s " +
            "order by 2 desc",
            columna, columna
        ), Agrupacion.class);

        query.setParameter("desde", desde);
        query.setParameter("hasta", hasta);

        return query.getResultList();
    }
}
