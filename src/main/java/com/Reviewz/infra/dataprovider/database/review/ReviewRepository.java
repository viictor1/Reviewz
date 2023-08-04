package com.Reviewz.infra.dataprovider.database.review;

import com.Reviewz.infra.dataprovider.schema.review.ReviewSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewSchema, Long> {

    @Query(value = "SELECT r FROM ReviewSchema r WHERE r.user.id = :userId")
    List<ReviewSchema> getReviewsByUserId(@Param("userId") UUID userId);
}
