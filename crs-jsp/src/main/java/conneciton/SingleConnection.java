package conneciton;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	//statico pq sempre vai ser o mesmo
	private static String banco = "jdbc:postgresql://localhost:5432/crs-jsp?autoReconnect=true";
	private static String user = "postgres";
	private static String password = "84353246mv";
	private static Connection connection = null;
	
	public static Connection getConnection() {
		return connection;
	}
	
	static {//de qlq lugar que chamar a classe SingleConnection
		conectar(); //vai obter uma conex�o
	}
	
	
	public SingleConnection() {//qnd tiver uma instacia vai conectar
         conectar();//desse objeto
	}
	
	
	private static void conectar() {
		try {
			//se a conex�o for igual a null ai vai abrir a cnx
			if(connection == null) {
				Class.forName("org.postgresql.Driver");//drive de conex�o
				
				//os dados da nossa conex�o com o banco
				connection = DriverManager.getConnection(banco, user, password);
			    connection.setAutoCommit(false);//para n efetuar altera��es no banco sem nosso comando
			    
			}
			
		}catch(Exception e) {
			e.printStackTrace();//mostar qualquer erro no momento de conectar
		}
	}
	

}
