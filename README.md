## Monedero

### Contexto

Este repositorio contiene el código de un _monedero virtual_, al que podemos agregarle y quitarle dinero, a través 
de los métodos `Monedero.sacar` y `Monedero.poner`, respectivamente. 
Pero hay algunos problemas: por un lado el código no está muy bien testeado, y por el otro, hay numeros _code smells_. 

### Consigna

Tenés seis tareas: 

 1. :fork_and_knife: Hacé un repositorio usando este template (presionando desde Github el botón _use this template_)
 2. :arrow_down: Descargalo y construí el proyecto, utilizando `maven`
 2. :nose: Identificá y anotá todos los _code smells_ que encuentres 
 3. :test_tube: Agregá los tests faltantes y mejorá los existentes. 
     * :eyes: Ojo: ¡un test sin ningún tipo de aserción está incompleto!
 4. :rescue_worker_helmet: Corregí smells, de a un commit por vez. 
 5. :arrow_up: Subí todos los cambios a tu _fork_
 6. :bug: Activá los issues de Github desde https://github.com/TU_GITHUB_USERNAME/dds-monedero-java8/settings así podemos darte nuestras devoluciones

### Solucion. Code smells encontrados y corregidos:
1. Long method sacar() y poner().
2. Duplicated code en sacar() y poner().
3. Type test en calcularValor().
4. Temporary field en esDeposito.
5. Long parameter list en agregarMovimiento().
6. Dead code en setMovimientos().

### Observaciones:
1. fueDepositado() y fueExtraido() son dead code pero no fueron eliminados porque serian funcionales a resolver el problerma de getMontoExtraidoA() que no tiene en cuenta la fecha en el filter.
 

  


