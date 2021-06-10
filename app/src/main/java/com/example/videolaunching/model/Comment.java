package com.example.videolaunching.model;

public class Comment {

    private int commentId;
    private String commentDescription;
    private String commentarName;
    private double rating;
    private String videoId;

    public Comment(int commentId, String commentDescription, String commentarName, double rating, String videoId) {
        this.commentId = commentId;
        this.commentDescription = commentDescription;
        this.commentarName = commentarName;
        this.rating = rating;
        this.videoId = videoId;
    }

    public int getCommentId() {
        return commentId;
    }


    public String getCommentDescription() {
        return commentDescription;
    }

    public void setCommentDescription(String commentDescription) {
        this.commentDescription = commentDescription;
    }

    public String getCommentarName() {
        return commentarName;
    }

    public void setCommentarName(String commentarName) {
        this.commentarName = commentarName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
