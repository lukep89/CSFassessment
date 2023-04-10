package ibf2022.batch1.csf.assessment.server.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import ibf2022.batch1.csf.assessment.server.models.Comment;
import ibf2022.batch1.csf.assessment.server.models.Review;

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
	 * { $match: { "title": ? } },
	 * { $group: { _id: "$title", count: { $sum: 1 } } },
	 * ])
	 */

	public int countComments(String title) {

		int commentCount = 0;

		Criteria criteria = Criteria.where("title").is(title);

		MatchOperation matchAgg = Aggregation.match(criteria);

		GroupOperation groupAgg = Aggregation.group("title")
				.count().as("total");

		Aggregation pipeline = Aggregation.newAggregation(matchAgg, groupAgg);

		List<Document> result = template.aggregate(pipeline, COLLECTION_COMMENTS, Document.class).getMappedResults();
		// able to get list of result (title and it's count)
		// System.out.println(">>>> list of Doc results: " + result);

		for (Document d : result) {

			Review r = Review.create(d);

			commentCount = r.getCommentCount();
		}

		return commentCount;
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

		return template.insert(comment, COLLECTION_COMMENTS); // can save to mongo
	}

}
