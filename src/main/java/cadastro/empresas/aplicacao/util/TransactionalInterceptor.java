package cadastro.empresas.aplicacao.util;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Transactional
public class TransactionalInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public TransactionalInterceptor() {
		
	}
	
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
