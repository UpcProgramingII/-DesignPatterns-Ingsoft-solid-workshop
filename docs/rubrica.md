# Rúbrica de evaluación

Cada ejercicio se califica sobre 5,0 con los siguientes criterios.

| Criterio | Peso | Excelente (5,0) | Adecuado (4,0) | En desarrollo (3,0) | Insuficiente (≤ 2,0) |
|---|---|---|---|---|---|
| **Identificación** | 20 % | Encuentra todos los hallazgos relevantes y clasifica bien su tipo y principio | Encuentra la mayoría; confunde algún principio | Encuentra solo los más evidentes | Hallazgos ausentes o equivocados |
| **Evidencia** | 20 % | Cita archivo, método y líneas; explica el mecanismo del problema | Cita la ubicación, con explicación parcial | Afirmaciones generales sin ubicación precisa | Sin evidencia |
| **Consecuencias** | 20 % | Liga cada problema a un cambio previsto, un defecto observable o un costo de prueba | Consecuencias correctas pero genéricas | Repite la definición del principio en lugar de la consecuencia | Sin consecuencias |
| **Criterio de proporcionalidad** | 20 % | Distingue con argumentos lo necesario de lo innecesario; reconoce lo que está bien diseñado | Identifica la sobreingeniería evidente | Trata toda abstracción como buena o como mala | No aborda el tema |
| **Rediseño** | 20 % | Diagrama válido y proporcionado; cada elemento está justificado por el contexto | Diagrama válido con alguna abstracción sin justificar | Diagrama incompleto o sobrediseñado | Sin rediseño o diagrama inválido |

## Penalizaciones

- Diagrama Mermaid que no pasa la validación: −0,5.
- Modificar el código original en `src/`: −0,5.
- Proponer una abstracción sin indicar qué cambio la justifica: −0,2 por cada una (máximo −1,0).

## Bonificación

- Hasta +0,5 por detectar un defecto funcional no mencionado en el enunciado y explicar cómo lo favorece o lo esconde el diseño.
