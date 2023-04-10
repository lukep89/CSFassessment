package ibf2022.batch1.csf.assessment.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ibf2022.batch1.csf.assessment.server.models.Comment;
import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.services.MovieService;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@Controller
// @CrossOrigin(origins = "*")
@RequestMapping
public class MovieController {

	@Autowired
	private MovieService movieSvc;

	// TODO: Task 3, Task 4, Task 8

	@GetMapping(path = "/api/search", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> searchReviews(@RequestParam String movieName) {

		List<Review> reviews = movieSvc.searchReviews(movieName);
		System.out.println(">>>> review list: " + reviews);

		if (reviews.isEmpty()) {
			// return ResponseEntity.ofNullable("Null. no review found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Null. no review found");
		}

		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
		reviews.stream()
				.forEach(v -> {

					arrBuilder.add(v.toJsonObj());

				});

		return ResponseEntity.ok(arrBuilder.build().toString());

	}

	// @PostMapping(path = "/api/comment")
	// public ResponseEntity<String> saveComment(@RequestBody Comment comment) {

	// // this method get back the request body
	// System.out.println(">>>> controller comment: " + comment.getTitle());
	// System.out.println(">>>> controller comment: " + comment.getName());
	// System.out.println(">>>> controller comment: " + comment.getRating());
	// System.out.println(">>>> controller comment: " + comment.getComment());

	// // save to mongo using repo
	// movieSvc.saveComment(comment);

	// String title = comment.getTitle();

	// int movieCount = movieSvc.countComments();

	// JsonObjectBuilder ocjBuilder = Json.createObjectBuilder();
	// ocjBuilder.add("title", title);
	// ocjBuilder.add("movieCount", movieCount);
	// JsonObject result = ocjBuilder.build();

	// return ResponseEntity
	// .status(HttpStatus.OK)
	// .contentType(MediaType.APPLICATION_JSON)
	// .body(result.toString());

	// }

	@PostMapping(path = "/api/comment")
	public ResponseEntity<String> saveComment(@RequestParam("title") String title,
			@RequestParam("name") String name, @RequestParam("rating") String rating,
			@RequestParam("comment") String comment) {

		// System.out.println(">>>> controller title: " + title);
		// System.out.println(">>>> controller name: " + name);
		// System.out.println(">>>> controller rating: " + rating);
		// System.out.println(">>>> controller comment: " + comment);

		// this method get back the request param
		Comment c = new Comment();
		c.setTitle(title);
		c.setName(name);
		c.setRating(rating);
		c.setComment(comment);

		// System.out.println(">>>> new comment obj: " + c.toString());

		movieSvc.saveComment(c);

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(null);

	}

}
