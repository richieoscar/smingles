package com.richieoscar.smingles.repository;

import com.richieoscar.smingles.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
    @Query("from UserAccount where age=:userAge and  city=:userCity and country=:userCountry and id<>:userId")
    List<UserAccount> findMatches(@Param("userAge")int age, @Param("userCity") String city, @Param("userCountry") String country, @Param("userId")int id);
}
