package com.richieoscar.smingles.repository;

import com.richieoscar.smingles.entity.UserInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInterestRepository extends JpaRepository<UserInterest, Integer> {
}
