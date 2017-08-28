<%@page import="SGQ.construct.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	HttpSession s = request.getSession();
	Usuario usuario = (Usuario)s.getAttribute("usuario");
%>


<div data-role="header" data-position="fixed" data-theme="b">
                <h1>ENGEPAR</h1>
                <a href="#" data-rel="back"	class="ui-btn ui-corner-all ui-icon-back ui-btn-icon-notext">go	back</a>
              
                <a class="ui-btn-right" data-icon="user" href="">${sessionScope.usuario.nome}</a>
</div>

