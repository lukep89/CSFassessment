package ibf2022.batch1.csf.assessment.server.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

	@Autowired
	private MongoTemplate template;

	// TODO: Task 5
	// You may modify the parameter but not the return type
	// Write the native mongo database query in the comment below
	//
	/*
	 * db.comments.aggregate([
	 * { $group: { _id: "$title", count: { $sum: 1 } } },
	 * { $sort: { count: -1 } }
	 * ])
	 */

	public int countComments(Object param) {
		return 0;
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

	public static String COLLECTION_COMMENTS = "comments";

}
