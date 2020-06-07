package app.web.main

import scalatags.Text.all._

object document {

  def make(
      titleStr: String = "Default Title",
      mainBody: Frag = "",
      scripts: Frag = "",
      heads: Frag = ""
  ): String = {

    val htmlFrag = html(lang := "en")(
      headFrag(titleStr, heads),
      bodyFrag(mainBody, scripts)
    )

    "<!DOCTYPE html>" + htmlFrag.render
  }

  private def bodyFrag(mainBody: Frag, scripts: Frag) =
    body(id := "page-top")(
      mainBody,
      mainScripts,
      scripts
    )

  private def headFrag(titleStr: String, heads: Frag) = head(
    title := titleStr,
    mainHeads,
    heads
  )

  private def mainHeads = frag(
    meta(
      charset := "UTF-8",
      name := "viewport",
      content := "width=device-width, initial-scale=1.0, shrink-to-fit=no"
    ),
    link(
      rel := "stylesheet",
      href := "public/css/sb-admin-2.css"
    ),
    link(
      href := "public/lib/fontawesome-free/css/all.min.css",
      rel := "stylesheet",
      `type` := "text/css"
    ),
    link(
      href := "https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i",
      rel := "stylesheet"
    )
  )

  private def mainScripts = frag(
    script(src := "public/lib/jquery-3.5.1/jquery.min.js"),
    script(src := "public/lib/bootstrap-4.5.0/js/bootstrap.bundle.min.js"),
    script(src := "public/lib/jquery-easing-1.4.2/jquery.easing.min.js"),
    script(src := "public/js/sb-admin-2.min.js"),
    // individual page scripts
    script(src := "public/js/todo/todo-demo.js")
  )

}
