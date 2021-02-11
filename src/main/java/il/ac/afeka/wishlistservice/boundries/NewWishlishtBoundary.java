package il.ac.afeka.wishlistservice.boundries;

public class NewWishlishtBoundary {
    private UserBoundary user;
    private String name;

    public NewWishlishtBoundary() {
    }

    public NewWishlishtBoundary(UserBoundary user, String name) {
        this.user = user;
        this.name = name;
    }

    public UserBoundary getUser() {
        return user;
    }

    public void setUser(UserBoundary user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
