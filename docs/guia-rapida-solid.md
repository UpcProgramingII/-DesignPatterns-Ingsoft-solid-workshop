# Guía rápida: SOLID y señales de sobreingeniería

## Los cinco principios

| Principio | Idea central | Síntomas típicos de violación |
|---|---|---|
| **SRP** · Responsabilidad única | Un módulo responde a un solo actor (un grupo de personas que pide cambios por la misma razón) | Clases que mezclan reglas de negocio, persistencia y comunicación; cambios de un área que rompen otra; conflictos de fusión frecuentes en el mismo archivo |
| **OCP** · Abierto/Cerrado | Agregar comportamiento escribiendo código nuevo, sin modificar el que ya funciona | `switch` o cadenas de `if` sobre un tipo, repetidos en varios lugares; cada variante nueva obliga a editar muchas clases |
| **LSP** · Sustitución de Liskov | Un subtipo cumple todas las promesas de su supertipo: no exige más ni garantiza menos | `UnsupportedOperationException`; valores especiales como `-1` o `null` para casos «raros»; `instanceof` para tratar subtipos de forma distinta |
| **ISP** · Segregación de interfaces | Los clientes no dependen de métodos que no usan | Interfaces grandes con implementaciones vacías o que lanzan excepciones; dobles de prueba enormes |
| **DIP** · Inversión de dependencias | Las políticas de negocio no dependen de los detalles; las abstracciones pertenecen a quien las usa | `new` de clases de infraestructura dentro del dominio; interfaces que exponen tipos técnicos (`ResultSet`, `HttpResponse`); pruebas que necesitan base de datos o red |

## Reglas para contratos (LSP)

Un subtipo puede **pedir menos y prometer más**, nunca al revés:

- Precondiciones: iguales o más débiles.
- Postcondiciones: iguales o más fuertes.
- Invariantes: se preservan.
- Excepciones: no aparecen excepciones que el contrato no menciona.

## Señales de sobreingeniería

- Una interfaz con una sola implementación **y** sin necesidad de sustituirla en pruebas ni en el futuro previsible.
- Fábricas que crean un solo tipo; estrategias con una sola estrategia; observadores con un solo suscriptor.
- Un *Builder* para un objeto con dos campos.
- Un «repositorio» para datos que nunca se guardan.
- Un contenedor de dependencias hecho a mano en un programa de un solo flujo.
- Para seguir una operación simple hay que abrir más de cinco archivos.

## Preguntas para decidir si una abstracción vale la pena

1. ¿Qué **cambio concreto** protege? ¿Es probable en el horizonte del proyecto?
2. ¿Existe hoy, o existirá pronto, una **segunda implementación**? (Un doble de prueba cuenta).
3. ¿Marca una **frontera** con algo volátil: base de datos, red, proveedor externo o *framework*?
4. ¿Cuánto cuesta introducirla **después**, cuando el cambio llegue de verdad?

Si las tres primeras respuestas son «no» y la cuarta es «poco», probablemente no la necesita todavía.

## Abstracción ausente, defectuosa o innecesaria

| Tipo | Ejemplo | Corrección |
|---|---|---|
| Ausente | Un servicio de despachos crea con `new` el cliente HTTP de una transportadora que se cambiará el próximo trimestre | Introducir un puerto definido por el servicio |
| Defectuosa | Una interfaz `ClienteClima` que devuelve `HttpResponse<String>` y vive junto a su implementación HTTP | Expresarla en términos del dominio (`Pronostico`) y ubicarla en el paquete que la usa |
| Innecesaria | Una interfaz y una fábrica para formatear fechas con un único formato fijado por norma | Eliminarlas y usar `DateTimeFormatter` directamente |
