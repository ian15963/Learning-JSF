package cadastro.empresas.aplicacao.interceptor;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Transactional
@Priority(Interceptor.Priority.APPLICATION)
public class TransactionalInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager entityManager;
	
	@AroundInvoke
	public Object invoke(InvocationContext context) throws Exception {
		EntityTransaction transaction = entityManager.getTransaction();
		boolean creator = false;
		
		try {
			if(!transaction.isActive()) {
				
				transaction.begin();
				transaction.rollback();
				
				transaction.begin();
				
				creator = true;
			}
			
			return context.proceed();
		}catch(Exception e) {
			if(transaction != null && creator) {
				transaction.rollback();
			}
			
			throw e;
		}finally {
			if(transaction != null && creator) {
				transaction.commit();
			}
		}
	}
	
}
