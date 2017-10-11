package exercicio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;

public class LanceDAOImpl implements LanceDAO
{	
	public long inclui(Lance umLance) 
	{	try
		{	EntityManager em = JPAUtil.getEntityManager();

			em.persist(umLance);
			
			return umLance.getId();
		} 
		catch(RuntimeException e)
		{	throw new InfraestruturaException(e);
		}
	}

	public void altera(Lance umLance) 
		throws ObjetoNaoEncontradoException 
	{	try
		{	EntityManager em = JPAUtil.getEntityManager();

			Lance Lance = em.find(Lance.class, umLance.getId(), LockModeType.PESSIMISTIC_WRITE);
			
			if(Lance == null)
			{	throw new ObjetoNaoEncontradoException();
			}
		
			em.merge(umLance);
		}
		catch(RuntimeException e)
		{	throw new InfraestruturaException(e);
		}
	}

	public void exclui(long id) 
		throws ObjetoNaoEncontradoException 
	{	try
		{	EntityManager em = JPAUtil.getEntityManager();
		
			Lance Lance = em.find(Lance.class, id, LockModeType.PESSIMISTIC_WRITE);
			
			if(Lance == null)
			{	throw new ObjetoNaoEncontradoException();
			}
	
			em.remove(Lance);
		}
		catch(RuntimeException e)
		{	throw new InfraestruturaException(e);
		}
	}

	public Lance recuperaUmLance(long id) 
		throws ObjetoNaoEncontradoException 
	{	try
		{	EntityManager em = JPAUtil.getEntityManager();

			Lance umLance = (Lance)em
				.find(Lance.class, new Long(id));
			
			if (umLance == null)
			{	throw new ObjetoNaoEncontradoException();
			}

			return umLance;
		} 
		catch(RuntimeException e)
		{	throw new InfraestruturaException(e);
		}
	}

	public Lance recuperaUmLanceComLock(long id) 
		throws ObjetoNaoEncontradoException 
	{	try
		{	EntityManager em = JPAUtil.getEntityManager();

			Lance umLance = em.find(Lance.class, id, LockModeType.PESSIMISTIC_WRITE);

			if (umLance == null)
			{	throw new ObjetoNaoEncontradoException();
			}

			return umLance;
		} 
		catch(RuntimeException e)
		{	throw new InfraestruturaException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Lance> recuperaLances()
	{	try
		{	EntityManager em = JPAUtil.getEntityManager();

			List<Lance> Lances = em
				.createQuery("select p from Lance p " +
						     "order by p.id asc")
				.getResultList();

			return Lances;
		} 
		catch(RuntimeException e)
		{	throw new InfraestruturaException(e);
		}
	}
}