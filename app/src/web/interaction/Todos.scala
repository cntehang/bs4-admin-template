package app.web.interaction

import zio.Task

import scalatags.Text.all._

import app.web.main.pageLayout
import app.web.shared.infoDialog

import app.model.Todo
import app.repository.todoRepository

object todos {

  def makePage(): Task[String] = {
    val todoList: List[Todo] = todoRepository.todoList.toList
    val page = pageLayout.make(
      titleStr = "Todos",
      scripts = todoScripts,
      pageContent = content(todoList),
      heads = todoStyles
    )
    Task(page)
  }

  private val todoScripts = script(src := "/public/js/todo/todo-demo.js")

  private val todoStyles = link(
    href := "public/lib/datatables-1.10.20/dataTables.bootstrap4.css",
    rel := "stylesheet"
  )

  private def content(todoList: List[Todo]): Frag = frag(
    description,
    selectForm,
    div(cls := "card shadow mb-4")(
      tableTop,
      tableBody(todoList)
    ),
    infoDialog.makeFrag("No Selection", "Please select at least one item.")
  )

  private val description: Frag = frag(
    h1(cls := "h3 mb-2 text-gray-800")("Todos"),
    p(cls := "mb-4")(
      " We use DatatTables to display todo items. DataTables is a third party plugin, for more information, please visit the",
      a(target := "_blank", href := "https://databales.net")(
        "official DataTables documentation"
      )
    )
  )

  private val selectForm = div(cls := "input-group mb-1")(
    form(
      id := "selectedForm",
      method := "POST",
      action := "/todos"
    )(
      button(
        tpe := "submit",
        cls := "btn btn-outline-primary mr-2",
        id := "showSelectedBtn"
      )("Show Selected")
    )
  )

  private val tableTop = div(cls := "card-header py-3")(
    span(cls := "h4 mr-4 font-weight-bold text-primary")("Todo Table"),
    button(
      tpe := "button",
      cls := "btn btn-outline-info",
      id := "selectAllBtn"
    )("Select All"),
    button(
      tpe := "button",
      cls := "btn btn-outline-info",
      id := "unselectAllBtn"
    )("Unselect All")
  )

  private def tableBody(todoList: List[Todo]): Frag = div(cls := "card-body")(
    div(cls := "table-responsive")(
      table(
        cls := "table table-bordered",
        id := "dataTable",
        width := "100%"
        // cellspacing := "0"
      )(
        thead(
          tr(
            th(
              i(cls := "fas fa-check fa-sm")
            ),
            th("Id"),
            th("Name"),
            th("Is Completed")
          )
        ),
        tbody(
          for (todo <- todoList)
            yield tr(
              td(
                input(
                  tpe := "checkbox",
                  attr("data-todo-id") := todo.id.toString
                )
              ),
              td(todo.id.toString),
              td(todo.name),
              td(todo.isCompleted.toString)
            )
        )
      )
    )
  )
}
