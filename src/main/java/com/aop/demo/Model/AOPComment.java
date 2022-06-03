package com.aop.demo.Model;

import java.time.LocalDateTime;

public class AOPComment {

    private String text;
    private String author;
    private LocalDateTime commentTime;

    public AOPComment() {
    }

    public AOPComment(String text, String author, LocalDateTime commentTime) {
        this.text = text;
        this.author = author;
        this.commentTime = commentTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(LocalDateTime commentTime) {
        this.commentTime = commentTime;
    }

    @Override
    public String toString() {
        return "AOPComment{" +
                "text='" + text + '\'' +
                ", author='" + author + '\'' +
                ", commentTime=" + commentTime +
                '}';
    }
}
