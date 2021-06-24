package servlet;

import java.io.IOException;

import dao.DAOLoginRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

//o chamado controller que são as servlet ou ServletLoginController
@WebServlet(urlPatterns = {"/principal/ServletLogin", "/ServletLogin"})//mapeamento da url que vem da tela
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();
	

	public ServletLogin() {

	}
  //recebe os dados pela url e pelos parametros
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}
   //recebe os dados enviados por  um formulário
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String url = request.getParameter("url"); //pra definir a url
		
		try {
		if (login != null && !login.isEmpty() && password != null && !password.isEmpty()) {
			
			ModelLogin modelLogin =  new ModelLogin();
			modelLogin.setLogin(login);
			modelLogin.setPassword(password);
			
			//passa o dao login aqui pegando os dados do password e login do banco de dados
			if (daoLoginRepository.validarAutenticacao(modelLogin)) {/*Deu certo o  login*/
				
				request.getSession().setAttribute("usuario", modelLogin.getLogin());/*Coloca o user na sessao*/
				
				if (url == null || url.equals("null")) { //se a url vir nula
					url = "principal/principal.jsp";//vai por o link principal do sistema página inicial
				}
				RequestDispatcher redirecionar = request.getRequestDispatcher(url);
				redirecionar.forward(request, response); //dispacha pra url se der certo a session
				
			}else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("msg", "Informe o login e senha corretamente!");
				redirecionar.forward(request, response); //caso escreva errado
			}
			
		}else { //caso escreva nada no login
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Informe o login e senha corretamente!");
			redirecionar.forward(request, response);
		}
		
		}catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}
	
	}

