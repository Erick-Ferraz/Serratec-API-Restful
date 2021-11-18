package br.org.serratec.backend.exception;

public class CpfException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CpfException(String descricao) {
		super(descricao);
	}

	public CpfException() {
		super("CPF inválido!");
	}

}
