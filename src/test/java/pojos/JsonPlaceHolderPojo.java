package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonPlaceHolderPojo {

    /*
    1) Create private variables for every key
    2) Create constructor with all parameters, and without any parameters
    3) Create getters and setters
    4) Create toString() method
     */

    //    1) Create private variables for every key
    private Integer userId;
    private String title;
    private Boolean completed;

//    2) Create constructor with all parameters, and without any parameters

    public JsonPlaceHolderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    public JsonPlaceHolderPojo() {
    }

//    3) Create getters and setters

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

//    4) Create toString() method

    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
    /*
    How do we handle different key-values in response body?
    I use @JsonIgnoreProperties(ignoreUnknown = true) at the top of thePojo class.
    It comes from "org.codehaus.jackson.annotate.JsonIgnoreProperties"
     */




}