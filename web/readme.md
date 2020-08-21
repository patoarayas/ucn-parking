# Servicio web para Parking UCN

## Descripción
Servicio web que permite el registro de usuarios al sistema Parking UCN, también permite al usuario ver los datos existentes en el sistema. Usa Zero ICE para la conexión con el backend.

### Autores

- <a href="mailto:patricio.araya@alumnos.ucn.cl">Patricio Araya González </a>

- <a href="mailto:david.canto@alumnos.ucn.cl">David Canto Alcayaga</a>

- <a href="mailto:ariel.vejar@live.cl">Ariel Vejar Martinez</a>

### Licencia
[MIT](../LICENSE).

#### Configuración módulo web (Laravel)

Para trabajar con el módulo web se debe realizar la configuración de Laravel.
1. Renombrar el archivo .env.example a .env
2. Ejecutar el comando

  `php artisan key:generate`

  Para generar la **APP_KEY** que quedara registrada en el `.env`
3. Si es necesario descargar las dependencias.

  Se puede realizar dentro del IDE PhpStorm o ejecutando los siguientes comandos (dentro del directorio `ucn-parking/web/`):
  - `composer install`
  - `npm install` (requiere instalar node)
