package ru.kibia.fileserver.mvc.model.user.bean;

/**
 * Created by kostin on 22.11.2016.
 */
public class User {

    private final long id;
    private final String login;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private Role role;
    private int loginCount;
    private int trafficLimit;

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public Role getRole() {
        return role;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public int getTrafficLimit() {
        return trafficLimit;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    public void setTrafficLimit(int trafficLimit) {
        this.trafficLimit = trafficLimit;
    }

    private User(Builder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.middleName = builder.middleName;
        this.lastName = builder.lastName;
        this.role = builder.role;
        this.loginCount = builder.loginCount;
        this.trafficLimit = builder.trafficLimit;
    }

    public static class Builder {

        private final long id;
        private final String login;
        private final String password;
        private String firstName;
        private String middleName;
        private String lastName;
        private Role role;
        private int loginCount;
        private int trafficLimit;

        public Builder(long id, String login, String password) {
            this.id= id;
            this.login = login;
            this.password = password;
        }

        public Builder(String login, String password) {
            this.id= 0;
            this.login = login;
            this.password = password;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setRole(Role role) {
            this.role = role;
            return this;
        }

        public Builder setLoginCount(int loginCount) {
            this.loginCount = loginCount;
            return this;
        }

        public Builder setTrafficLimit(int trafficLimit) {
            this.trafficLimit = trafficLimit;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }
}
