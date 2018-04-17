package java8;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorDemo {
	
	
	public static void main(String[] args) {
		
		
		Stream<String> collectStream1 = Stream.of("w", "o", "l", "f");
		StringBuilder collectbuilder1 = collectStream1.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
		System.out.println("collectbuilder1:" + collectbuilder1.toString());
		
		/*
		Stream<String> collectStream1 = Stream.of("w", "o", "l", "f").parallel();
		StringBuilder collectbuilder1 = collectStream1.collect(StringBuilder::new, StringBuilder::append, (a,b)->{throw new UnsupportedOperationException("nope");});
		System.out.println("collectbuilder1:" + collectbuilder1.toString());
		*/

		Stream<String> collectStream2 = Stream.of("w", "o", "l", "f");
		TreeSet<String> collectSet2 = collectStream2.collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
		System.out.print("collectSet2: ");
		collectSet2.forEach(System.out::print);

		System.out.println();
		Stream<String> collectStream3 = Stream.of("w", "o", "l", "f");
		TreeSet<String> collectSet3 = collectStream3.collect(Collectors.toCollection(TreeSet::new));
		System.out.print("collectSet3: ");
		collectSet3.forEach(System.out::print);

		System.out.println();
		Stream<String> collectStream4 = Stream.of("w", "o", "l", "f");
		Set<String> collectSet4 = collectStream4.collect(Collectors.toSet());
		System.out.print("collectSet4: ");
		collectSet4.forEach(System.out::print);

	}
}
