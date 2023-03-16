package com.example.reviewfilter.service;

import com.example.reviewfilter.model.Review;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Override
    public List<Review> mapperJsonToClass() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Review> reviews  = objectMapper.readValue(new File("C:\\Users\\Cule 5\\Desktop\\TestZadaca2\\src\\main\\resources\\static\\reviews.json"), new TypeReference<List<Review>>(){}   );
       return reviews;
    }

    @Override
    public List<Review> filterReviews(String minRating, String textPrioritize, String rateSort,String dateSort) throws IOException {
        List<Review> reviews = this.mapperJsonToClass();
        List<Review> filtered = new ArrayList<>(); // reviews so pogoden minRating
        List<Review> textYes = new ArrayList<>();  // se popolnuva dokolku TextPrioritize = yes
        List<Review> textNo = new ArrayList<>();   // se popolnuva dokolku TextPrioritize = yes
        List<Review> krajna = new ArrayList<>();   // krajnata lista pri textPriritize = yes
        for (Review review : reviews) {
            if (review.getRating() >= Integer.parseInt(minRating)) {
                filtered.add(review);
            }
        }
        if (textPrioritize.equals("yes")){
            for (Review review : filtered) {
                if (!(review.getReviewFullText().equals(""))) {
                    textYes.add(review);
                } else {
                    textNo.add(review);
                }
            }
            textYes.sort(Review::compareTo); //sorted ascending
            textNo.sort(Review::compareTo); //sorted ascending
            if (rateSort.equals("highestFirst")){
                Collections.reverse(textYes);
                Collections.reverse(textNo);
            }
            krajna = Stream.concat(textYes.stream(),textNo.stream()).collect(Collectors.toList());
            return krajna;
        }else{
            //textPrioritize == no, continue
            filtered.sort(Review::compareTo);
            if (rateSort.equals("highestFirst")){
                Collections.reverse(filtered);
            }
           return filtered;
        }

    }
}
