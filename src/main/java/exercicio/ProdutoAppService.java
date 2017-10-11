package exercicio;

import java.util.List;

public class ProdutoAppService
{	
	private static ProdutoDAO produtoDAO = FabricaDeDAOs.getDAO(ProdutoDAO.class);
	private LanceAppService lanceAppService = new LanceAppService();
	public static int semaforo = 0;

	public long inclui(Produto umProduto) 
	{	try
		{	
			// NENHUMA VALIDA��O EST� SENDO REALIZADA AQUI!!!
		
/*==>*/		JPAUtil.beginTransaction();
			

			long numero = produtoDAO.inclui(umProduto);

/*==>*/		JPAUtil.commitTransaction();
			
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
			JPAUtil.closeEntityManager();
		}
	}

	public void altera(Produto umProduto)
		throws ProdutoNaoEncontradoException
	{	try
		{	JPAUtil.beginTransaction();
			produtoDAO.altera(umProduto);

			JPAUtil.commitTransaction();

		} 
		catch(ObjetoNaoEncontradoException e)
		{	
			JPAUtil.rollbackTransaction();

			throw new ProdutoNaoEncontradoException("Produto n�o encontrado");
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
	
	public void altera(Produto umProduto, Lance umLance)
			throws ProdutoNaoEncontradoException
		{	try
			{	JPAUtil.beginTransaction();
				semaforo++;
				lanceAppService.inclui(umLance);
				
				produtoDAO.altera(umProduto);
				
				JPAUtil.commitTransaction();
				semaforo--;
			} 
			catch(ObjetoNaoEncontradoException e)
			{	
				JPAUtil.rollbackTransaction();

				throw new ProdutoNaoEncontradoException("Produto n�o encontrado");
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
			{   if(semaforo == 0)
					JPAUtil.closeEntityManager();
			}
		}
		
	public void exclui(long numero) 
		throws ProdutoNaoEncontradoException
	{	try
		{	JPAUtil.beginTransaction();

			produtoDAO.exclui(numero);

			JPAUtil.commitTransaction();
		} 
		catch(ObjetoNaoEncontradoException e)
		{	
			JPAUtil.rollbackTransaction();

		    throw new ProdutoNaoEncontradoException("Produto n�o encontrado");
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

	public Produto recuperaUmProduto(long numero) 
		throws ProdutoNaoEncontradoException
	{	
		try
		{	Produto umProduto = produtoDAO.recuperaUmProduto(numero);
			
			return umProduto;
		} 
		catch(ObjetoNaoEncontradoException e)
		{	throw new ProdutoNaoEncontradoException("Produto n�o encontrado");
		}
		finally
		{   JPAUtil.closeEntityManager();
		}
	}

	public List<Produto> recuperaProdutos() 
	{	try
		{	List<Produto> produtos = produtoDAO.recuperaProdutos();

			return produtos;
		} 
		finally
		{   JPAUtil.closeEntityManager();
		}
	}
}