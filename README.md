# Ejercicios de análisis de diseño: principios SOLID en Java

Cinco casos de estudio para **identificar, justificar y corregir** problemas de diseño orientado a objetos. Cada caso trae un contexto de negocio, código Java que compila y se ejecuta, y un diagrama de clases del diseño actual.

El objetivo no es solo encontrar violaciones a SOLID, sino también desarrollar el criterio contrario: **reconocer cuándo una abstracción no hace falta**. Algunos casos tienen problemas por falta de diseño; otros, por exceso; y otros, por ambas cosas a la vez.

> Curso de Ingeniería de Software · 2026-I

## Objetivos de aprendizaje

Al terminar los ejercicios, el estudiante podrá:

1. Identificar violaciones de SRP, OCP, LSP, ISP y DIP en código real, citando evidencia concreta.
2. Explicar las **consecuencias** de cada violación en términos de cambios futuros, defectos y capacidad de prueba.
3. Distinguir entre una abstracción **necesaria**, una **defectuosa** y una **innecesaria**, a partir del contexto y de los cambios previsibles.
4. Proponer un rediseño proporcionado y documentarlo con un diagrama de clases.

## Ejercicios

| # | Caso | Nivel | Tiempo | Enfoque |
|---|---|---|---|---|
| 1 | [Préstamos de la Biblioteca Central](ejercicios/ej01-biblioteca/README.md) | Básico | 60 min | Clase con múltiples responsabilidades y dependencias concretas |
| 2 | [Notificaciones de DomiExpress](ejercicios/ej02-notificaciones/README.md) | Intermedio | 75 min | Interfaces y contratos entre implementaciones |
| 3 | [Parqueadero ParkCentro](ejercicios/ej03-parqueadero/README.md) | Intermedio | 75 min | Jerarquías de herencia y sustitución |
| 4 | [Calculadora de nota definitiva](ejercicios/ej04-calculadora-notas/README.md) | Intermedio | 60 min | Proporcionalidad del diseño |
| 5 | [Liquidación y pago de matrícula](ejercicios/ej05-matricula/README.md) | Avanzado | 90 min | Caso mixto |

Se recomienda resolverlos en orden. Cada enunciado incluye una sección de **cambios previstos**: úsela como criterio principal para decidir qué es un problema real y qué no.

## Estructura del repositorio

```
.
├── ejercicios/                 Enunciados: contexto, diagrama y actividades
│   ├── ej01-biblioteca/
│   ├── ej02-notificaciones/
│   ├── ej03-parqueadero/
│   ├── ej04-calculadora-notas/
│   └── ej05-matricula/
├── src/main/java/co/edu/ingsoft/solid/
│   ├── ej01biblioteca/         Código de cada ejercicio
│   ├── ej02notificaciones/
│   ├── ej03parqueadero/
│   ├── ej04notas/
│   └── ej05matricula/
├── docs/
│   ├── guia-rapida-solid.md    Repaso de los principios y señales de sobreingeniería
│   ├── plantilla-analisis.md   Formato de entrega
│   └── rubrica.md              Criterios de evaluación
├── entregas/                   Aquí van los análisis de los estudiantes
├── scripts/
│   └── validar-mermaid.mjs     Verifica la sintaxis de los diagramas
├── .github/                    Integración continua y plantilla de pull request
└── pom.xml
```

## Requisitos

- JDK 21 o superior.
- Maven 3.9 o superior (opcional: también se puede compilar solo con `javac`).
- Un editor que muestre Mermaid para revisar los diagramas (GitHub, IntelliJ IDEA, VS Code con la extensión *Markdown Preview Mermaid Support*). También puede usar [mermaid.live](https://mermaid.live).

## Compilar y ejecutar

Con Maven:

```bash
mvn -q compile
java -cp target/classes co.edu.ingsoft.solid.ej02notificaciones.DemoNotificaciones
java -cp target/classes co.edu.ingsoft.solid.ej03parqueadero.DemoParqueadero
java -cp target/classes co.edu.ingsoft.solid.ej04notas.AplicacionCalculadora 3.0 2.9 3.0
java -cp target/classes co.edu.ingsoft.solid.ej05matricula.DemoMatricula
```

Sin Maven:

```bash
javac -d out $(find src -name "*.java")
java -cp out co.edu.ingsoft.solid.ej03parqueadero.DemoParqueadero
```

> Si la consola muestra caracteres extraños en las tildes, agregue `-Dstdout.encoding=UTF-8` al comando `java`.

El ejercicio 1 no tiene programa de demostración: su análisis es sobre el código fuente.

## Cómo trabajar y entregar

1. Obtenga su copia del repositorio: con la invitación de **GitHub Classroom** que comparta el docente o con un *fork*.
2. Cree una rama por ejercicio: `git switch -c analisis-ej01`.
3. Copie [`docs/plantilla-analisis.md`](docs/plantilla-analisis.md) en `entregas/<su-usuario-github>/ej01.md` y complétela.
4. Si su rediseño incluye código, póngalo en `entregas/<su-usuario-github>/ej01/` y **no modifique** el código original en `src/`.
5. Verifique que sus diagramas Mermaid son válidos (ver abajo) y abra un *pull request* hacia la rama `main`. La plantilla del *pull request* incluye una lista de verificación.

### Validar los diagramas antes de entregar

```bash
cd scripts
npm install mermaid jsdom
node validar-mermaid.mjs ../entregas
```

La integración continua ejecuta la misma validación en cada *pull request*.

## Reglas del juego

- **Toda afirmación necesita evidencia.** «Viola SRP» no es una respuesta; «`Factura.imprimir()` calcula el IVA y además arma el PDF; la primera regla la cambia Contabilidad y la segunda el área de Diseño» sí lo es.
- **Toda consecuencia se liga a un cambio.** Use la sección de cambios previstos de cada enunciado.
- **No todo es una violación.** Algunas clases están bien diseñadas, y concluirlo con argumentos también suma puntos.
- **Menos puede ser más.** Un rediseño que agrega diez interfaces sin justificar cada una se evalúa peor que uno sencillo y bien argumentado.

## Material de apoyo

- [Guía rápida de SOLID y señales de sobreingeniería](docs/guia-rapida-solid.md)
- [Rúbrica de evaluación](docs/rubrica.md)
