package com.Reviewz.core.review.model;

import com.Reviewz.core.user.exception.ValidationError;

import java.util.Date;
import java.util.UUID;

public class Review {

    private Long id;
    private String title;
    private String genre;
    private int stars;
    private String review;
    private Date publishedAt;
    private Date reviewedAt;
    private UUID userId;

    public Review() {
    }

    public Review(Long id, String title, String genre, int stars, String review, Date publishedAt, Date reviewedAt, UUID userId) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.stars = stars;
        this.review = review;
        this.publishedAt = publishedAt;
        this.reviewedAt = reviewedAt;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws ValidationError {
        if(title == null || title.isEmpty()) {
            throw new ValidationError("The title cannot be blank or empty");
        }
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) throws ValidationError {
        if(genre == null || genre.isEmpty()) {
            throw new ValidationError("The genre cannot be blank or empty");
        }
        this.genre = genre;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) throws ValidationError {
        if(stars < 0 || stars > 5) {
            throw new ValidationError("The stars has to been between 0 and 5");
        }
        this.stars = stars;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Date getReviewedAt() {
        return reviewedAt;
    }

    public void setReviewedAt(Date reviewedAt) throws ValidationError {
        if(reviewedAt == null) {
            throw new ValidationError("The reviedAt date cannot be blank or empty");
        }
        this.reviewedAt = reviewedAt;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) throws ValidationError {
        if(userId == null) {
            throw new ValidationError("The userId cannot be blank or empty");
        }
        this.userId = userId;
    }
}
