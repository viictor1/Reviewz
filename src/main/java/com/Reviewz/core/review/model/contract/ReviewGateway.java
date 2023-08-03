package com.Reviewz.core.review.model.contract;

import com.Reviewz.infra.dataprovider.schema.review.ReviewSchema;

public interface ReviewGateway {
    void create (ReviewSchema reviewSchema);
}
