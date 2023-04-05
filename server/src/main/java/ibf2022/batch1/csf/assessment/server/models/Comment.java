package ibf2022.batch1.csf.assessment.server.models;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Comment {
    private String name;
    private String rating;
    private String comment;
    private String title;
    private Comment[] comments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Comment[] getComments() {
        return comments;
    }

    public void setComments(Comment[] comments) {
        this.comments = comments;
    }

    public static Comment create(Document doc) {
        Comment comment = new Comment();
        comment.setName(doc.getString("name"));
        comment.setRating(doc.getString("rating"));
        comment.setComment(doc.getString("comment"));
        comment.setTitle(doc.getString("title"));

        return comment;
    }

    // public Comment createToComment(JsonObject json) {
    //     Comment comment = new Comment();
    //     comment.setName(json.getString("name"));
    //     comment.setRating(json.getString("rating"));
    //     comment.setComment(json.getString("comment"));
    //     comment.setTitle(json.getString("title"));

    //     return comment;
    // }

    public JsonObject toJSON() {
        JsonObject jobj = Json.createObjectBuilder()
                .add("name", getName())
                .add("rating", getRating())
                .add("comment", getComment())
                .add("title", getTitle())
                .build();
        return jobj;
    }

}
