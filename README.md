# Proyecto de Estructura de Datos: MindLink

## Integrantes:
Miguel Vasquez,
Juan David Muñoz,
Juan Pablo Rodríguez


## Observaciones:
### Relaciones:
Para la base de datos se relacionan los modelos a través de los ids, para evitar redundancia y que no se actualizen correctamente los datos.
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
