package app.model

/**
  * Presentation object returned in Json format.
  * Note that it's a good practice to keep the presentation DTO,
  * which are used for reads, distinct from the form processing DTO,
  * which are used for writes.
  *
  * Optionally, you may want to name it with a `Vo` postfix
  * to show that it is an view object.
  */
case class Todo(id: Long, name: String, isCompleted: Boolean)
