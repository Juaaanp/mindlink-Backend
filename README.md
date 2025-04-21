# Proyecto de Estructura de Datos: MindLink

## Integrantes:
Miguel Vasquez,
Juan David Muñoz,
Juan Pablo Rodríguez


## Observaciones:
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
