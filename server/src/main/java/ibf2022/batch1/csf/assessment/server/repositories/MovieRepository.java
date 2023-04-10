package ibf2022.batch1.csf.assessment.server.repositories;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.stereotype.Repository;

import ibf2022.batch1.csf.assessment.server.models.Comment;

@Repository
public class MovieRepository {

	@Autowired
	private MongoTemplate template;

	public static String COLLECTION_COMMENTS = "comments";

	// TODO: Task 5
	// You may modify the parameter but not the return type
	// Write the native mongo database query in the comment below
	//
	/*
	 * db.comments.aggregate([
	 * { $group: { _id: "$title", count: { $sum: 1 } } },
	 * ])
	 */

	public int countComments() {

		GroupOperation groupAgg = Aggregation.group("title")
				.count().as("totla");

		Aggregation pipeline = Aggregation.newAggregation(groupAgg);

		List<Document> result = template.aggregate(pipeline, COLLECTION_COMMENTS, Document.class).getMappedResults();

		System.out.println("list of Doc results: " + result);

		List<Comment> commentList = result.stream()
				.map(d -> Comment.create(d))
				// .filter(Comment d -> {d.getTitle().equals(title);})
				.toList();

		return commentList.size();
	}

	// TODO: Task 8
	// Write a method to insert movie comments comments collection
	// Write the native mongo database query in the comment below
	//
	/*
	 * db.comment.insertOne({
	 * name: ?,
	 * rating: ?,
	 * comment: ?,
	 * title: ?
	 * })
	 */

	public Comment saveComment(Comment comment) {

		// Boolean isSaved = false;

		return template.insert(comment, COLLECTION_COMMENTS); //can save to mongo
	}

}
