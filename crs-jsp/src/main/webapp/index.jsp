<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Curso Jsp</title>
</head>
<h1>Bem vindo ao curso jsp</h1>
<body>
<h3 style="color: red;">${msg}</h3>
 <form action="ServletLogin" method="post"><!-- qnd a tag é value tem que por o = pra imprimir o valor que está dentro -->
 <input type="hidden" value="<%= request.getParameter("url") %>" name="url"> <!-- hidden fica escondido -->
<table>
<tr>
<td><label>Login</label></td>
<td> 
 <input type="text" name="login" placeholder="login" required="required" >
</td>
</tr>

<tr>
<td><label>senha</label></td>
<td>
 <input type="password" name="password" placeholder="password" required="required" >
</td>
</tr>

<tr>
<td/>
<td>
 <input type="submit" value="login">
</td>
</tr>

</table>
 </form>
</body>
</html>