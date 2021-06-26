package servlet;

import java.io.IOException;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

@WebServlet("/ServletUsuarioController")
public class ServletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	public ServletUsuarioController() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			String id = request.getParameter("id");// nome e id que vai pegar do formulario
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String password = request.getParameter("password");

			ModelLogin modelLogin = new ModelLogin();
			
			// id diferente de null e vazio ? se sim , passa o long com parse senão null
			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setPassword(password);

			daoUsuarioRepository.gravarUsuario(modelLogin);

			// retorna pra mesma tela e retorna os dados que tinha escrevido
			request.setAttribute("modelLogin", modelLogin); // seta os atributo mantem os dados na tela serv pra editar

            request.setAttribute("msg", "Operação realizada com sucesso");
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			

		} catch (Exception e) { //erro inesperado do sistema 
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);

		}
	}

}
