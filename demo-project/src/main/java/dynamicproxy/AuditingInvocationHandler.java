package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AuditingInvocationHandler implements InvocationHandler {
	private final Auditor auditor;
	private final Object target;

	public AuditingInvocationHandler(Auditor auditor, Object target) {
		this.auditor = auditor;
		this.target = target;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		auditor.audit(target.getClass().getName(), "before " + method.getName());
		Object returnObject = method.invoke(target, args);
		auditor.audit(target.getClass().getName(), "after " + method.getName());
		return returnObject;
	}
}