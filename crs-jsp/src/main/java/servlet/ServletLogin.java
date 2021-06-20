package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

//o chamado controller que são as servlet ou ServletLoginController
@WebServlet("/ServletLogin")//mapeamento da url que vem da tela
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletLogin() {

	}
  //recebe os dados pela url e pelos parametros
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
   //recebe os dados enviados por  um formulário
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//pega os parametros da tela
		String login = request.getParameter("login");
		String password = request.getParameter("password");
       
		//se login e password é diferente de vazio e nulo uma condição
		if(login != null && !login.isEmpty() && password != null && password.isEmpty()) {
		
		ModelLogin modelLogin = new ModelLogin();//objeto de classe instaciado
		modelLogin.setLogin(login);//vai passar os dados que foram
		modelLogin.setPassword(password);//recebios acima por parametro
		//se a condição foi feita vai por no objeto e continuar a auth
		
		}else {//vai dispachar pra mesma tela se n informar login e a senha
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Informe o Login e senha corretamente!");
			redirecionar.forward(request, response);
		}
	}

}
