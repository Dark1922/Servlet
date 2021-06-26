package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conneciton.SingleConnection;
import model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection connection; // conex�o do java sql

	public DAOUsuarioRepository() {
		// passa nossa classe de conex�o com o banco de dados
		connection = SingleConnection.getConnection();
	}

	// nosso objeto modellogin	
	public ModelLogin gravarUsuario(ModelLogin objeto) throws Exception {
		
		//nosso sql de salvar no banco de dados pelo insert sem id pq � gerado automaticamente
		String sql = "insert into model_login(login, password, nome, email) values(?, ?, ?, ?);";
		
		//prepara o sql da um apelido pra ele passa o valor de conex�o com o preparo junto com o sql
		PreparedStatement preparedStatement = connection.prepareStatement(sql); 
		
		preparedStatement.setString(1, objeto.getLogin());
		preparedStatement.setString(2, objeto.getPassword()); 
		preparedStatement.setString(3, objeto.getNome());
		preparedStatement.setString(4, objeto.getEmail()); //vai gravar
        
		preparedStatement.execute(); //executar o sql	
		
		connection.commit(); //commitar no banco
		
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

}
