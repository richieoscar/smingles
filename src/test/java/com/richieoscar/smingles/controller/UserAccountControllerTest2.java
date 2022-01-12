package com.richieoscar.smingles.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.richieoscar.smingles.entity.UserAccount;
import com.richieoscar.smingles.entity.UserInterest;
import com.richieoscar.smingles.repository.UserAccountRepository;
import com.richieoscar.smingles.repository.UserInterestRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserAccountControllerTest2 {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserAccountRepository userAccountRepository;

    @MockBean
    UserInterestRepository userInterestRepository;

    @Test
    public void registerUser() throws Exception {
        String name = "test";
        UserAccount userAccount = new UserAccount(1, "octavia", "password", 22, "octavia@gmail.com", "female", "0902213457", "New York", "USA", null);
        mockMvc.perform(
                        post("/api/users/register-user")
                                .content(asJsonString(name))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn()
                .getRequest();


    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addUserInterest() throws Exception {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(2);
        UserInterest userInterest = new UserInterest();
        userInterest.setUserId(userAccount.getId());
        userInterest.setUserAccount(userAccount);
        userInterest.setAbout("Introvert");
        userInterest.setHobbies("Video games");
        userInterest.setLikes("Big ass");
        userInterest.setProfileUrl("jjk");
        userInterest.setDisLikes("smoking");

        mockMvc.perform(
                        post("/api/user/add-interest")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(userInterest))
                )
                .andExpect(status().isOk());


    }

    @Test
    public void getUserAccount() {
    }

    @Test
    public void getUsers() {
    }

    @Test
    public void deleteUserInterest() {
    }

    @Test
    public void findMatches() {
    }
}