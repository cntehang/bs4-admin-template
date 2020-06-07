package app.web.main.components

import scalatags.Text.all.{Frag, raw}

object interaction {

  val htmlStr: Frag = raw(
    """
<!-- Divider -->
<hr class="sidebar-divider">

<!-- Heading -->
<div class="sidebar-heading">
  Interaction
</div>

<!-- Nav Item - Tables -->
<li class="nav-item">
  <a class="nav-link" href="/todos">
    <i class="fas fa-fw fa-envelope"></i>
    <span>Todos</span></a>
</li>
"""
  )
}
