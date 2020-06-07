package app.web.main

import scalatags.Text.all._
import app.web.main.components._

object pageLayout {

  def make(
      titleStr: String = "Admin Dashboard",
      pageContent: Frag = "",
      scripts: Frag = "",
      heads: Frag = ""
  ): String = {
    val layoutBody = layoutContent(pageContent)
    document.make(titleStr, layoutBody, scripts, heads)
  }

  private def layoutContent(content: Frag) = frag(
    div(id := "wrapper")(
      sidebar.htmlStr,
      div(id := "content-wrapper", cls := "d-flex flex-column")(
        div(id := "content")(
          topbar.htmlStr,
          logoutModal.htmlStr,
          div(cls := "container-fluid")(content),
          footer
        )
      )
    ),
    scrollToTop
  )

  val footer = raw("""
  <footer class="sticky-footer bg-white">
    <div class="container my-auto">
      <div class="copyright text-center my-auto">
        <span>Copyright &copy; Your Website 2020</span>
      </div>
    </div>
  </footer>
    """)

  val scrollToTop = raw("""
<a class="scroll-to-top rounded" href="#page-top">
  <i class="fas fa-angle-up"></i>
</a>  
  """)

}
