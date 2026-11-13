package co.edu.ingsoft.solid.ej04notas.servicio;

import co.edu.ingsoft.solid.ej04notas.calculo.IEstrategiaCalculo;
import co.edu.ingsoft.solid.ej04notas.calculo.IFabricaEstrategias;
import co.edu.ingsoft.solid.ej04notas.calculo.TipoCalculo;
import co.edu.ingsoft.solid.ej04notas.eventos.EventoCalculoRealizado;
import co.edu.ingsoft.solid.ej04notas.eventos.IObservadorCalculo;
import co.edu.ingsoft.solid.ej04notas.infraestructura.ContenedorDependencias;
import co.edu.ingsoft.solid.ej04notas.modelo.INota;
import co.edu.ingsoft.solid.ej04notas.modelo.NotaBuilder;
import co.edu.ingsoft.solid.ej04notas.persistencia.IRepositorioNotas;
import co.edu.ingsoft.solid.ej04notas.presentacion.IFormateadorResultado;
import co.edu.ingsoft.solid.ej04notas.validacion.IValidadorNota;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServicioDefinitivaImpl implements IServicioDefinitiva {

    private final IRepositorioNotas repositorio;
    private final IValidadorNota validador;
    private final IEstrategiaCalculo estrategia;
    private final IFormateadorResultado formateador;
    private final List<IObservadorCalculo> observadores = new ArrayList<>();

    public ServicioDefinitivaImpl() {
        ContenedorDependencias c = ContenedorDependencias.instancia();
        this.repositorio = c.obtener(IRepositorioNotas.class);
        this.validador = c.obtener(IValidadorNota.class);
        this.estrategia = c.obtener(IFabricaEstrategias.class).crear(TipoCalculo.PROMEDIO_PONDERADO);
        this.formateador = c.obtener(IFormateadorResultado.class);
        this.observadores.add(c.obtener(IObservadorCalculo.class));
    }

    @Override
    public void registrarNota(double valor, double peso) {
        INota nota = NotaBuilder.nuevaNota().conValor(valor).conPeso(peso).construir();
        validador.validar(nota);
        repositorio.guardar(nota);
    }

    @Override
    public String calcularDefinitiva() {
        List<INota> notas = repositorio.obtenerTodas();
        double definitiva = estrategia.calcular(notas);
        EventoCalculoRealizado evento = new EventoCalculoRealizado(definitiva, notas.size(), LocalDateTime.now());
        observadores.forEach(o -> o.alCalcular(evento));
        repositorio.limpiar();
        return formateador.formatear(definitiva);
    }
}
