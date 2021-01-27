package il.ac.afeka.wishlistservice.boundries;

import il.ac.afeka.wishlistservice.data.UserEntity;

public class UserBoundary {
    private String email;

    public UserBoundary() {
    }

    public UserBoundary(String email) {
        this.email = email;
    }

    public UserBoundary(UserEntity entity) {
        if (entity.getEmail() != null)
            setEmail(entity.getEmail());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserBoundary{" +
                "email='" + email + '\'' +
                '}';
    }
}
