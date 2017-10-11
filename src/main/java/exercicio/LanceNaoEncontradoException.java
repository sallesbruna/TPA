package exercicio;

public class LanceNaoEncontradoException extends Exception {
private final static long serialVersionUID = 1;
	
	private int codigo;
	
	public LanceNaoEncontradoException(String msg)
	{	super(msg);
	}

	public LanceNaoEncontradoException(int codigo, String msg)
	{	super(msg);
		this.codigo = codigo;
	}
	
	public int getCodigoDeErro()
	{	return codigo;
	}

}
