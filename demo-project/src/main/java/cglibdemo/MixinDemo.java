package cglibdemo;

import net.sf.cglib.proxy.Mixin;

interface Interface1 {
	String first();
}

interface Interface2 {
	String second();
}

class Class1 implements Interface1 {
	@Override
	public String first() {
		return "first behaviour";
	}
}

class Class2 implements Interface2 {
	@Override
	public String second() {
		return "second behaviour";
	}
}

interface MixinInterface extends Interface1, Interface2 {
}
/**
 * A mixin is a construct that allows combining multiple objects into one. 
 * We can include a behavior of a couple of classes and expose that behavior as a single class or interface.  
 * The cglib Mixins allow the combination of several objects into a single object. 
 * However, in order to do so all objects that are included within a mixin must be backed by interfaces
 * @author 28883
 *
 */
public class MixinDemo {

	public static void main(String[] args) {
		Mixin mixin = Mixin.create(new Class[] { Interface1.class, Interface2.class, MixinInterface.class },
				new Object[] { new Class1(), new Class2() });
		MixinInterface mixinDelegate = (MixinInterface) mixin;

		System.out.println(mixinDelegate.first());
		System.out.println(mixinDelegate.second());
	}

}
