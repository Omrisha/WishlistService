package il.ac.afeka.wishlistservice.logic;

import il.ac.afeka.wishlistservice.boundries.WishlistBoundary;

public interface WishlistService {
    WishlistBoundary create(WishlistBoundary wishlist);
}
