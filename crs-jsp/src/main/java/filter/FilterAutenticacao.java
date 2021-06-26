package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import conneciton.SingleConnection;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

//Intercepta toda as requisi��es que vierem do projeto ou mapeamento
@WebFilter(urlPatterns = { "/principal/*" }) // tudo que vier de requisi��o da pasta principal vai ser
public class FilterAutenticacao implements Filter {// requisitado ou filtrado pra fazer valida��o

	private static Connection connection; // conex�o do sql

	public FilterAutenticacao() {
	}

	// encerra os processos quando o servidor � parado
	// exemplo mataria os processos de conex�o com o banco
	public void destroy() {
		try { // se der algum problema na conex�o vai fechar a cnx
			connection.close();

		} catch (SQLException e) {

			e.printStackTrace();// vai dar uma exce�a�
		}
	}

	// intercepta todas requisi��es e as respostas no sistema
	// tudo que fazemos no sistema vai passar por aqui
	// exemplo valida��o de autentica��o
	// dar commit e rollback de transa��es do banco
	// valdiar e fazer redirecionamentos de p�ginas especificos
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {

			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();

			String usuarioLogado = (String) session.getAttribute("usuario");// atributo usuario servlet session

			String urlParaAutenticar = req.getServletPath(); // url que est� sendo acessada

			// validar se est� logado sen�o redireciona para tela de login
			// se o usuario � igual a nul ou
			// e tentar acessar uma url diferente da autentica��o sendo que n�o t� logado
			// ser acessada / se o principal / n conter o servletlogin
			if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) // n�o est�
																											// logado
			{

				// request do parametro doFilter
				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "por favor realize o login!");
				redireciona.forward(req, response); // comando de redirecionamento
				return; // para a execu�a� e redireciona para o login

			} else {// se n�o vai deixar redirecionar se logou
				chain.doFilter(request, response);
			}
			connection.commit(); // deu tudo certo, ent comita as altera��es no banco de dados

		} catch (Exception e) {

			e.printStackTrace();
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);

			try {
				connection.rollback();
				
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
	}

	// inicia os processos ou recursos quando o servidor sobe o projeto
	// exemplo inicia conex�o com o banco
	public void init(FilterConfig fConfig) throws ServletException {
		// conex�o sql com nossa conxe�o do banco de dados retornando a conex�o
		connection = SingleConnection.getConnection();
	}// inicia conex�o qnd subir o projeot

}
