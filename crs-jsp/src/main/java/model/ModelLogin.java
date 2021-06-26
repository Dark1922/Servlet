package model;

import java.io.Serializable;

public class ModelLogin implements Serializable {

	/* parte de compilação das classes implements e uid abaixo */
	private static final long serialVersionUID = 1L;

	private long id;
	private String login;
	private String password;
	private String email;
	private String nome;
    
	//public pra acessar de outros lugares
	public boolean isNovo() {
		//this pega o atributo da classe lá de cima
		if (this.id == 0) {//inserir um novo id
			return true;
			
		}else if (this.id != 0 && this.id > 0) {
			return false; //atualizar não é null
		}
		
		return id == 0; //se é igual a null se não ele já existe se n vai gravar um novo e atualizar
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

}
