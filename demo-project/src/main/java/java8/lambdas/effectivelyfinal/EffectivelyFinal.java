package java8.lambdas.effectivelyfinal;

import java.util.function.Supplier;

public class EffectivelyFinal {
	static String walk = "walk";
	static Boolean b = false;

	public static void main(String[] args) {
		evePlay(b);
	}

	static void evePlay(boolean b) {
		String approach = "amble";
		// Approach should be effectively final
		// approach = "gamble";
		play(() -> approach);

		// Instance variables don't have any issues if you change the values
		walk = "Don't walk";
		play(() -> walk);

		// Boolean value being passed is effectively final
		// b = true;
		play(() -> b ? "true" : "false");

	}

	static void play(Supplier<String> sup) {
		System.out.println("->" + sup.get());
	}
}
