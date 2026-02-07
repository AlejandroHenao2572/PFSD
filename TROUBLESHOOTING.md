# Guía Rápida: Solucionar ClassNotFoundException en IntelliJ IDEA

## Problema
```
Error: Could not find or load main class Main
Caused by: java.lang.ClassNotFoundException: Main
```

## Solución Paso a Paso

### 1. Verificar la Estructura del Código ✓

El archivo `PrimerTercio/Taller1/transacion.scala` debe tener `object Main extends App` al nivel raíz (ya está correcto en este proyecto).

### 2. Configurar IntelliJ IDEA

#### A. Instalar el Plugin de Scala (si no está instalado)
1. File → Settings → Plugins
2. Buscar "Scala"
3. Instalar el plugin oficial de Scala
4. Reiniciar IntelliJ

#### B. Configurar el SDK de Scala
1. File → Project Structure (Ctrl+Alt+Shift+S)
2. Project Settings → Libraries
3. Click en "+" → New Project Library → Scala SDK
4. Seleccionar versión 2.13.x (preferentemente 2.13.18)
5. Click OK

#### C. Configurar el Módulo del Proyecto
1. File → Project Structure → Modules
2. Seleccionar el módulo PFSD
3. En la pestaña "Dependencies", verificar que Scala SDK esté incluido
4. Click OK

### 3. Compilar el Proyecto

**Opción A: Compilación Completa**
- Build → Rebuild Project
- Esperar a que termine la compilación

**Opción B: Compilación Automática**
- File → Settings → Build, Execution, Deployment → Compiler
- Marcar "Build project automatically"

### 4. Crear/Verificar la Configuración de Run

#### Método 1: Ejecución Directa (Más Fácil)
1. Abrir el archivo `PrimerTercio/Taller1/transacion.scala`
2. Encontrar la línea `object Main extends App`
3. Click en el ícono verde ▶ que aparece al lado izquierdo de esa línea
4. Seleccionar "Run 'Main'"

#### Método 2: Configuración Manual
1. Run → Edit Configurations
2. Click en "+" → Application (o Scala Application si está disponible)
3. Configurar:
   - **Name**: Main
   - **Main class**: Main
   - **Working directory**: ruta de la carpeta PFSD
   - **Use classpath of module**: seleccionar PFSD
   - **JRE**: usar la JRE del proyecto
4. Click OK
5. Run → Run 'Main'

### 5. Soluciones Adicionales si el Error Persiste

#### A. Invalidar Cache de IntelliJ
1. File → Invalidate Caches
2. Marcar "Invalidate and Restart"
3. Esperar a que IntelliJ reinicie y reindexe el proyecto

#### B. Limpiar Archivos Compilados
1. Build → Clean Project
2. Build → Rebuild Project

#### C. Sincronizar Proyecto con SBT (si usa SBT)
1. Abrir la ventana "sbt" (View → Tool Windows → sbt)
2. Click en el botón "Reload All sbt Projects" (ícono de flechas circulares)

#### D. Verificar el Classpath
1. Run → Edit Configurations
2. Seleccionar la configuración "Main"
3. En la pestaña "Configuration", verificar que "Use classpath of module" esté configurado
4. Click en "Modify options" → "Add VM options"
5. Agregar: `-Dfile.encoding=UTF-8`

### 6. Verificar la Salida Esperada

Al ejecutar correctamente, deberías ver en la consola:
```
Transaccion 1 segura: true
Transaccion 2 segura: false
Comicion 1: 50.0
Comicion 2: 250.0
```

## Problemas Comunes y Soluciones

### Problema: "Scala SDK is not configured for module"
**Solución**: 
- File → Project Structure → Modules
- Seleccionar el módulo → Dependencies tab
- Click "+" → Library → Scala SDK
- Seleccionar versión 2.13.x

### Problema: "Cannot resolve symbol"
**Solución**:
- File → Invalidate Caches → Invalidate and Restart
- Verificar que el plugin de Scala esté habilitado

### Problema: JDK no compatible
**Solución**:
- Usar JDK 11 o superior (recomendado JDK 21)
- File → Project Structure → Project → Project SDK

### Problema: "Could not find or load main class" persiste
**Solución**:
1. Cerrar IntelliJ
2. Eliminar la carpeta `.idea` en la raíz del proyecto
3. Reabrir IntelliJ y configurar nuevamente

## Alternativa: Compilación Manual

Si IntelliJ sigue dando problemas, puedes compilar y ejecutar desde la terminal:

```bash
# En Windows (Git Bash o PowerShell)
cd PrimerTercio/Taller1
scalac transacion.scala
scala Main

# En Linux/Mac
cd PrimerTercio/Taller1
scalac transacion.scala
scala Main
```

## Recursos Adicionales

- [Documentación oficial de Scala](https://docs.scala-lang.org/)
- [Plugin de Scala para IntelliJ](https://plugins.jetbrains.com/plugin/1347-scala)
- [IntelliJ IDEA Scala Guide](https://www.jetbrains.com/help/idea/scala.html)

## Contacto

Si el problema persiste después de seguir todos estos pasos, contactar al profesor o asistentes del curso con:
1. Captura de pantalla del error completo
2. Versión de IntelliJ IDEA (Help → About)
3. Versión de Scala SDK configurada
4. Versión de JDK (java -version)
