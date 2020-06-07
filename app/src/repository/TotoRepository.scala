package app.repository

import scala.collection.mutable.ArrayBuffer

import app.model.Todo

object todoRepository {

  // For simplicity, all data are defined here
  // In production, they are managed in a service
  val todoList: ArrayBuffer[Todo] = ArrayBuffer(
    Todo(1, "Todo 1", true),
    Todo(2, "Todo 2", true),
    Todo(3, "Todo 3", false),
    Todo(
      4,
      "Todo 4 Note that it's a good practice to keep the presentation DTO, Note that it's a good practice to keep the presentation DTO.",
      false
    )
  )
}
