package com.Reviewz.infra.dataprovider.database.review;

import com.Reviewz.infra.dataprovider.schema.review.ReviewSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewSchema, Long> {
}
