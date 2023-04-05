package ibf2022.batch1.csf.assessment.server.services;

import java.io.StringReader;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch1.csf.assessment.server.models.Comment;
import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.repositories.MovieRepository;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue.ValueType;;

@Service
public class MovieService {

	public static final String MOVIE_API_URL = "https://api.nytimes.com/svc/movies/v2/reviews/search.json";

	@Value("${movie.key}")
	private String movieKey;

	@Autowired
	private MovieRepository movieRepo;

	// TODO: Task 4
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	public List<Review> searchReviews(String query) {

		String movieReviewUrl = UriComponentsBuilder
				.fromUriString(MOVIE_API_URL)
				.queryParam("query", query)
				.queryParam("api-key", movieKey)
				.build()
				.toUriString();

		System.out.println(">>>> movieReviewUrl: " + movieReviewUrl);

		RequestEntity<Void> req = RequestEntity.get(movieReviewUrl)
				.accept(MediaType.APPLICATION_JSON).build();

		RestTemplate template = new RestTemplate();

		ResponseEntity<String> resp = null;

		try {
			resp = template.exchange(req, String.class);

		} catch (RestClientException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}


		String payload = resp.getBody();

		JsonReader reader = Json.createReader(new StringReader(payload));
		JsonObject respJsonObj = reader.readObject();
		System.out.println(">>>> respJsonObj: " + respJsonObj);

		JsonArray jsonArr = respJsonObj.getJsonArray("results");

		// List<Review> reviewsList = jsonArr.stream()
		// .map(n -> n.asJsonObject())
		// .map(n -> Review.toReview(n))
		// .toList();

		// return reviewsList;

		return jsonArr.stream()
				.map(n -> n.asJsonObject())
				.map(n -> Review.toReview(n))
				.toList();

	}

	public Comment saveComment(Comment comment) {
		return movieRepo.saveComment(comment);
	}

	public int countComments() {
		return movieRepo.countComments();
	}

}
