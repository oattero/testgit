package com.code.test.myapplication1;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by user on 14/6/2559.
 */
public class Blog{
    String status;  int count;
    @SerializedName("count_total")
    int totalCount;
    int pages;
    List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    /*public void setPosts(List<Post> posts) {
        this.posts = posts;
    }*/
}
