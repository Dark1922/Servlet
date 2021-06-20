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
 <form action="ServletLogin" method="post">
<table>
<tr>
<td><label>Login</label></td>
<td> 
 <input type="text" name="login" placeholder="login" >
</td>
</tr>

<tr>
<td><label>senha</label></td>
<td>
 <input type="password" name="password" placeholder="password" >
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