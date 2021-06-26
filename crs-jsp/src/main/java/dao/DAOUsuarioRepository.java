package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conneciton.SingleConnection;
import model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection connection; // conexão do java sql

	public DAOUsuarioRepository() {
		// passa nossa classe de conexão com o banco de dados
		connection = SingleConnection.getConnection();
	}

	// nosso objeto modellogin	
	public ModelLogin gravarUsuario(ModelLogin objeto) throws Exception {
		
		if (objeto.isNovo()) { //grava um novo
		
		//nosso sql de salvar no banco de dados pelo insert sem id pq é gerado automaticamente
		String sql = "insert into model_login(login, password, nome, email) values(?, ?, ?, ?);";
		
		//prepara o sql da um apelido pra ele passa o valor de conexão com o preparo junto com o sql
		PreparedStatement preparedStatement = connection.prepareStatement(sql); 
		
		preparedStatement.setString(1, objeto.getLogin());
		preparedStatement.setString(2, objeto.getPassword()); 
		preparedStatement.setString(3, objeto.getNome());
		preparedStatement.setString(4, objeto.getEmail()); //vai gravar
        
		preparedStatement.execute(); //executar o sql	
		
		connection.commit(); //commitar no banco
		
		}else { //id é igual ao objeto id que veio da tela
			String sql = "update model_login set login=?, password=?, nome=? , email=? where id = "+objeto.getId()+";";
			
			PreparedStatement preparasql = connection.prepareStatement(sql);
			
			preparasql.setString(1, objeto.getLogin());
			preparasql.setString(2, objeto.getPassword()); 
			preparasql.setString(3, objeto.getNome());
			preparasql.setString(4, objeto.getEmail()); //vai gravar
	        
			preparasql.executeUpdate(); //executar o sql	
			
			connection.commit(); //commitar no banco
			
		}
		return this.consultaUsuario(objeto.getLogin()); //consultar o usuario pelo login e retorna pra gt
		
			

	}
	//vai retorna o model login
	public ModelLogin consultaUsuario(String  login) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		//vai fazer uma consulta pelo parametro de login passado upper ignorando as case
		String sql = "select * from model_login where upper(login) = upper('"+login+"')";
		PreparedStatement statement  = connection.prepareStatement(sql);
		
	   ResultSet resultado = statement.executeQuery(); //retorna um result set
	   
	   while (resultado.next()) {//se tem resultado
		   
		   //vai preenchr esses dados e vai retorna 
		     modelLogin.setId(resultado.getLong("id"));
		     modelLogin.setEmail(resultado.getString("email"));
		     modelLogin.setNome(resultado.getString("nome"));
		     modelLogin.setLogin(resultado.getString("login"));
		     modelLogin.setPassword(resultado.getString("password"));
	   } 
	   return modelLogin;
	}

	public boolean validarLogin(String login)  throws Exception{
		//as exist nome que demos pro contador que vai dar true ou false coluna
		String sql = "select count(1) > 0 as exist from model_login where upper(login) = upper('"+login+"')";
		
		PreparedStatement statement  = connection.prepareStatement(sql);
		
	    ResultSet resultado = statement.executeQuery(); //retorna um result set
	    
	    if (resultado.next()) { // pra ele entrar nos resultados
	    	return resultado.getBoolean("exist");
	    }
		 
		 return false;
	}
}
