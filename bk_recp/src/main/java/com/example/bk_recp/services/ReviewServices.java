package com.example.bk_recp.services;

import com.example.bk_recp.entity.Review;
import com.example.bk_recp.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServices {

    private final ReviewRepository reviewRepository;


    /**
     * Получаем все отзывы
     * @return
     */
    public List<Review> get_reviews() {

        List<Review> all_review = reviewRepository.findAll();
        log.info("Get all reviews");

        if (all_review.size() < 3) {
            return all_review;
        }

        //Get 3 random review
        Random rnd_numbers = new Random();
        List<Review> reviews = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            reviews.add(
                    all_review.get(rnd_numbers.nextInt(all_review.size()))
            );
        }

        return reviews;
    }

    /**
     * Получение review по id
     * @param id_review
     * @return
     */
    public Review get_review_by_id(Long id_review) {
        Review unique_review = reviewRepository.findById(id_review).orElse(null);
        log.info("Get review by id");
        return unique_review;
    }

    /**
     * Обновление информации об отзыве
     * @param review
     */
    public void update_review(Review review) {
        reviewRepository.save(review);
        log.info("Update review");
    }

    /**
     * Удаление review по id
     * @param id_review
     */
    public void delete_review(Long id_review) {
        reviewRepository.deleteById(id_review);
        log.info("Delete review");
    }

    /**
     * Добавление review
     * @param review
     */
    public void add_review(Review review) {
        reviewRepository.save(review);
    }
}
