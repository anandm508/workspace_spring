package dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test<T> {

	private T t;

	public static void main(String[] args) {
		
		/**
		 * Dynamic proxy Example
		 */
		Auditor auditor = new Auditor();
		Calculator real = new CalculatorImpl();
		InvocationHandler handler = new AuditingInvocationHandler(auditor, real);
		Calculator proxy = (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
				new Class[] { Calculator.class }, handler);
		real.add(2, 2); // Will not be audited
		proxy.add(2, 2); // Will be audited
		
		
		
		

		/*
		 * String val = null; System.out.println(Optional.of(val).orElse("asdasd"));
		 * Optional<String> optional = Optional.of(val);
		 * System.out.println(optional.orElse("asdsd"));
		 * System.out.println(optional.isPresent()); System.out.println(optional.get());
		 */
		/*
		List<String> list = new ArrayList<>();
		Optional<List<String>> optional = Optional.ofNullable(list);

		Function<List<String>, List<String>> function = (a) -> {
			if (a.size() == 0) {
				return null;
			}
			return a;
		};

		System.out.println(optional.map(function).get());

		System.out.println(optional.get());
		*/
	}

	public <E> void getExample(E e) {
		return;
	}

}
