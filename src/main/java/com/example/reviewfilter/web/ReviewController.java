package com.example.reviewfilter.web;

import com.example.reviewfilter.model.Review;
import com.example.reviewfilter.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class ReviewController {
    private final ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
       this.reviewService = reviewService;
    }
    @GetMapping("/reviews")
    public String returnReviews(Model model, @RequestParam  String rateSort,
                                             @RequestParam  String minRatings,
                                             @RequestParam  String dateSort,
                                             @RequestParam String textPrioritize ) throws IOException {
        model.addAttribute("reviews", reviewService.filterReviews(minRatings,textPrioritize,rateSort,dateSort));

        return"index";
    }

    @GetMapping("/index")
    public String showInitialPage(){
        return"index";
    }
}
