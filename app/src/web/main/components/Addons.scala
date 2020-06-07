package app.web.main.components

import scalatags.Text.all.{Frag, raw}

object addons {

  val htmlStr: Frag = raw(
    """
<!-- Divider -->
<hr class="sidebar-divider" />

<!-- Heading -->
<div class="sidebar-heading">
  Addons
</div>

<!-- Nav Item - Pages Collapse Menu -->
<li class="nav-item">
  <a
    class="nav-link collapsed"
    href="#"
    data-toggle="collapse"
    data-target="#collapsePages"
    aria-expanded="true"
    aria-controls="collapsePages"
  >
    <i class="fas fa-fw fa-folder"></i>
    <span>Pages</span>
  </a>
  <div
    id="collapsePages"
    class="collapse"
    aria-labelledby="headingPages"
    data-parent="#accordionSidebar"
  >
    <div class="bg-white py-2 collapse-inner rounded">
      <h6 class="collapse-header">Login Screens:</h6>
      <a class="collapse-item" href="/addons/login">Login</a>
      <a class="collapse-item" href="/addons/register">Register</a>
      <a class="collapse-item" href="/addons/forgotPassword">Forgot Password</a>
      <div class="collapse-divider"></div>
      <h6 class="collapse-header">Other Pages:</h6>
      <a class="collapse-item" href="/addons/notFound">404 Page</a>
      <a class="collapse-item" href="/addons/blank">Blank Page</a>
    </div>
  </div>
</li>

<!-- Nav Item - Charts -->
<li class="nav-item">
  <a class="nav-link" href="/addons/charts">
    <i class="fas fa-fw fa-chart-area"></i>
    <span>Charts</span></a
  >
</li>

<!-- Nav Item - Tables -->
<li class="nav-item">
  <a class="nav-link" href="/addons/tables">
    <i class="fas fa-fw fa-table"></i>
    <span>Tables</span></a
  >
</li>
"""
  )
}
