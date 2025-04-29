# Proyecto de Estructura de Datos: MindLink

## Integrantes:
Miguel Vasquez,
Juan David Muñoz,
Juan Pablo Rodríguez


## Observaciones:
- Jackson Json usa un constructor vacío y luego setters para crear los objetos y enviarlos a la base de datos.
- Se cambio de lista doblmente enlazada a lista simplemente enlazada para evitar redundancia (nodo previo) en el envío de jsons.
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

Convertir la lista propia a normal antes de enviarlo al cliente:

@GetMapping("/students/{id}/with-groups")
public ResponseEntity<?> getStudentWithGroups(@PathVariable String id) {
    Student estudiante = studentService.cargarEstudianteConGrupos(id);

    // Transformar la lista personalizada en una lista normal para JSON
    List<StudyGroup> grupos = estudiante.getStudyGroupsOwnList().toList(); // <--- deberías implementar este método en DoublyLinkedList

    // Puedes devolver el estudiante completo, pero reemplazando esa propiedad
    Map<String, Object> respuesta = new HashMap<>();
    respuesta.put("id", estudiante.getId());
    respuesta.put("name", estudiante.getName());
    respuesta.put("email", estudiante.getEmail());
    respuesta.put("interests", estudiante.getInterests());
    respuesta.put("studyGroupsIdList", estudiante.getStudyGroupsIdList());
    respuesta.put("studyGroups", grupos); // <--- lista simple que el frontend puede entender

    return ResponseEntity.ok(respuesta);
}

En DoublyLinkedList:

public List<T> toList() {
    List<T> list = new ArrayList<>();
    Node<T> current = this.head;
    while (current != null) {
        list.add(current.data);
        current = current.next;
    }
    return list;
}