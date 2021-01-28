package il.ac.afeka.wishlistservice.data;

import javax.validation.constraints.Email;

public class UserEntity {
    @Email
    private String email;

    public UserEntity() {
    }

    public UserEntity(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "email='" + email + '\'' +
                '}';
    }
}
