<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
<title>Curso Jsp</title>

<style type="text/css">
form {
	position: absolute;
	top: 40%;
	left: 32%;
	right: 32%;
}

h5 {
	position: absolute;
	top: 18%;
	left: 32%;
	right: 32%;
	text-align: center;
}

h4 {
	position: absolute;
	top: 27%;
	left: 32%;
	right: 32%;
}

button {
	width: 100%;
	margin-top: 3%;
}

.msg {
	widht: 100%;
	height: 54px;
	/*background: #FDF6F6;*/
	color: #FA2D2D;
	text-align: center;
	border-radius: 3px;
	margin-top: 10px;
	padding: 10px;
}

input {
	widht: 100%;
}
</style>
</head>
<h5>Bem vindo ao curso jsp</h5>
<body>
	<h4 class="msg">${msg}</h4>

	<form action="ServletLogin" method="post"
		class="row g-3 needs-validation" novalidate>
		<!-- qnd a tag é value tem que por o = pra imprimir o valor que está dentro -->

		<input type="hidden" value="<%=request.getParameter("url")%>"
			name="url">
		<!-- hidden fica escondido -->

		<div class="form-group">
			<label class="form-label" for="login">Login</label> <input
				class="form-control" type="text" name="login" id="login"
				placeholder="login" required>

			<div id="invalidCheckLogin" class="invalid-feedback">Digite seu
				login.</div>

		</div>

		<div class="form-group">

			<label class="form-label" for="password">senha</label> <input
				class="form-control" type="password" name="password" id="password"
				placeholder="password" required>

			<div id="invalidCheckPassword" class="invalid-feedback">Digite
				sua senha.</div>

		</div>

		<div class="col-md-12">
			<button type="submit" value="acessar" class="btn btn-primary">Log
				in</button>
		</div>

	</form>
	
		<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
		crossorigin="anonymous"></script>

	<script type="text/javascript">
		// Example starter JavaScript for disabling form submissions if there are invalid fields script de validação do bootstrap
		(function() {
			'use strict'

			// Fetch all the forms we want to apply custom Bootstrap validation styles to
			var forms = document.querySelectorAll('.needs-validation')

			// Loop over them and prevent submission
			Array.prototype.slice.call(forms).forEach(function(form) {
				form.addEventListener('submit', function(event) {
					if (!form.checkValidity()) {
						event.preventDefault()
						event.stopPropagation()
					}

					form.classList.add('was-validated')
				}, false)
			})
		})()
	</script>


</body>
</html>