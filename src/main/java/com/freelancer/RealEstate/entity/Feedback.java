package com.freelancer.RealEstate.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * This is used for take feedback data.
 *
 * @author Vikas Pawar
 */
@Entity
@Table(name = "tab_feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private Integer feedbackId;

    @Column(name = "email")
    @NotNull(message = "email can not be blank")
    private String email;

    @Column(name = "msg", columnDefinition = "varchar(50) default null")
    private String msg;

    @CreationTimestamp
    @Column(name = "created_on")
    private Date created_on;

    @Column(name = "sender_type", columnDefinition = "varchar(50) default 'feedback'")
    private String sender_type;

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public String getSender_type() {
        return sender_type;
    }

    public void setSender_type(String sender_type) {
        this.sender_type = sender_type;
    }
}
