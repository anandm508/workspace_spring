package java8;

import static org.junit.Assert.assertEquals;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class TestMiscUtils {
	
	public static class Model {
		private String data;
		private int id;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

	};
	
	@Test
	public void testOptionalDefault(){
		Model model1 = new Model();
		Model model2 = new Model();
		MiscUtils.optionalDefault(model1::getData, model2::setData, "Hello World!");
		assertEquals("Hello World!", model2.getData());
	}
	
	@Test
	public void testOptionalMap(){
		Model model1 = new Model();
		model1.setData("hello world!");
		Model model2 = new Model();
		MiscUtils.optionalMap(model1.getData(), StringUtils::upperCase, model2::setData);
		assertEquals("HELLO WORLD!", model2.getData());
		
		MiscUtils.optionalMap("abcxyz", "xyz", StringUtils::strip, model2::setData);
		assertEquals("abc", model2.getData());
	}
	
	@Test
	public void testCopyTo() {
		Model model1 = new Model();
		model1.setData("hello world!");
		Model model2 = MiscUtils.copyTo(model1, Model::new);
		assertEquals(model1.getData(), model2.getData());
		
		model2 = MiscUtils.copyTo(Model::new).apply(model1);
		assertEquals(model1.getData(), model2.getData());
	}
}