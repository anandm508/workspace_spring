package java8;

import java.util.Collections;
import java.util.Comparator;

public class TestDefault {

	class Test1 implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			return reversed().compare(o1, o2);
		}

	}

	interface Test2<T> extends Comparator<T> {
		@Override
		default Comparator<T> reversed() {
			return Collections.reverseOrder(this);
		}
	}

	class Test3 implements Comparator<String>, Test2<String> {

		@Override
		public int compare(String o1, String o2) {
			return reversed().compare(o1, o2);
		}

	}
}
