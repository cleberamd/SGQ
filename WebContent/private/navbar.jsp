 <header Style="margin-bottom: 10px">
 <!-- Fixed navbar -->
    <nav class="navbar navbar-inverse navbar-fixed-top" >
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Engepar Engenharia</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a href="RISController?action=getRIS">RIS</a></li>
				<li><a href="UsuarioController?action=usuarios">USUARIO</a></li>
				
				<li><a href="ObraController?action=listaObra">OBRA</a></li>
				<li><a href="ItsController?action=listaIts">ITS</a></li>
				<li><a href="ReportServlet?action=relatorio">RELATORIO</a></li>
				<li><a href="UsuarioController?action=mobile">MOBILE</a></li>
              </ul>
           
          <ul class="nav navbar-nav navbar-right">
           
            <li class="active"><a href="UsuarioController?action=verUsuario&usuarioId=${sessionScope.usuario.id}">${sessionScope.usuario.nome} </a></li>
          <li><a href="UsuarioController?action=sair*">Sair</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

	</header>
	<div style="padding-bottom: 5%"></div>


        