
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SGQ</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet"
	href="./resources/jquery/jquery.mobile-1.4.5.min.css">
<script src="./resources/jquery/jquery-1.11.3.min.js"></script>
<script src="./resources/jquery/jquery.mobile-1.4.5.min.js"></script>

</head>

<body>

  <div data-role="page" id="page1">
            <jsp:include page="header.jspf"/>
            <div data-role="content">
                <div class="div-widget">
                    <form action="UsuarioController?action=loginM" method="POST" >
                        <div data-role="fieldcontain">
                            <label for="nome">Usuario</label>
                            <input type="text" name="nome" placeholder="Usuario" data-mini="true"
                            />
                        </div>
                        <div data-role="fieldcontain">
                            <label for="senha">Senha</label>
                            <input type="password" name="senha" placeholder="Senha" data-mini="true"
                            />
                        </div>
                         <button data-role="button" data-transition="slideup" type="submit">Entrar</button>
                    </form>
                </div>
            </div>
            <jsp:include page="Footer.jspf"/>
        </div>

</body>
</html>