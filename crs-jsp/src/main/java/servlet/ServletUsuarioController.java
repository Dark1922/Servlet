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
			throws ServletException, IOException {//método get ultilizado pra buscar e deletar mais
		try {
		//vamos pegar da requisição o get parameter
		String acao = request.getParameter("acao");
		
		//diferente de null e vazio e ignorar a case sendo deletar
		if (!acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
			
			String idUser = request.getParameter("id"); //id do usuario
			
			daoUsuarioRepository.deletarUser(idUser); //método de delete criado
			
			request.setAttribute("ok", "Excluido com sucesso!"); //mensagem de delete
			//redireciona pro usuario dps que exclui e a requisição e resposta
			
		}
		//deletando ou não retorna pra mesma página
		request.getRequestDispatcher("principal/usuario.jsp").forward(request, response); 
		
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			
		}
	}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {//doPost mais ultilizado para criar e atualizar
		try {
			
			String msg = "Operação realizada com sucesso";
			
			String id = request.getParameter("id");// nome e id que vai pegar do formulario
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String password = request.getParameter("password");

			ModelLogin modelLogin = new ModelLogin();
			
			// id diferente de null e vazio ? se sim , passa o long com parse senão null
			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : 0);
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setPassword(password);
			
			if (daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == 0) {
				msg = "Já existe usuário com o mesmo login, informe outro login.";
			}else {
				if (modelLogin.isNovo()) {
					msg = "Gravado com sucesso!"; 
				}else {
					msg= "Atualizado com sucesso!";
				}
			
				//vai gravar e consultar o usuario na tela
		    modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin);
			}
				
			
			
			

			// retorna pra mesma tela e retorna os dados que tinha escrevido
			request.setAttribute("modelLogin", modelLogin); // seta os atributo mantem os dados na tela serv pra editar

            request.setAttribute("ok", msg);
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			

		} catch (Exception e) { //erro inesperado do sistema 
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);

		}
	}

}
