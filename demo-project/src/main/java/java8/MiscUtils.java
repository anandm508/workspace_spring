package java8;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;


public class MiscUtils {
	
	private MiscUtils() {
		throw new AssertionError();
	}
	
	/**
	 * This function takes a supplier(from) maps its value or the default to the consumer(to)
	 * @param supplier
	 * @param consumer
	 * @param defaultVal
	 */
	public static <I> void optionalDefault(final Supplier<I> from, final Consumer<I> to, final I defaultVal) {
		to.accept(Optional.ofNullable(from.get()).orElse(defaultVal));
	}
	
	/**
	 * Maps the value to the consumer(to) after applying the converter(convert).
	 * @param value
	 * @param mapper
	 * @param to
	 */
	public static <I, O> void optionalMap(final I value, final Function<I, O> convert, Consumer<O> to) {
		Optional.ofNullable(value).map(convert).ifPresent(to);
	}
	
	/**
	 * Maps the value1 and value2 to the consumer(to) after applying the converter(convert).
	 * @param value
	 * @param mapper
	 * @param to
	 */
	public static <I1, I2, O> void optionalMap(final I1 value1, final I2 value2, final BiFunction<I1, I2, O> convert, Consumer<O> to) {
		if( value1 != null && value2 != null) {
			optionalMap(convert.apply(value1, value2), Function.identity(), to);
		}
	}
	
	/**
	 * Maps a list (list) to consumer (to) after applying conversion to individual elements
	 * @param list
	 * @param to
	 * @param convert
	 */
	public static <I, O> void optionalMap(final List<I> list, final Consumer<List<O>> to, final Function<I, O> convert) {
		optionalMap(list, convertList(convert), to);
	}
	
	/**
	 * Returns a Function which can work on lists using a function which is specific to individual items
	 * @param convert
	 * @return
	 */
	private static <I, O> Function<List<I>, List<O>> convertList(Function<I, O> convert) {
		return list -> list.stream().filter(Objects::nonNull).map(convert).filter(Objects::nonNull).collect(Collectors.toList());
	}
	
	/**
	 * Maps the value (from) to the consumer(to) if the test holds true after applying the conversion function
	 * @param from
	 * @param to
	 * @param test
	 * @param convert
	 */
	public static <I, O> void optionalMap(final I from, Consumer<O> to, Predicate<I> test, Function<I, O> convert) {
		Optional.ofNullable(from).ifPresent(object -> {
			if(test.test(from)) {
				to.accept(convert.apply(from));
			}
		});
	}
	
	/**
	 * Maps a map to list {@link #mapToList(Function)}
	 * @param converter
	 * @return Function<Map<K,V>, List<T>>
	 */
	public static <K,V,T> Function<Map<K,V>, List<T>> mapToList(final Function<Entry<K,V>, T> converter){
		return map -> map.entrySet().stream().map(converter).collect(Collectors.toList());
	}
	
	/**
	 * Copies input values to output
	 * @param in
	 * @param supplier
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static <I, O> O copyTo(final I in, final Supplier<O> supplier) {
		final O out = supplier.get();
		try {
			BeanUtils.copyProperties(out, in);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return out;
	}
	
	/**
	 * Returns a function version of {@link #copyTo(Object, Supplier)
	 * @param supplier
	 * @return
	 */
	public static <I, O> Function<I, O> copyTo(final Supplier<O> supplier) {
		return input -> copyTo(input, supplier);
	}
}
