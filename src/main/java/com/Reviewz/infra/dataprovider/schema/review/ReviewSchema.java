package com.Reviewz.infra.dataprovider.schema.review;

import com.Reviewz.core.review.model.Review;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "reviews")
public class ReviewSchema {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "made_by")
    private String madeBy;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private int stars;

    @Column
    private String review;

    @Column(name = "published_at")
    private Date publishedAt;

    @Column(name = "reviewed_at", nullable = false)
    private Date reviewedAt;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserSchema user;

    public ReviewSchema() {
    }

    public ReviewSchema(Review review) {
        this.title = review.getTitle();
        this.madeBy = review.getMadeBy();
        this.category = review.getCategory();
        this.review = review.getReview();
        this.publishedAt = review.getPublishedAt();
        this.reviewedAt = review.getReviewedAt();
        this.stars = review.getStars();
        this.user = review.getUser();
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
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

    public void setReviewedAt(Date reviewedAt) {
        this.reviewedAt = reviewedAt;
    }

    public UserSchema getUser() {
        return user;
    }

    public void setUser(UserSchema user) {
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
        if (!(o instanceof ReviewSchema that)) return false;

        if (stars != that.stars) return false;
        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(title, that.title)) return false;
        if (!Objects.equals(madeBy, that.madeBy)) return false;
        if (!Objects.equals(category, that.category)) return false;
        if (!Objects.equals(review, that.review)) return false;
        if (!Objects.equals(publishedAt, that.publishedAt)) return false;
        if (!Objects.equals(reviewedAt, that.reviewedAt)) return false;
        return Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (madeBy != null ? madeBy.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + stars;
        result = 31 * result + (review != null ? review.hashCode() : 0);
        result = 31 * result + (publishedAt != null ? publishedAt.hashCode() : 0);
        result = 31 * result + (reviewedAt != null ? reviewedAt.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
