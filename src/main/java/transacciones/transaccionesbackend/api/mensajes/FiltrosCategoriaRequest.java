package transacciones.transaccionesbackend.api.mensajes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FiltrosCategoriaRequest extends FiltrosRequest {
    private final String categoria;

    @JsonCreator
    public FiltrosCategoriaRequest(
        @JsonProperty(required = false) String categoria,
        @JsonProperty(required = false) String desde,
        @JsonProperty(required = false) String hasta) {
        super(desde, hasta);
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }
}
