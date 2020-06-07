package app.web.interaction

import zio.Task

import scalatags.Text.all._

import app.web.main.pageLayout
import app.web.shared.infoDialog

object todos {

  def makePage(): Task[String] = {
    val page = pageLayout.make(
      scripts = todoScripts,
      pageContent = content,
      heads = todoStyles
    )
    Task(page)
  }

  private val todoScripts = script(src := "public/js/todo/todo-demo.js")
  private val todoStyles = link(
    href := "public/lib/datatables-1.10.20/dataTables.bootstrap4.css",
    rel := "stylesheet"
  )

  private val content = frag(
    raw(
      """
<h1 class="h3 mb-2 text-gray-800">Todos</h1>
<p class="mb-4"> We use DatatTables to display todo items.
  DataTables is a third party plugin, for more information, please visit the
  <a target="_blank" href="https://datatables.net">
    official DataTables documentation
  </a>.
</p>

<div class="input-group mb-1">
  <form id="selectedForm" method="POST" action="@TodoController.selected">
    @helper.CSRF.formField
    <button type="submit" class="btn btn-outline-primary mr-2" id="showSelectedBtn">
      Show Selected
    </button>
  </form>

  <a href="/todos" role="button" class="btn btn-outline-primary ">
    Show All
  </a>
</div>

<div class="card shadow mb-4">
  <div class="card-header py-3">
    <span class="h4 mr-4 font-weight-bold text-primary">Todo Table</span>
    <button type="button" class="btn btn-outline-info" id="selectAllBtn">Select All</button>
    <button type="button" class="btn btn-outline-info" id="unselectAllBtn">Unselect All</button>
  </div>
  <div class="card-body">
    <div class="table-responsive">
      <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
        <thead>
          <tr>
            <th>
              <i class="fas fa-check fa-sm"></i>
            </th>
            <th>Id</th>
            <th>Name</th>
            <th>Is Complete</th>
          </tr>
        </thead>
        <tbody>
          @for(todo <- todos) { 
            <tr>
              <td>
                <input type="checkbox" data-todo-id="@todo.id">
              </td>
              <td>@todo.id</td>
              <td>@todo.name</td>
              <td>@todo.isComplete</td>
            </tr>
            }
        </tbody>
      </table>
    </div>
  </div>
</div>
    """
    ),
    infoDialog.makeFrag("No Selection", "Please select at least one item.")
  )
}
