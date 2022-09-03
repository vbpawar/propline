package com.freelancer.RealEstate.repository;

import com.freelancer.RealEstate.entity.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialRepository extends JpaRepository<Testimonial, Integer> {
}
