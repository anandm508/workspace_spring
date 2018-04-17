package tasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 * Runs enclosed test classes
 */
@RunWith(Enclosed.class)
public class TestUtil {

	// Utility class variable
	private static Util util = new Util();

	/**
	 * Static inner parameterized test class for filter method
	 */
	@RunWith(Parameterized.class)
	public static class TestFilterMethod {

		@Parameter(0)
		public List<Video> videoList;

		@Parameter(1)
		public int resultSize;

		/**
		 * Parameter fixture for filterByMp4Videos test method.
		 */
		@Parameters
		public static Collection<Object[]> data() {
			final List<Video> videoList = new LinkedList<>();
			videoList.add(new Video("MP4"));
			videoList.add(new Video("mp4"));
			videoList.add(new Video("AVI"));
			videoList.add(new Video("FLV"));
			videoList.add(new Video("MOV"));

			final List<Video> videoListWithoutMp4 = new LinkedList<>();
			videoList.add(new Video("AVI"));
			videoList.add(new Video("FLV"));
			videoList.add(new Video("MOV"));
			return Arrays.asList(new Object[][] { { null, 0 }, { videoList, 2 }, { videoListWithoutMp4, 0 } });
		}

		/**
		 * Test method for <b>filterByMp4Videos</b> method
		 */
		@Test
		public void testFilterByMp4Videos() {
			final List<Video> result = util.filterByMp4Videos(videoList);
			assertNotNull(result);
			assertEquals(result.size(), resultSize);
		}
	}

	/**
	 * Static inner test class<br/>
	 * Runs test which doesn't needs parameterized testing
	 */
	public static class TestOtherMethods {

		/**
		 * Test method for <b>convertDateToZonedDateTime</b> method
		 */
		@Test
		public void testConvertDateToZonedDateTime() {
			assertNull(util.convertDateToZonedDateTime(null));
			assertNotNull(util.convertDateToZonedDateTime(new Date()));
		}

		/**
		 * Test method for <b>convertZonedDateTimeToString</b> method
		 */
		@Test
		public void testConvertZonedDateTimeToString() {
			assertNull(util.convertZonedDateTimeToString(null));
			assertNotNull(util.convertZonedDateTimeToString(ZonedDateTime.now()));
		}
	}
}
