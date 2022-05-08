package transacciones.transaccionesbackend.api;

import static java.util.Optional.ofNullable;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import transacciones.transaccionesbackend.api.mensajes.FiltrosCategoriaRequest;
import transacciones.transaccionesbackend.api.mensajes.FiltrosRequest;
import transacciones.transaccionesbackend.repo.TransaccionesRepo;
import transacciones.transaccionesbackend.repo.TransaccionesServicio;
import transacciones.transaccionesbackend.repo.modelo.Agrupacion;
import transacciones.transaccionesbackend.repo.modelo.Balance;
import transacciones.transaccionesbackend.repo.modelo.Movimiento;
import transacciones.transaccionesbackend.repo.modelo.Transaccion;

@CrossOrigin
@RestController
@RequestMapping("api")
public class APIController {
    private static final String CATEGORIA_DEF = "descripcion";
    private static final String FECHA_MIN = "0001-01-01";
    private static final String FECHA_MAX = "9999-01-01";

    private final TransaccionesRepo transaccionesRepo;
    private final TransaccionesServicio transaccionesServicio;

    public APIController(
        TransaccionesRepo transaccionesRepo,
        TransaccionesServicio transaccionesServicio) {
        this.transaccionesRepo = transaccionesRepo;
        this.transaccionesServicio = transaccionesServicio;
    }

    @GetMapping("balance/actual")
    public Double getBalanceActual() {
        return transaccionesRepo.balanceActual();
    }

    @GetMapping("balance")
    public List<Balance> listarBalances(FiltrosRequest request) {
        return transaccionesRepo.calcularBalances(
            ofNullable(request.getDesde()).orElse(FECHA_MIN),
            ofNullable(request.getHasta()).orElse(FECHA_MAX)
        );
    }

    @GetMapping("movimiento")
    public List<Movimiento> listarMovimientos(FiltrosRequest request) {
        return transaccionesRepo.calcularMovimientos(
            ofNullable(request.getDesde()).orElse(FECHA_MIN),
            ofNullable(request.getHasta()).orElse(FECHA_MAX)
        );
    }

    @GetMapping("categorias")
    public List<Agrupacion> listarCategorias(FiltrosCategoriaRequest request) {
        return transaccionesServicio.listarCategorias(
            ofNullable(request.getCategoria()).orElse(CATEGORIA_DEF),
            ofNullable(request.getDesde()).orElse(FECHA_MIN),
            ofNullable(request.getHasta()).orElse(FECHA_MAX)
        );
    }

    @GetMapping("transaccion")
    public List<Transaccion> listarTransacciones(FiltrosRequest request) {
        return transaccionesRepo.listarOrdenado(
            ofNullable(request.getDesde()).orElse(FECHA_MIN),
            ofNullable(request.getHasta()).orElse(FECHA_MAX)
        );
    }
}
