# Proyecto de Estructura de Datos: MindLink

## Integrantes:
Miguel Vasquez,
Juan David Muñoz,
Juan Pablo Rodríguez


## Observaciones:
- Jackson Json usa un constructor vacío y luego setters para crear los objetos y enviarlos a la base de datos.
- Se cambio de lista doblmente enlazada a lista simplemente enlazada para evitar redundancia (nodo previo) en el envío de jsons.
- Para terminar un proceso: netstat -a -n -o | find "8090", taskkill /F /PID 1234
- Cuando borro un contenido debo borrar las valoraciones de ese contenido (hacer)
- Se cambio el campo topic a body en Content
### Autenticación:
- Al hacer login, a la sesíon(HttpSession) se le asgina un id único que se envía como cookie al cliente y por eso el cliente debe devolver esta cookie en las peticiones luego de loguearse, para reconocer quien es y devolver sus datos
- HttpSession guarda el id de la cookie en cada petición, por eso si mando credentials vacío(cookie vacío) el id de sesión se vuelve vacía
- Se debe poner un email válido para no demorarse mucho en el proceso de registro
### Relaciones:
Para la base de datos se relacionan los modelos a través de los ids, para evitar redundancia y evitar que no se actualicen correctamente los datos.
Sin embargo, se hacen listas propias "OwnList" (Transient para no ser escritas en la BD y evitar circularidad), que son traídas a través de las relaciones con ids y se mostrarán en el frontend.
### Respositorys:
 - Para obtener los datos de la base en Mongo, usamos una clase de servicio MongoRepositoryImpl, esta contiene los métodos:
1. save(T entity)
2. findAll()
3. findById(String id)
4. deleteById(String id)

- Métodos personalizados:
1. (Content) findByTopic(String topic)
2. (Content) findByAuthor(String author)
3. (Content) findByType(String type)

⚙️ ¿Entonces qué pasa realmente?
Supongamos esto:

Hacés login desde el navegador → se crea sesión A con ID JSESSIONID=abc123

Spring guarda el student en esa sesión A

Luego hacés una petición desde Postman a /me sin cookie:

Spring no ve ninguna cookie → crea una nueva sesión B

En la sesión B no hay student, entonces lanza la excepción

Luego recargás el navegador → este sigue enviando la cookie abc123 automáticamente

El backend encuentra la sesión A con el student

Todo funciona como antes

🔁 Así que cada cliente (navegador, Postman, otro navegador, otro dispositivo) tiene su propia sesión independiente, mientras no compartan el mismo JSESSIONID.