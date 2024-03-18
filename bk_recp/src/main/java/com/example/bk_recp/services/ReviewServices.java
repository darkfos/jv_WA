package com.example.bk_recp.services;

import com.example.bk_recp.entity.Review;
import com.example.bk_recp.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServices {

    private final ReviewRepository reviewRepository;
    private final NoteService noteService;

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
            Review rnd_review = all_review.get(
                    rnd_numbers.nextInt(all_review.size()));

            if (!reviews.contains(rnd_review)) {
                reviews.add(
                        rnd_review
                );
            } else {
                i--;
            }
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
    public boolean add_review(Review review, Principal principal) {
        review.setUser(
                noteService.getUserByPrincipal(principal)
        );

        //Created more 1 review
        ArrayList<Review> all_review = (ArrayList<Review>) reviewRepository.findAll();
        for (Review rev : all_review) {
            if (rev.getUser().equals(noteService.getUserByPrincipal(principal))) {
                return false;
            }
        }
        reviewRepository.save(review);
        return true;
    }
}
