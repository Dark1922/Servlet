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

//Intercepta toda as requisições que vierem do projeto ou mapeamento
@WebFilter(urlPatterns = { "/principal/*" }) // tudo que vier de requisição da pasta principal vai ser
public class FilterAutenticacao implements Filter {// requisitado ou filtrado pra fazer validação

	private static Connection connection; // conexão do sql

	public FilterAutenticacao() {
	}

	// encerra os processos quando o servidor é parado
	// exemplo mataria os processos de conexão com o banco
	public void destroy() {
		try { // se der algum problema na conexão vai fechar a cnx
			connection.close();

		} catch (SQLException e) {

			e.printStackTrace();// vai dar uma exceçaõ
		}
	}

	// intercepta todas requisições e as respostas no sistema
	// tudo que fazemos no sistema vai passar por aqui
	// exemplo validação de autenticação
	// dar commit e rollback de transações do banco
	// valdiar e fazer redirecionamentos de páginas especificos
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {

			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();

			String usuarioLogado = (String) session.getAttribute("usuario");// atributo usuario servlet session

			String urlParaAutenticar = req.getServletPath(); // url que está sendo acessada

			// validar se está logado senão redireciona para tela de login
			// se o usuario é igual a nul ou
			// e tentar acessar uma url diferente da autenticação sendo que não tá logado
			// ser acessada / se o principal / n conter o servletlogin
			if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) // não está
																											// logado
			{

				// request do parametro doFilter
				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "por favor realize o login!");
				redireciona.forward(req, response); // comando de redirecionamento
				return; // para a execuçaõ e redireciona para o login

			} else {// se não vai deixar redirecionar se logou
				chain.doFilter(request, response);
			}
		connection.commit(); // deu tudo certo, ent comita as alterações no banco de dados

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
	// exemplo inicia conexão com o banco
	public void init(FilterConfig fConfig) throws ServletException {
		// conexão sql com nossa conxeão do banco de dados retornando a conexão
		connection = SingleConnection.getConnection();
	}// inicia conexão qnd subir o projeot

}
