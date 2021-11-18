package br.org.serratec.backend.exception;

public class UsernameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsernameException(String descricao) {
		super(descricao);
	}

	public UsernameException() {
		super("Username já está em uso!");
	}

}
