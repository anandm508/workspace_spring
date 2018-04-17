package java8.lambdas.functionalinterfaces;

@FunctionalInterface
public interface CheckTrait {
	boolean test(Animal a);

	@Override
	boolean equals(Object obj);

	@Override
	public int hashCode();

}
