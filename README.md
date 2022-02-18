# Real Estate
## _Proyecto 2ª DAM (Acceso a datos / Programación de servicios y procesos / Diseño de interfaces / Programación multimedia y dispositivos móviles)_

[![N|Solid](logo.png)](https://triana.salesianos.edu/colegio//nsolid)



Este proyecto ha sido desarrollado por Francisco Gallego Gordillo
Programas empleados:

- API: IntelliJ idea
- Postman

>## Funcionalidad

  Crear una aplicación para gestionar publicaciones y de imagenes y videos así como su visualización correspondiente mediante los usuarios propietarios.

>## Entidades
  Contamos con 2 entidades que son:
  - Usuarios
  - Post (publicaciones)

>## Instrucciones de arranque
  Para ejecutar esta aplicación tras clonar el repositorio, debes ejecutar en la consola de tu IDE, "spring-boot:run", con la configuración de Maven.

>## Instrucciones para probar el programa
  Para probar este proyecto haremos uso de Postman.

  Una vez ejecutada la aplicación desde el IDE, podremos empezar creando usuarios.

  Para el register existen 3 peticiones:

  - Register User Publico. Crea un usuario con un perfil público. Utiliza como cuerpo el archivo "Perfil1.png" y el json "UserPublico.json".
  - Register User Privado. Crea un usuario con un perfil privado. Utiliza como cuerpo el archivo "Perfil1.png" y el json "UserPrivado.json".
  - Register User Fail. Intenta crear un usuario sin email para comprobar la validación de este.

  Para el login del usuario sólo tenemos una petición:

  - Login. (IMPORTANTE REESCATAR EL TOKEN PARA INCLUIRLO EN CADA PETICIÓN QUE QUERAMOS HACER)

  Para el edit usaremos la siguiente petición:

  - Edit User. Utiliza como cuerpo el archivo "UserEdit.png" y el json "UserEdit.json".

  Las peticiones restantes son peticiones GET en las cuales no se necesita proporcionar ninguna objeción extra.


  Seguiremos con las peticiones para gestionar POSTS.

  Cosas para tener en cuenta a la hora de probar las publicaciones:

  - Para realizar TODAS las peticiones tendremos que añadirle el token en el apartado "Authorization".
  - La petición Find All Post devuelve todas las peticiones PUBLICAS.
  - La petición Edit Post solo puede editar una publicación del usuario logueado.
  - La petición Delete Post solo puede eliminar una publicación del usuario logueado.
  - Las peticiones Find All Post By User y FindOne Public devuelven publicaciones públicas.


  Para crear una nueva publicación también tenemos 3 peticiones:

  - Add Post. Crea una publicación pública. Utiliza como cuerpo el archivo "Post1.jpg" y el json "PostJson.json".
  - Add Private Post. Crea una publicación privada. Utiliza como cuerpo el archivo "Post1.jpg" y el json "PostPrivada.json".
  - Add Post Video. Crea una publicación pública subiendo como archivo un vídeo. Utiliza como cuerpo el archivo "Video1.mp4" y el json "PostVideo.json".


  Para comprobar la foto de perfil de un usuario o el archivo de la publicación que hemos creado, tendremos que acceder a la dirección que nos devuelve el atributo correspondiente y realizar la petición sin olvidar añadir el token del usuario logeado.
