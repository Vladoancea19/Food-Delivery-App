package User;

import java.util.Objects;

public class User {
    private int userId;
    protected String userNameSurname;
    protected String userEmail;
    protected String userPassword;
    protected String userPhoneNumber;

    public User(int userId, String userNameSurname, String userEmail, String userPassword, String userPhoneNumber) {
        this.userId = userId;
        this.userNameSurname = userNameSurname;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPhoneNumber = userPhoneNumber;
    }

    public User(String userNameSurname, String userEmail, String userPassword, String userPhoneNumber) {
        this.userNameSurname = userNameSurname;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPhoneNumber = userPhoneNumber;
    }

    public User(String userPassword) {
        this.userPassword = userPassword;
    }

    public User() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserNameSurname() {
        return userNameSurname;
    }

    public void setUserNameSurname(String userNameSurname) {
        this.userNameSurname = userNameSurname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userNameSurname='" + userNameSurname + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return userId == user.userId && userNameSurname.equals(user.userNameSurname) && userEmail.equals(user.userEmail) && userPassword.equals(user.userPassword) && userPhoneNumber.equals(user.userPhoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userNameSurname, userEmail, userPassword, userPhoneNumber);
    }
}
