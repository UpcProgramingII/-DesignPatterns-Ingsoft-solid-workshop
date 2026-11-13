package co.edu.ingsoft.solid.ej04notas.infraestructura;

import co.edu.ingsoft.solid.ej04notas.calculo.FabricaEstrategias;
import co.edu.ingsoft.solid.ej04notas.calculo.IFabricaEstrategias;
import co.edu.ingsoft.solid.ej04notas.eventos.IObservadorCalculo;
import co.edu.ingsoft.solid.ej04notas.eventos.ObservadorBitacora;
import co.edu.ingsoft.solid.ej04notas.persistencia.IRepositorioNotas;
import co.edu.ingsoft.solid.ej04notas.persistencia.RepositorioNotasMemoria;
import co.edu.ingsoft.solid.ej04notas.presentacion.FormateadorConsola;
import co.edu.ingsoft.solid.ej04notas.presentacion.IFormateadorResultado;
import co.edu.ingsoft.solid.ej04notas.validacion.IValidadorNota;
import co.edu.ingsoft.solid.ej04notas.validacion.ValidadorCompuesto;
import co.edu.ingsoft.solid.ej04notas.validacion.ValidadorPeso;
import co.edu.ingsoft.solid.ej04notas.validacion.ValidadorRango;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public final class ContenedorDependencias {

    private static final ContenedorDependencias INSTANCIA = new ContenedorDependencias();

    private final Map<Class<?>, Supplier<?>> registros = new HashMap<>();
    private final Map<Class<?>, Object> singletons = new HashMap<>();

    private ContenedorDependencias() {
        registrar(IRepositorioNotas.class, RepositorioNotasMemoria::new);
        registrar(IFabricaEstrategias.class, FabricaEstrategias::new);
        registrar(IFormateadorResultado.class, FormateadorConsola::new);
        registrar(IObservadorCalculo.class, ObservadorBitacora::new);
        registrar(IValidadorNota.class,
                () -> new ValidadorCompuesto(List.of(new ValidadorRango(), new ValidadorPeso())));
    }

    public static ContenedorDependencias instancia() {
        return INSTANCIA;
    }

    public <T> void registrar(Class<T> tipo, Supplier<? extends T> proveedor) {
        registros.put(tipo, proveedor);
    }

    public <T> T obtener(Class<T> tipo) {
        Object existente = singletons.get(tipo);
        if (existente == null) {
            Supplier<?> proveedor = registros.get(tipo);
            if (proveedor == null) {
                throw new IllegalStateException("No hay registro para " + tipo.getName());
            }
            existente = proveedor.get();
            singletons.put(tipo, existente);
        }
        return tipo.cast(existente);
    }
}
