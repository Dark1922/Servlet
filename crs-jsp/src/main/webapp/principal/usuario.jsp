<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>
<!-- inclui a parte do header melhora a formatação e manutenção de código -->

<body>
	<jsp:include page="theme-loader.jsp"></jsp:include>
	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<jsp:include page="navbar.jsp"></jsp:include>


			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<jsp:include page="main-menu.jsp"></jsp:include>


					<div class="pcoded-content">
						<!-- Page-header start -->

						<jsp:include page="page-header.jsp"></jsp:include>

						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">

										<div class="row">
											<div class="col-sm-12">
												<div class="card">
													<div class="card-header">

														<h4 style="text-align: center">Cadastro de Usuário</h4>
													</div>
													<div class="card-block">


														<form class="form-material" method="post"
															action="<%=request.getContextPath()%>/ServletUsuarioController">

															<input type="hidden" name="acao" id="acao" value="">
          												 
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="id" id="id" class="form-control"  readonly="readonly" disabled value="${modolLogin.id}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">ID:</label>
                                                            </div>
															<div class="form-group form-default">
														<input type="text" name="nome" id="nome"
																	class="form-control" required="required" value="${modelLogin.nome}"> <span
																	class="form-bar"></span> <label class="float-label">Nome</label>
															</div>
															<div class="form-group form-default">
																<input type="email" name="email" id="email"
																	class="form-control" required="required"
																	autocomplete="off" value="${modelLogin.email}"> <span class="form-bar"></span>
																<label class="float-label">Email</label>
															</div>
															<div class="form-group form-default">
																<input type="text" name="login" id="login" value="${modelLogin.login}"
																	class="form-control" required="required"> <span
																	class="form-bar"></span> <label class="float-label">Login</label>
															</div>
															<div class="form-group form-default">
																<input type="password" name="password" id="password"
																	class="form-control" required="required"
																	autocomplete="off" value="${modelLogin.password}"> <span class="form-bar"></span>
																<label class="float-label">Password</label>
															</div>

															<button class="btn btn-primary waves-effect waves-light">Novo
															</button>
															<button class="btn btn-success waves-effect waves-light">Salvar
															</button>


															<button class="btn btn-danger waves-effect waves-light">Excluir
															</button>


														</form>
													</div>
												</div>
											</div>
										</div>
									</div>

								</div>
								<!-- Page-body end -->
							</div>
							<div id="styleSelector"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>


	<jsp:include page="javascriptfile.jsp"></jsp:include>
</body>

</html>
