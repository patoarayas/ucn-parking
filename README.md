# Sistema de estacionamientos UCN

## Descripción
Sistema para el control de acceso a los estacionamientos de la Universidad Católica del Norte ([UCN](https://www.ucn.cl)). Parte del Curso de Proyecto de Desarrollo e Integración de Soluciones (PDIS).

## Tecnología
- Aplicación movil implementada en Java y Kotlin
- Backend en .NET Core
- Aplicación web en Laravel
- EBS utilizando Zero Ice

### Autores

- <a href="mailto:patricio.araya@alumnos.ucn.cl">Patricio Araya González </a>

- <a href="mailto:david.canto@alumnos.ucn.cl">David Canto Alcayaga</a>

- <a href="mailto:ariel.vejar@live.cl">Ariel Vejar Martinez</a>

### Licencia
[MIT](./LICENSE).

### Colaborar con el Proyecto
Este proyecto utiliza el modelo de trabajo de Git llamado Fork & Pull. Para colaborar con el proyecto debes.

##### 1. Hacer un **fork** de este repositorio.

##### 2. **Clonar** *tu* versión del repositorio en tu computador.
`git clone <url-del-repositorio>`

##### 3. Realizar cambios de manera local.
- Se recomienda un **branch** nuevo por *feature* a implementar.

    `git checkout -b <nombre-de-la-branch>` - Crea una nueva **branch**.

    `git checkout <nombre-de-la-branch>` - Permite cambiar de una **branch** a otra.

- Para mantener tu repositorio (**fork**) actualizado con respecto al proyecto debes agregar el repositorio central (*upstream*).

    `git remote add upstream https://github.com/patoarayas/ucn-parking`

    Luego cada vez que quieras actualizar tu repositorio:

    a. Primero debes hacer **fetch** para revisar si hay diferencias con el repositorio central.

    `git fetch upstream`

    b. Si hay diferencias puedes descargarlas y combinarlas en tu repo haciendo **merge**.

    `git merge upstream/master`

    **NOTA:**
    El merge se realizará en la **branch** que este seleccionada. Se recomienda volver a la **branch** *master* al momento de hacer **merge** desde el *upstream*.

    `git checkout master`

##### 4. Hacer **push** de los cambios a tu repositorio.

##### 5. Crear un **pull request** para que sea revisado e integrado al proyecto.

#### Para más información leer:
- [Sobre modelo Fork & Pull](https://reflectoring.io/github-fork-and-pull/)
- [Más sobre Forking y Pull Requests](https://guides.github.com/activities/forking/)
- [Más sobre trabajar con branches](https://guides.github.com/introduction/flow/)

#### Guía de estilo para los mensajes de commit
Los **commit** deben estar en *inglés*.

Los **commit** realizados en este proyecto deben adecuarse al formato:
```
type(scope): description

body (optional)

footer (optional)
```

Los tipos de **commit** que se pueden usar son:
```
feat,fix, build, chore, ci, docs, style, refactor, perf, test
```

Para mas detalles acerca del formato que deben tener los **commits** se puede ver la especificación a seguir [aquí](https://www.conventionalcommits.org/en/v1.0.0/)
