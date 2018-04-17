package tasks;

public class Video {

	public Video(String video) {
		super();
		this.video = video;
	}

	private String video;

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	@Override
	public String toString() {
		return "Video [video=" + video + "]";
	}

}
