package tasks;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Util {
	/**
	 * Return only the list containing MP4 video format files
	 * 
	 * @param videoList
	 * @return List of Video
	 */
	protected List<Video> filterByMp4Videos(List<Video> videoList) {
		if (videoList == null)
			return new LinkedList<>();

		List<Video> mp4VideoList = videoList.stream().filter(
				p -> p.getVideo().trim().toUpperCase().contains(ProductConstants.VIDEO_FORMAT_MP4.toUpperCase()) && p
						.getVideo().toUpperCase()
						.substring(p.getVideo().trim().length() - (ProductConstants.VIDEO_FORMAT_MP4.length()),
								p.getVideo().trim().length())
						.equals(ProductConstants.VIDEO_FORMAT_MP4.toUpperCase()))
				.collect(Collectors.toCollection(LinkedList::new));
		if (mp4VideoList == null)
			mp4VideoList = new LinkedList<>();

		return mp4VideoList;
	}

	/**
	 * Convert Date prior to JDK1.8 to JDK1.8 ZonedDateTime Class
	 * 
	 * @param date
	 * @return ProductCreditTerms
	 */
	protected ZonedDateTime convertDateToZonedDateTime(Date date) {
		if (date == null)
			return null;
		Instant instant = date.toInstant();
		ZoneId zoneId = ZoneId.of(ProductConstants.TZ_GMT_ZONE_ID);
		ZonedDateTime zonedDateTime = instant.atZone(zoneId);
		return zonedDateTime;
	}

	/**
	 * Convert LocalDateTime to String in Custom Format
	 * 
	 * @param zonedDateTime
	 * @return String
	 */
	protected String convertZonedDateTimeToString(ZonedDateTime zonedDateTime) {
		if (zonedDateTime == null)
			return null;
		DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
		String formattedDateTime = zonedDateTime.format(formatter);
		return formattedDateTime;
	}

	/*
	 * public static void main(String[] args) {
	 * 
	 * Util util = new Util();
	 * 
	 * List<Video> videoList = new LinkedList<>();
	 * 
	 * videoList.add(new Video("MP4")); videoList.add(new Video("mp4"));
	 * videoList.add(new Video("AVI")); videoList.add(new Video("FLV"));
	 * videoList.add(new Video("MOV"));
	 * 
	 * System.out.println(util.filterByMp4Videos(videoList));
	 * 
	 * Date date = new Date(); System.out.println(date); ZonedDateTime zz =
	 * util.convertDateToZonedDateTime(date); System.out.println(zz);
	 * System.out.println(util.convertZonedDateTimeToString(zz));
	 * 
	 * }
	 */

}
