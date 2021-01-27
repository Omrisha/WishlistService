package il.ac.afeka.wishlistservice.logic;

import il.ac.afeka.wishlistservice.boundries.WishlistBoundary;
import il.ac.afeka.wishlistservice.data.WishlistDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistServiceImpl implements WishlistService {
    private WishlistDao wishlistDao;

    @Autowired
    public WishlistServiceImpl(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    @Override
    public WishlistBoundary create(WishlistBoundary wishlist) {
        System.err.println("From Service: " + wishlist);
        return null;
    }
}
