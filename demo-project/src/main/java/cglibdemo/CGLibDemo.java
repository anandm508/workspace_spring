package cglibdemo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;

public class CGLibDemo {

	public static void main(String... args) {

		// using fixed value callback. It will return the same value from proxy
		// everytime
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(PersonService.class);
		enhancer.setCallback((FixedValue) () -> "Hello Tom!");
		PersonService proxy = (PersonService) enhancer.create();
		String res = proxy.sayHello(null);
		System.out.println(res);
		// It will throw exception since string is not a valid return type for
		// lengthOfName method
		// System.out.println(proxy.lengthOfName(null));

		// Uses method interceptor to check and return values accordingly.
		Enhancer enhancer2 = new Enhancer();
		enhancer2.setSuperclass(PersonService.class);
		enhancer2.setCallback((MethodInterceptor) (obj, method, args_val, proxy_val) -> {
			if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
				return "Hello Tom!";
			} else {
				return proxy_val.invokeSuper(obj, args_val);
			}
		});
		PersonService proxy2 = (PersonService) enhancer2.create();
		System.out.println(proxy2.sayHello(null));
		int lengthOfName = proxy2.lengthOfName("Mary");
		System.out.println(lengthOfName);

		// Bean generator demo
		try {
			BeanGenerator beanGenerator = new BeanGenerator();
			beanGenerator.addProperty("name", String.class);
			Object myBean = beanGenerator.create();
			Method setter = myBean.getClass().getMethod("setName", String.class);
			setter.invoke(myBean, "some string value set by a cglib");
			Method getter = myBean.getClass().getMethod("getName");
			System.out.println(getter.invoke(myBean));
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}

	}
}
