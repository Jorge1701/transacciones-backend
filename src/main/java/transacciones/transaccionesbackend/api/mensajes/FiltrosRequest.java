package transacciones.transaccionesbackend.api.mensajes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FiltrosRequest {
    private final String desde;
    private final String hasta;

    @JsonCreator
    public FiltrosRequest(
        @JsonProperty(required = false) String desde,
        @JsonProperty(required = false) String hasta) {
        this.desde = desde;
        this.hasta = hasta;
    }

    public String getDesde() {
        return desde;
    }

    public String getHasta() {
        return hasta;
    }
}
