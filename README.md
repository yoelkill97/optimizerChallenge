# Optimizer Challenge

## Descripción
`Optimizer Challenge` es una aplicación de analisis y gestión. Permite registrar, analizar y generar recomendaciones basadas en los datos ingresados, como frecuencia de uso, consumo de CPU y RAM, y estado de soporte de las aplicaciones.

### Características
- **Registro de Aplicaciones**: Permite registrar aplicaciones con detalles como nombre, versión, frecuencia de uso, consumo de recursos y estado de soporte.
- **Análisis de Optimización**: Detecta aplicaciones obsoletas, subutilizadas o redundantes para optimizar recursos.
- **Recomendaciones**: Genera informes que sugieren acciones basadas en el análisis de los datos ingresados.

## Herramientas Utilizadas
- **Kotlin**: Lenguaje de desarrollo principal para la implementación de la lógica de negocio y las pantallas en Android.
- **Jetpack Compose**: Biblioteca de UI para construir interfaces de usuario reactivas y modernas en Android.
- **Room Database**: Librería para manejo de base de datos local en Android.
- **Coroutines**: Para manejar operaciones asíncronas y gestión de tareas en segundo plano.
- **StateFlow y LiveData**: Para observar y reaccionar a cambios en los datos en tiempo real.
- **Hilt/Dagger**: Inyección de dependencias para una arquitectura más escalable y mantenible.

## Requisitos Previos
- Android Studio `Arctic Fox` o superior.
- Kotlin `1.5.0` o superior.
- Dependencias de Jetpack Compose y Room correctamente configuradas en el archivo `build.gradle`.

## Instalación y Configuración
1. Clonar el repositorio:

    ```bash
    git clone https://github.com/yoelkill97/optimizerChallenge.git
    ```
2. Abrir el proyecto en Android Studio.
3. Sincronizar las dependencias desde el archivo `build.gradle`.
4. Ejecutar el proyecto en un emulador o dispositivo físico.

## Estructura del Proyecto
- `data`: Contiene las clases de base de datos (`Room`) y repositorios.
- `ui`: Implementa las pantallas (`RegisterScreen`, `ApplicationsScreen`) utilizando Jetpack Compose.
- `viewmodel`: Contiene la lógica de negocio y estado de la UI mediante `ViewModel` y `StateFlow`.
- `Optimizer`: Contiene la lógica para analizar las aplicaciones y generar recomendaciones.

## Uso
1. **Registrar Aplicación**: Navegar a la pantalla de registro y hacer clic en el botón `Registrar`.
2. **Listado de Aplicaciónes**: Navegar a la pantalla de listado mostrara las aplicaciones registradas.
3. **Visualizar Recomendaciones**: Después de registrar las aplicaciones, ir a la pantalla de análisis para ver las recomendaciones generadas.
4. **Optimización**: Aplicar las recomendaciones sugeridas para optimizar los recursos de las aplicaciones.


## Autor
- **Yoelkill97** - Mobile Developer.
"""
