package com.example.invoices.models.dto;

import com.example.ringate.models.entities.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProfileDto {

    public ProfileDto(Profile profile) {
        if (profile != null) {
            this.setId(profile.getId());
            this.setFirstName(profile.getFirstName());
            this.setGender(profile.getGender());
            this.setId(profile.getId());
            this.setFirstName(profile.getFirstName());
            this.setLastName(profile.getLastName());
        }
    }

    private Long id;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private String bio;
    private String profilePictureUrl;
}

//    // Getters and Setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public String getBio() {
//        return bio;
//    }
//
//    public void setBio(String bio) {
//        this.bio = bio;
//    }
//
//    public String getProfilePictureUrl() {
//        return profilePictureUrl;
//    }
//
//    public void setProfilePictureUrl(String profilePictureUrl) {
//        this.profilePictureUrl = profilePictureUrl;
//    }