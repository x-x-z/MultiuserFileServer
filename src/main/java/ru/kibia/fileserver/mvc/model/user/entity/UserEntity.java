package ru.kibia.fileserver.mvc.model.user.entity;

import ru.kibia.fileserver.mvc.model.file.entity.SharedFileEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -2842696775955969066L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="users_id_seq")
    @SequenceGenerator(name="users_id_seq", sequenceName="users_sequence")
    @Column(updatable = false)
    private long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
	private String firstName;

    @Column(nullable = false)
	private String middleName;

    @Column(nullable = false)
	private String lastName;

    @Column(nullable = false)
	private String role;

	private int loginCount;

	private int trafficLimit;

    public UserEntity() {
    }

    public UserEntity(long id, String login, String password, String firstName, String middleName, String lastName,
                      String role, int loginCount, int trafficLimit) {
        this.id = id;
        this.login = login;
		this.password = password;
		this.firstName = firstName;
        this.lastName = lastName;
		this.middleName = middleName;
		this.role = role;
        this.loginCount = loginCount;
        this.trafficLimit = trafficLimit;
    }

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

    public String getRole() {
        return role;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public int getTrafficLimit() {
        return trafficLimit;
    }

    @Override
    public String toString() {
        return String.format(
                "UserEntity[id=%d, login='%s', firstName='%s', middleName='%s', lastName='%s', role='%s']",
                id, login, firstName, middleName, lastName, role);
    }

}