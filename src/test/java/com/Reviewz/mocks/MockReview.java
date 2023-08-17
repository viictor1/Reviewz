package com.Reviewz.mocks;

import com.Reviewz.core.review.model.Review;
import com.Reviewz.infra.dataprovider.schema.review.ReviewSchema;
import com.Reviewz.infra.dataprovider.schema.user.UserSchema;
import com.Reviewz.mocks.MockUser;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class MockReview {

    public ReviewSchema mockEntity(Integer number){
        ReviewSchema review = new ReviewSchema();
        review.setId(number.longValue());
        review.setReview("Review " + number);
        review.setCategory("Category + " + number);
        review.setReviewedAt(new Date());
        review.setPublishedAt(new Date());
        review.setMadeBy("Made by " + number);
        review.setUser(new UserSchema());
        return review;
    }

    public Review mockDto(Integer number) throws Exception {
        Review review = new Review();
        review.setId(number.longValue());
        review.setReview("Review " + number);
        review.setCategory("Category + " + number);
        review.setReviewedAt(new Date());
        review.setPublishedAt(new Date());
        review.setMadeBy("Made by " + number);
        review.setUser(new UserSchema());
        return review;
    }

    public List<ReviewSchema> mockEntityList() {
        List<ReviewSchema> reviews = new ArrayList<ReviewSchema>();
        for (int i = 0; i < 14; i++) {
            reviews.add(mockEntity(i));
        }
        return reviews;
    }

    public List<Review> mockDtoList() throws Exception {
        List<Review> reviews = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            reviews.add(mockDto(i));
        }
        return reviews;
    }
}
