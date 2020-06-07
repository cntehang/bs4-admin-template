package app.web.addons

import zio.Task
import scalatags.Text.all.raw

import app.web.main.pageLayout

object blankPage {
  def makePage(): Task[String] = {
    val page = pageLayout.make(titleStr = "Blank", pageContent = content)
    Task(page)
  }

  private val content = raw(
    """<h1 class="h3 mb-4 text-gray-800">Blank Page<h1>"""
  )
}
