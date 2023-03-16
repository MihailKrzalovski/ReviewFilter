package com.example.reviewfilter.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review implements Comparable<Review>, Serializable {
    private float id;
    private String reviewId;
    private String reviewFullText;
    private String reviewText;
    private float numLikes;
    private float numComments;
    private float numShares;
    private float rating;
    private String reviewCreatedOn;
    private String reviewCreatedOnDate;
    private float reviewCreatedOnTime;
    private String reviewerId = null;
    private String reviewerUrl = null;
    private String reviewerName;
    private String reviewerEmail = null;
    private String sourceType;
    private boolean isVerified;
    private String source;
    private String sourceName;
    private String sourceId;
    ArrayList< Object > tags = new ArrayList < Object > ();
    private String href = null;
    private String logoHref = null;
    ArrayList < Object > photos = new ArrayList < Object > ();


    @Override
    public int compareTo(Review o) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
        LocalDateTime dateTime = LocalDateTime.parse(this.reviewCreatedOnDate, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(o.reviewCreatedOnDate, formatter);

        int result = Float.compare(this.rating,o.rating);
        int dateResult = 0;
        if(result == 0){
                dateResult = dateTime.compareTo(dateTime2);
                return dateResult;
        }else{
            return result;
        }
    }

}
