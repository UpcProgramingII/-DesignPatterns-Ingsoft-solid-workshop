package co.edu.ingsoft.solid.ej05matricula.infraestructura;

import java.util.UUID;

public class PasarelaPagosBancoAndino {

    private final String urlBase;
    private final String llaveApi;

    public PasarelaPagosBancoAndino(String urlBase, String llaveApi) {
        this.urlBase = urlBase;
        this.llaveApi = llaveApi;
    }

    /** Cobra el valor a la tarjeta y devuelve el número de autorización del banco. */
    public String cobrarConTarjeta(String numeroTarjeta, double valor, String referencia) {
        if (numeroTarjeta == null || numeroTarjeta.length() < 15) {
            throw new IllegalArgumentException("Número de tarjeta inválido");
        }
        System.out.printf("POST %s/cobros [%s] referencia=%s valor=%,.0f%n",
                urlBase, llaveApi.substring(0, 5) + "***", referencia, valor);
        return "BA-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
