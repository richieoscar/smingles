package com.richieoscar.smingles.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Username is required")
    @Length(min = 5, max = 30, message = "Username should be min of 5 Characters and Max or 30")
    private String userName;
    @NotBlank(message = "Password is required")
    @Length(min = 8, max = 30, message = "Password should minimum of 8 characters and maximum of 30")
    private String password;
    @NotNull
    @Min(value = 18, message = "Min age is 18")
    @Max(value = 30, message = "Max age is 30")
    private int age;
    @NotBlank(message = "Email is required")
    @Email(message = "Provide a valid email")
    private String email;
    private String gender;
    @NotBlank(message = "Phone number required")
    @Length(min = 11, max = 11, message = "Phone Number should be  11 characters")
    private String phoneNumber;
    private String city;
    private String country;
    @OneToOne(mappedBy = "userAccount")
    private UserInterest interest;

}
