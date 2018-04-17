package java8.lambdas.functionalinterfaces;

public class Animal {

	private String species;
	private boolean canHop;
	private boolean canSwim;

	public Animal(String species, boolean canHop, boolean canSwim) {
		super();
		this.species = species;
		this.canHop = canHop;
		this.canSwim = canSwim;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public boolean isCanHop() {
		return canHop;
	}

	public void setCanHop(boolean canHop) {
		this.canHop = canHop;
	}

	public boolean isCanSwim() {
		return canSwim;
	}

	public void setCanSwim(boolean canSwim) {
		this.canSwim = canSwim;
	}

	private static void print(Animal a, CheckTrait c) {
		System.out.println(c.test(a));
	}

	public static void main(String[] args) {
		Animal a = new Animal("Fish", false, true);
		Animal b = new Animal("Kangaroo", true, false);

		print(a, (animal) -> animal.isCanHop());
		print(b, (animal) -> animal.isCanHop());
	}

}
