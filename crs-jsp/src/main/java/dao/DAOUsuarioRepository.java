package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import conneciton.SingleConnection;
import model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection connection; // conex�o do java sql

	public DAOUsuarioRepository() {
		// passa nossa classe de conex�o com o banco de dados
		connection = SingleConnection.getConnection();
	}

	// nosso objeto modellogin	
	public void gravarUsuario(ModelLogin objeto) throws Exception {
		
		try {
		//nosso sql de salvar no banco de dados pelo insert sem id pq � gerado automaticamente
		String sql = "insert into model_login(login, password, nome, email) values(?, ?, ?, ?);";
		
		//prepara o sql da um apelido pra ele passa o valor de conex�o com o preparo junto com o sql
		PreparedStatement preparedStatement = connection.prepareStatement(sql); 
		
		preparedStatement.setString(1, objeto.getLogin());
		preparedStatement.setString(2, objeto.getPassword()); 
		preparedStatement.setString(3, objeto.getNome());
		preparedStatement.setString(4, objeto.getEmail());
        
		preparedStatement.execute(); //executar o sql	
		connection.commit();
		
		}catch(Exception e) {
			
			e.printStackTrace();
		}

	}

}
