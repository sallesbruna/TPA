package exercicio;

import java.util.List;

public class LanceAppService
{	
	private static LanceDAO LanceDAO = FabricaDeDAOs.getDAO(LanceDAO.class);

	public long inclui(Lance umLance) 
	{	
			try
			{	
				long numero = 0;
				// NENHUMA VALIDAÇÃO ESTÁ SENDO REALIZADA AQUI!!!
				
					
				
		/*==>*/		JPAUtil.beginTransaction();
					ProdutoAppService.semaforo++;
		
					numero = LanceDAO.inclui(umLance);
					if(ProdutoAppService.semaforo == 0)
					{
		/*==>*/			JPAUtil.commitTransaction();
						ProdutoAppService.semaforo--;
					}
					return numero;
				
			} 
			catch(InfraestruturaException e)
			{	try
	/*==>*/		{
					JPAUtil.rollbackTransaction();
				}
				catch(InfraestruturaException ie)
				{				
				}
				
			    throw e;
			}
			finally
	/*==>*/ {   
				if(ProdutoAppService.semaforo == 0)
					JPAUtil.closeEntityManager();
			}
		
	}

	public void altera(Lance umLance)
		throws LanceNaoEncontradoException
	{	try
		{	JPAUtil.beginTransaction();
			JPAUtil.semaforo++;
			LanceDAO.altera(umLance);

			JPAUtil.commitTransaction();

			JPAUtil.semaforo--;
		} 
		catch(ObjetoNaoEncontradoException e)
		{	
			JPAUtil.rollbackTransaction();

			throw new LanceNaoEncontradoException("Lance não encontrado");
		}
		catch(InfraestruturaException e)
		{	try
			{	JPAUtil.rollbackTransaction();
			}
			catch(InfraestruturaException ie)
			{				
			}

		    throw e;
		}
		finally
		{   JPAUtil.closeEntityManager();
		}
	}
	
	public void exclui(long numero) 
		throws LanceNaoEncontradoException
	{	try
		{	JPAUtil.beginTransaction();

			LanceDAO.exclui(numero);

			JPAUtil.commitTransaction();
		} 
		catch(ObjetoNaoEncontradoException e)
		{	
			JPAUtil.rollbackTransaction();

		    throw new LanceNaoEncontradoException("Lance não encontrado");
		}
		catch(InfraestruturaException e)
		{	try
			{	JPAUtil.rollbackTransaction();
			}
			catch(InfraestruturaException ie)
			{				
			}

		    throw e;
		}
		finally
		{   JPAUtil.closeEntityManager();
		}
	}

	public Lance recuperaUmLance(long numero) 
		throws LanceNaoEncontradoException
	{	
		try
		{	Lance umLance = LanceDAO.recuperaUmLance(numero);
			
			return umLance;
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new LanceNaoEncontradoException("Lance não encontrado");
		}
		finally
		{   JPAUtil.closeEntityManager();
		}
	}

	public List<Lance> recuperaLances() 
	{	try
		{	List<Lance> Lances = LanceDAO.recuperaLances();

			return Lances;
		} 
		finally
		{   JPAUtil.closeEntityManager();
		}
	}
}