package com.richieoscar.smingles.controller;

import com.richieoscar.smingles.entity.UserAccount;
import com.richieoscar.smingles.entity.UserInterest;
import com.richieoscar.smingles.exception.UserAccountNotFoundException;
import com.richieoscar.smingles.repository.UserAccountRepository;
import com.richieoscar.smingles.repository.UserInterestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserAccountController {

    private final UserAccountRepository userAccountRepository;
    private final UserInterestRepository userInterestRepository;

    @Autowired
    public UserAccountController(UserAccountRepository userAccountRepository, UserInterestRepository userInterestRepository) {
        this.userAccountRepository = userAccountRepository;
        this.userInterestRepository = userInterestRepository;
    }

    @PostMapping("/users/register-user")
    public UserAccount registerUser(@RequestBody UserAccount userAccount) {
        log.info("Registering User");
        try {
            return userAccountRepository.save(userAccount);
        } catch (ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PostMapping("/user/add-interest")
    public UserInterest addUserInterest(@RequestBody UserInterest interest) {
        try {
            Optional<UserAccount> optionalUserAccount = userAccountRepository.findById(interest.getUserId());
            if (optionalUserAccount.isPresent()) {
                UserAccount userAccount = optionalUserAccount.get();
                interest.setUserAccount(userAccount);
                return userInterestRepository.save(interest);
            } else
                throw new UserAccountNotFoundException(String.format("User Account with %d does not found", interest.getUserId()));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping("/user/get-user/{id}")
    public UserAccount getUserAccount(@PathVariable("id") int id) {
        try {
            Optional<UserAccount> optionalUserAccount = userAccountRepository.findById(id);
            if (optionalUserAccount.isPresent()) {
                return optionalUserAccount.get();
            } else throw new UserAccountNotFoundException(String.format("UserAccount with %d not found", id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    @GetMapping("/users/get-all")
    public List<UserAccount> getUsers() {
        return userAccountRepository.findAll();
    }

    @DeleteMapping("/user/interest/delete/{interestId}")
    public String deleteUserInterest(@PathVariable("interestId") int id) {
        try {

            Optional<UserInterest> optionalUserInterest = userInterestRepository.findById(id);
            if (optionalUserInterest.isPresent()) {
                userInterestRepository.delete(optionalUserInterest.get());
                return "User Interest Deleted Successfully";
            } else throw new IllegalStateException(String.format("user interest with %d not found", id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }

    }

    @GetMapping("/users/find-matches/{id}")
    public List<UserAccount> findMatches(@PathVariable("id") int id) {
        try {

            Optional<UserAccount> optionalUserAccount = userAccountRepository.findById(id);
            if (optionalUserAccount.isPresent()) {
                UserAccount userAccount = optionalUserAccount.get();
                return userAccountRepository.findMatches(
                        userAccount.getAge(),
                        userAccount.getCity(),
                        userAccount.getCountry(),
                        userAccount.getId()
                );
            } else throw new UserAccountNotFoundException(String.format("User account with %d not found", id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
