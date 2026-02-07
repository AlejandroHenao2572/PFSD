# Configuración de IntelliJ IDEA

Esta carpeta contiene ejemplos de configuración para IntelliJ IDEA.

## Configuración Automática

IntelliJ IDEA debería detectar automáticamente el proyecto Scala si:

1. El plugin de Scala está instalado
2. El archivo `build.sbt` está en la raíz del proyecto
3. Tienes un Scala SDK configurado

## Configuración Manual

Si IntelliJ no detecta el proyecto automáticamente:

1. File → Project Structure → Modules
2. Agregar nuevo módulo si es necesario
3. Configurar Scala SDK en Dependencies

## Estructura Recomendada

```
PFSD/
├── .idea/                    # Configuración de IntelliJ (auto-generada)
├── PrimerTercio/
│   └── Taller1/
│       └── transacion.scala
├── build.sbt
└── README.md
```

## Notas

- La carpeta `.idea` es generada automáticamente por IntelliJ
- No es necesario incluir `.idea` en el repositorio (ya está en .gitignore)
- Cada desarrollador puede tener configuraciones diferentes en su `.idea` local
