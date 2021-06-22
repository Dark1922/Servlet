package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conneciton.SingleConnection;
import model.ModelLogin;

public class DAOLoginRepository {
	
	//objeto de conecxão
private Connection connection;

public DAOLoginRepository() {
	//sempre que instacia ou chamar essa classe vai ter a connection
  connection = SingleConnection.getConnection();
}

//throws exception vai jogar o erro pra cima e o filtro vai mostrar a exceção
public boolean validarAutenticacao(ModelLogin modelLogin) throws Exception {
	//login ? é igual ao parametro que agente passar e senha a msm coisa
	String sql = "select * from model_login where upper(login) = upper(?) and upper(password) = upper(?) ";  
	
	PreparedStatement statement = connection.prepareStatement(sql);//prepara o sql
    statement.setString(1, modelLogin.getLogin());
    statement.setString(2, modelLogin.getPassword());
    
    ResultSet  resultSet = statement.executeQuery();//objeto de resultado
    
    if(resultSet.next()) {//se tiver usuario com login e senha
    	
    	return true; //retorno verdadeiro autenticado
    }
    return false;// n tem os dados no banco não autenticado

}
//pesquiso no banco , preparo , seto os parameto com os dados pego , o resultado 
}
