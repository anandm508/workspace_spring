package java8.lambdas.interfaces.functional;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class OperatorsTest {

	public static void main(String[] args) {
		UnaryOperator<String> unaryOperator1 = String::toUpperCase;
		UnaryOperator<String> unaryOperator2 = (s) -> s.toLowerCase();

		System.out.println(unaryOperator1.apply("java"));
		System.out.println(unaryOperator2.apply("JAVA"));

		BinaryOperator<String> binaryOperator1 = String::concat;
		BinaryOperator<String> binaryOperator2 = (x, y) -> x.concat(y);

		System.out.println(binaryOperator1.apply("Welcome to ", "JAVA8"));
		System.out.println(binaryOperator2.apply("Welcome to ", ".Net"));
	}

}
