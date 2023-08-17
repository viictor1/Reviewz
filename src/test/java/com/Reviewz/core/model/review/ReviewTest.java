package com.Reviewz.core.model.review;

import com.Reviewz.core.genericException.ValidationError;
import com.Reviewz.core.review.model.Review;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReviewTest {
    Review review = new Review();

    @Test
    public void setTitleTest() throws Exception {
        review.setTitle("Test Title");
        assertEquals("Test Title", review.getTitle());
    }

    @Test
    public void setEmptyTitleTest() {
        try {
            review.setTitle("");
        } catch (Exception e) {
            assertEquals(e.getClass(), ValidationError.class);
        }
    }

    @Test
    public void setCategoryTest() throws Exception {
        review.setCategory("Books");
        assertEquals("Books", review.getCategory());
    }

    @Test
    public void setEmptyCategoryTest() {
        try {
            review.setCategory("");
        } catch (Exception e) {
            assertEquals(e.getClass(), ValidationError.class);
        }
    }

    @Test
    public void setStarsTest() throws Exception {
        review.setStars(3);
        assertEquals(3, review.getStars());
    }

    @Test
    public void setInvalidStarsTest() {
        assertThrows(ValidationError.class, () -> review.setStars(6));
        assertThrows(ValidationError.class, () -> review.setStars(-1));
    }

    @Test
    public void setReviewTest() {
        review.setReview("This is a test review.");
        assertEquals("This is a test review.", review.getReview());
    }

    @Test
    public void setPublishedAtTest() {
        Date publishedAt = new Date();
        review.setPublishedAt(publishedAt);
        assertEquals(publishedAt, review.getPublishedAt());
    }

    @Test
    public void setReviewedAtTest() throws Exception {
        Date reviewedAt = new Date();
        review.setReviewedAt(reviewedAt);
        assertEquals(reviewedAt, review.getReviewedAt());
    }

    @Test
    public void setEmptyReviewedAtTest() {
        assertThrows(ValidationError.class, () -> review.setReviewedAt(null));
    }

    @Test
    public void setUserTest() throws Exception {
        UserSchema user = new UserSchema();
        review.setUser(user);
        assertEquals(user, review.getUser());
    }

    @Test
    public void setNullUserTest() {
        assertThrows(ValidationError.class, () -> review.setUser(null));
    }

}
