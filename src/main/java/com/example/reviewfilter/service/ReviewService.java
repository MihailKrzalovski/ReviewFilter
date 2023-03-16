package com.example.reviewfilter.service;

import com.example.reviewfilter.model.Review;

import java.io.IOException;
import java.util.List;

public interface ReviewService {
   List<Review> mapperJsonToClass() throws IOException;
   List<Review> filterReviews( String minRating, String textPrioritize, String rateSort, String dateSort ) throws IOException;
}
