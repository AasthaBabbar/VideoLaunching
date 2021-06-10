package com.example.videolaunching.model;

public class Video {

    private String videoId;
    private String videoDescription;
    private String subjectId;

    public Video(String videoId, String videoDescription, String subjectId) {
        this.videoId = videoId;
        this.videoDescription = videoDescription;
        this.subjectId = subjectId;
    }

    public String getVideoId() {
        return videoId;
    }


    public String getVideoDescription() {
        return videoDescription;
    }


    public String getSubjectId() {
        return subjectId;
    }

}
