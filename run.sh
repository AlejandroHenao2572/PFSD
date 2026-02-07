#!/bin/bash
# Script para compilar y ejecutar el proyecto PFSD
# Usage: ./run.sh [compile|run|clean]

set -e

PROJECT_ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
SOURCE_DIR="$PROJECT_ROOT/PrimerTercio/Taller1"
SOURCE_FILE="$SOURCE_DIR/transacion.scala"

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo_success() {
    echo -e "${GREEN}✓ $1${NC}"
}

echo_error() {
    echo -e "${RED}✗ $1${NC}"
}

echo_info() {
    echo -e "${YELLOW}→ $1${NC}"
}

check_scala() {
    if ! command -v scalac &> /dev/null; then
        echo_error "Scala compiler (scalac) no encontrado"
        echo_info "Por favor, instala Scala 2.13.x"
        echo_info "Visita: https://www.scala-lang.org/download/"
        exit 1
    fi
    
    SCALA_VERSION=$(scalac -version 2>&1 | grep -oP '(?<=version )\d+\.\d+' || echo "unknown")
    echo_info "Scala version: $SCALA_VERSION"
}

compile_project() {
    echo_info "Compilando proyecto..."
    cd "$SOURCE_DIR"
    
    if scalac transacion.scala; then
        echo_success "Compilación exitosa"
    else
        echo_error "Error durante la compilación"
        exit 1
    fi
}

run_project() {
    echo_info "Ejecutando Main..."
    cd "$SOURCE_DIR"
    
    if [ ! -f "Main.class" ]; then
        echo_error "Proyecto no compilado. Ejecuta: ./run.sh compile"
        exit 1
    fi
    
    echo ""
    echo "=== Salida del programa ==="
    scala Main
    echo "=========================="
    echo ""
    echo_success "Ejecución completada"
}

clean_project() {
    echo_info "Limpiando archivos compilados..."
    cd "$SOURCE_DIR"
    rm -f *.class
    echo_success "Limpieza completada"
}

# Main script logic
case "${1:-help}" in
    compile)
        check_scala
        compile_project
        ;;
    run)
        check_scala
        run_project
        ;;
    clean)
        clean_project
        ;;
    all)
        check_scala
        clean_project
        compile_project
        run_project
        ;;
    help|*)
        echo "PFSD - Sistema de Transacciones Bancarias"
        echo ""
        echo "Uso: ./run.sh [comando]"
        echo ""
        echo "Comandos disponibles:"
        echo "  compile  - Compila el código Scala"
        echo "  run      - Ejecuta la aplicación Main"
        echo "  clean    - Elimina archivos compilados"
        echo "  all      - Limpia, compila y ejecuta (recomendado)"
        echo "  help     - Muestra esta ayuda"
        echo ""
        echo "Ejemplo rápido:"
        echo "  ./run.sh all"
        ;;
esac
