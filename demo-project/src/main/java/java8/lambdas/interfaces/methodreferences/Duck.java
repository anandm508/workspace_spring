package java8.lambdas.interfaces.methodreferences;

public class Duck {

	private String name;
	private Integer price;

	public Duck(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public Duck() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
}
