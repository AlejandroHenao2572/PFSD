# PFSD - Programación Funcional de Sistemas Distribuidos

## Taller 1 - Sistema de Transacciones Bancarias

### Estructura del Proyecto

```
PFSD/
├── PrimerTercio/
│   └── Taller1/
│       └── transacion.scala    # Código principal con Main object
├── build.sbt                    # Configuración del proyecto
└── README.md
```

### Requisitos

- Scala 2.13.x (recomendado 2.13.18)
- JDK 11 o superior (recomendado JDK 21)
- IntelliJ IDEA con plugin de Scala (recomendado) o SBT

### Descripción del Código

El archivo `transacion.scala` contiene:

1. **Case class `Transacion`**: Representa una transacción bancaria con:
   - `id`: Identificador de la transacción
   - `cuentaId`: Identificador de la cuenta
   - `monto`: Monto de la transacción
   - `tipo`: Tipo de transacción (deposito, retiro, etc.)

2. **Métodos de la clase**:
   - `esTransacionSegura()`: Valida que el monto esté entre 0 y 10,000
   - `calcularComision(banco, tasa)(monto)`: Calcula comisión usando currying

3. **Companion object `Transacion`**:
   - `procesarLote`: Procesa lotes de transacciones con filtros y transformaciones

4. **Object `Main`**: Punto de entrada de la aplicación con ejemplos de uso

### Cómo Compilar y Ejecutar

#### Opción 1: Usando IntelliJ IDEA (Recomendado)

1. **Abrir el proyecto en IntelliJ IDEA**
   - File → Open → Seleccionar la carpeta PFSD

2. **Configurar el SDK**
   - File → Project Structure → Project Settings → Project
   - Configurar Project SDK (JDK 21 o superior)
   - Configurar Scala SDK (2.13.18)

3. **Compilar el código**
   - Build → Build Project
   - O presionar `Ctrl+F9` (Windows/Linux) o `Cmd+F9` (Mac)

4. **Ejecutar la aplicación**
   - Click derecho en `transacion.scala`
   - Seleccionar "Run 'Main'"
   - O abrir el archivo y hacer click en el ícono verde al lado de `object Main extends App`

#### Opción 2: Usando scalac (línea de comandos)

```bash
# Compilar el archivo
cd PrimerTercio/Taller1
scalac transacion.scala

# Ejecutar la aplicación
scala Main
```

#### Opción 3: Usando SBT

```bash
# Compilar el proyecto
sbt compile

# Ejecutar la aplicación Main
sbt "runMain Main"
```

### Solución del Error "ClassNotFoundException: Main"

Si encuentras el error `Error: Could not find or load main class Main`, esto puede deberse a:

#### Causa 1: El código no está compilado
**Solución**: Compilar el proyecto en IntelliJ (Build → Build Project)

#### Causa 2: Estructura incorrecta del archivo
**Solución**: Asegurar que `object Main extends App` esté al nivel raíz del archivo (no anidado dentro de otra clase u objeto)

#### Causa 3: Nombre de archivo incorrecto
**Solución**: Aunque el archivo se llama `transacion.scala`, el Main object debe estar al nivel raíz y extender App

#### Causa 4: Cache de IntelliJ corrupto
**Solución**: File → Invalidate Caches → Invalidate and Restart

#### Causa 5: Configuración incorrecta de Run
**Solución**: 
1. Run → Edit Configurations
2. Crear una nueva configuración de tipo "Application" o "Scala"
3. Configurar:
   - Main class: `Main`
   - Working directory: carpeta del proyecto
   - Use classpath of module: seleccionar el módulo correcto

### Salida Esperada

Al ejecutar correctamente, debería ver:

```
Transaccion 1 segura: true
Transaccion 2 segura: false
Comicion 1: 50.0
Comicion 2: 250.0
```

### Conceptos Funcionales Implementados

1. **Case Classes**: Clases inmutables con pattern matching
2. **Companion Objects**: Métodos factory y utilidades
3. **Currying**: `calcularComision` acepta parámetros en dos grupos
4. **Partial Application**: `val comicion = t1.calcularComision("BBVA", 0.5) _`
5. **Higher-Order Functions**: `procesarLote` acepta funciones como parámetros
6. **Filter y Map**: Transformaciones funcionales en colecciones

### Notas Adicionales

- El Main object usa `extends App` para simplificar el punto de entrada
- Las funciones son ciudadanos de primera clase (first-class citizens)
- Se demuestra el uso de funciones parcialmente aplicadas con `_`
- El companion object implementa el patrón factory para procesamiento por lotes

### Troubleshooting

Si aún tienes problemas:

1. Verifica que el plugin de Scala esté instalado en IntelliJ
2. Asegúrate de que Scala SDK esté configurado correctamente
3. Limpia y reconstruye el proyecto (Build → Rebuild Project)
4. Verifica que no haya errores de compilación en el panel de mensajes
5. Revisa que la configuración de Run tenga el classpath correcto

### Contacto

Para preguntas o problemas, contactar al profesor o asistentes del curso
