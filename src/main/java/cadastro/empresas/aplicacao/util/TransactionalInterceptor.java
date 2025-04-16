package cadastro.empresas.aplicacao.util;

import java.io.Serializable;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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
