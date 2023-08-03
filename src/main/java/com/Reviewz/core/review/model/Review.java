package com.Reviewz.core.review.model;

import com.Reviewz.core.genericException.ValidationError;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Review {

    private Long id;
    private String title;
    private String madeBy;
    private String genre;
    private int stars;
    private String review;
    private Date publishedAt;
    private Date reviewedAt;
    private UserSchema user;

    public Review() {
    }

    public Review(Long id, String title, String madeBy, String genre, int stars, String review, Date publishedAt, Date reviewedAt, UUID userId) throws ValidationError {
        this.setId(id);
        this.setTitle(title);
        this.setMadeBy(madeBy);
        this.setGenre(genre);
        this.setStars(stars);
        this.setReview(review);
        this.setPublishedAt(publishedAt);
        this.setReviewedAt(reviewedAt);
        this.setUser(user);
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

    public UserSchema getUser() {
        return user;
    }

    public void setUser(UserSchema user) throws ValidationError {
        if(user == null) {
            throw new ValidationError("The user cannot be null");
        }
        this.user = user;
    }

    public String getMadeBy() {
        return madeBy;
    }

    public void setMadeBy(String madeBy) {
        this.madeBy = madeBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review review1)) return false;

        if (stars != review1.stars) return false;
        if (!Objects.equals(id, review1.id)) return false;
        if (!Objects.equals(title, review1.title)) return false;
        if (!Objects.equals(madeBy, review1.madeBy)) return false;
        if (!Objects.equals(genre, review1.genre)) return false;
        if (!Objects.equals(review, review1.review)) return false;
        if (!Objects.equals(publishedAt, review1.publishedAt)) return false;
        if (!Objects.equals(reviewedAt, review1.reviewedAt)) return false;
        return Objects.equals(user, review1.user);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (madeBy != null ? madeBy.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + stars;
        result = 31 * result + (review != null ? review.hashCode() : 0);
        result = 31 * result + (publishedAt != null ? publishedAt.hashCode() : 0);
        result = 31 * result + (reviewedAt != null ? reviewedAt.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
