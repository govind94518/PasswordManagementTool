package com.epam.dto;

import javax.validation.constraints.Pattern;

public class RegisterDTO {

    @Pattern(regexp = "(?=.*[A-Z])(?=\\S+$).{5,20}$", message = "Size:{4, 20}, Atleast 1 {upper}!")
    private String groupName;

    @Pattern(regexp = "(?=.*[A-Z])(?=.*[0-9])(?=\\S+$).{5,20}$", message = "Size:{4, 20}, Atleast 1 {upper,numeric}!")
    private String userName;

    @Pattern(regexp = "((([A-Za-z]{3,9}:(?:\\/\\/)?)" +
            "(?:[-;:&=\\+\\$,\\w]+@)?[A-Za-z0-9.-]" +
            "+|(?:www.|[-;:&=\\+\\$,\\w]+@)" +
            "[A-Za-z0-9.-]+)((?:\\/[\\+~%\\/.\\w-_]*)" +
            "?\\??(?:[-\\+=&;%@.\\w_]*)#?(?:[\\w]*))?)", message = "Please enter valid url-{http://www.epam.com}!")
    private String url;

    @Pattern(regexp = "^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=\\S+$).{5,20}$", message = "Size:{5, 20}, Atleast 1 {upper,lower,numeric}!")
    private String password;

    public RegisterDTO() {
    }

    public RegisterDTO(String groupName, String userName, String url, String password) {
        this.groupName = groupName;
        this.userName = userName;
        this.url = url;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

}
