package com.freelancer.RealEstate.model;

import javax.validation.constraints.Email;
import java.util.List;

/**
 * This Class is used for owner details.
 *
 * @author Vikas Pawar
 */
public class OwnerDetails {
    private String name;
    private List<String> mobile;

    private List<String> email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMobile() {
        return mobile;
    }

    public void setMobile(List<String> mobile) {
        this.mobile = mobile;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }
}
