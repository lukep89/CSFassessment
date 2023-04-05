package ibf2022.batch1.csf.assessment.server.models;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObject;

// DO NOT MODIFY THIS CLASS
public class Review {
	// display_title
	private String title;
	// mpaa_rating
	private String rating;
	// byline
	private String byline;
	// headline
	private String headline;
	// summary_short
	private String summary;
	// link.url
	private String reviewURL;
	// multimedia.src
	private String image = null;

	private int commentCount = 0;

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getRating() {
		return this.rating;
	}

	public void setByline(String byline) {
		this.byline = byline;
	}

	public String getByline() {
		return this.byline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getHeadline() {
		return this.headline;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setReviewURL(String reviewURL) {
		this.reviewURL = reviewURL;
	}

	public String getReviewURL() {
		return this.reviewURL;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return this.image;
	}

	public boolean hasImage() {
		return null != this.image;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	};

	public int getCommentCount() {
		return this.commentCount;
	}

	@Override
	public String toString() {
		return "Review{title:%s, rating:%s}".formatted(title, rating);
	}

	public static Review toReview(JsonObject obj) {

		Review review = new Review();
		review.setTitle(obj.getString("display_title"));
		System.out.println(">>>>>> object.get: " + obj.getString("display_title"));
		review.setRating(obj.getString("mpaa_rating"));
		review.setByline(obj.getString("byline"));
		review.setHeadline(obj.getString("headline"));
		review.setSummary(obj.getString("summary_short"));
		review.setReviewURL(obj.get("link").asJsonObject().getString("url"));
		review.setImage(haveImage("multimedia", obj));

		// System.out.println(">>>> IN toReview()");
		return review;
	}

	private static String haveImage(String fn, JsonObject obj) {

		// System.out.println(">>>>>" + !obj.isNull("multimedia"));
		if (!obj.isNull("multimedia")) {

			return obj.get("multimedia").asJsonObject().getString("src");
		}
		return "noImage";
	}

	public JsonObject toJsonObj() {
		return Json.createObjectBuilder()
				.add("title", title)
				.add("rating", rating)
				.add("headline", headline)
				.add("summary", summary)
				.add("reviewURL", reviewURL)
				.add("image", image)
				.add("commentCount", commentCount)
				.build();
	}
}
