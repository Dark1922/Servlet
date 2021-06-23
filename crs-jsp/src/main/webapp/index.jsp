<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
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
	background: #FDF6F6;
	color: #FA2D2D;
	text-align: center;
	border-radius: 3px;
	margin-top: 10px;
	padding: 10px;
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

		<div class="col-md-6">
			<label class="form-label">Login</label> <input type="text"
				name="login" placeholder="login" class="form-control"
				required="required">
				
						<div id="invalidCheckLogin" class="invalid-feedback">
        Digite seu login.
      </div>
		</div>

		<div class="col-md-6">
			<label class="form-label">senha</label> <input type="password"
				name="password" placeholder="password" class="form-control"
				required="required">
				
				<div id="invalidCheckPassword" class="invalid-feedback">
        Digite sua senha.
      </div>
		</div>

		<div class="col-md-12">
			<button type="submit" value="acessar" class="btn btn-primary">Log
				in</button>
		</div>

	</form>

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

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>