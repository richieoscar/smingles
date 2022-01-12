package com.richieoscar.smingles.controller;

import com.richieoscar.smingles.entity.UserAccount;
import com.richieoscar.smingles.repository.UserAccountRepository;
import com.richieoscar.smingles.repository.UserInterestRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class UserAccountControllerTest {

    @Mock
    UserAccountRepository userAccountRepository;

    @Mock
    UserInterestRepository userInterestRepository;


    @InjectMocks
    UserAccountController userAccountController;

    @Test
    void registerUser() {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(20);
        UserAccount savedAccount = new UserAccount();
        savedAccount.setId(20);
        when(userAccountRepository.save(userAccount)).thenReturn(savedAccount);
        UserAccount registeredUser = userAccountController.registerUser(userAccount);
        assertNotNull(registeredUser);
        assertNotNull(registeredUser.getId());
        assertEquals(savedAccount.getId(), registeredUser.getId());

    }

    @Test
    @Disabled
    void addUserInterest() {
    }

    @Test
    @Disabled
    void getUserAccount() {
    }

    @Test
    @Disabled
    void getUsers() {
    }

    @Test
    @Disabled
    void deleteUserInterest() {
    }

    @Test
    @Disabled
    void findMatches() {
    }
}