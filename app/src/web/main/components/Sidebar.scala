package app.web.main.components

import scalatags.Text.all._

object sidebar {

  val htmlStr: Frag = ul(
    cls := "navbar-nav bg-gradient-primary sidebar sidebar-dark accordion",
    id := "accordionSidebar"
  )(
    topPart,
    interaction.htmlStr,
    interface.htmlStr,
    addons.htmlStr,
    bottomPart
  )

  lazy val topPart: Frag = raw(
    """
  <!-- Sidebar - Brand -->
  <a class="sidebar-brand d-flex align-items-center justify-content-center"
    href="@routes.Home.index">
    <div class="sidebar-brand-icon rotate-n-15">
      <i class="fas fa-laugh-wink"></i>
    </div>
    <div class="sidebar-brand-text mx-3">SB Admin <sup>2</sup></div>
  </a>

  <!-- Divider -->
  <hr class="sidebar-divider my-0">

  <!-- Nav Item - Dashboard -->
  <li class="nav-item active">
    <a class="nav-link" href="@routes.Home.index">
      <i class="fas fa-fw fa-tachometer-alt"></i>
      <span>Dashboard</span></a>
  </li>
    """
  )

  lazy val bottomPart: Frag = raw(
    """
  <!-- Divider -->
  <hr class="sidebar-divider d-none d-md-block">

  <!-- Sidebar Toggler (Sidebar) -->
  <div class="text-center d-none d-md-inline">
    <button class="rounded-circle border-0" id="sidebarToggle"></button>
  </div>
    """
  )
}
