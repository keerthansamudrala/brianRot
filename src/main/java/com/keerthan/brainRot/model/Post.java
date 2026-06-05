package com.keerthan.brainRot.model;

import org.springframework.stereotype.Component;

@Component
public class Post {

    private int post_id;
    private String post_title;
    private int post_cockroaches;

    public Post(){

    }

    public Post(int post_id, String post_title, int post_cockroaches) {
        this.post_id = post_id;
        this.post_title = post_title;
        this.post_cockroaches = post_cockroaches;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public int getPost_cockroaches() {
        return post_cockroaches;
    }

    public void setPost_cockroaches(int post_cockroaches) {
        this.post_cockroaches = post_cockroaches;
    }

    @Override
    public String toString() {
        return "Post{" +
                "post_id=" + post_id +
                ", post_title='" + post_title + '\'' +
                ", post_cockroaches=" + post_cockroaches +
                '}';
    }
}
