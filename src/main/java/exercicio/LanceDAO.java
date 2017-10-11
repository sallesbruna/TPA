package exercicio;

import java.util.List;

public interface LanceDAO
{	
	public long inclui(Lance umLance); 

	public void altera(Lance umLance)
		throws ObjetoNaoEncontradoException; 
	
	public void exclui(long id) 
		throws ObjetoNaoEncontradoException; 
	
	public Lance recuperaUmLance(long numero) 
		throws ObjetoNaoEncontradoException; 
	
	public List<Lance> recuperaLances();
}