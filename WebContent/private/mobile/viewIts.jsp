<%@page import="SGQ.construct.model.Its"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SQO</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="./resources/jquery/jquery.mobile-1.4.5.min.css">
<link rel="stylesheet" href="./resources/jquery/jquery.mobile.structure-1.4.5.css">
<script src="./resources/jquery/jquery-1.11.3.min.js"></script>
<script src="./resources/jquery/jquery.mobile-1.4.5.min.js"></script>
<%
		 
		Its i = (Its) request.getAttribute("its");
%>
</head>

<body>

  <div data-role="page" id="page1">
            <jsp:include page="headerL.jsp"/>
            <div data-role="content">
                <div class="div-widget">
                   <table data-role="table" class="ui-responsive ui-shadow">
						<thead>
							<tr>
								<th>Its-<%=i.getNum()%>  <%=i.getNome() %></th>
								
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><iframe style="WIDTH: 100%; HEIGHT: 400px" src="<%=i.getLink()%>" frameBorder=0></iframe></td>

							<p>Caso não carregue automaticamente click <a href="<%=i.getLink()%>" data-ajax="false"><b>aqui</b> </a></p>
								

							</tr>
					</table>
                   
                </div>
            </div>
            <jsp:include page="FooterL.jspf"/>
        </div>

</body>
</html>