package com.supermetrics.client.sm;

/**
 * @author Mehdi Shahdoost
 */
public class PostData {

    private int page;
    private Post posts[];

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Post[] getPosts() {
        return posts;
    }

    public void setPosts(Post[] posts) {
        this.posts = posts;
    }
}
