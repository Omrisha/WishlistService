package il.ac.afeka.wishlistservice.logic;

import il.ac.afeka.wishlistservice.boundries.ProductBoundary;
import il.ac.afeka.wishlistservice.boundries.WishlistBoundary;
import il.ac.afeka.wishlistservice.enums.FilterByEnum;
import il.ac.afeka.wishlistservice.enums.SortByEnum;
import il.ac.afeka.wishlistservice.enums.SortOrderEnum;

public interface WishlistService {
    WishlistBoundary create(WishlistBoundary wishlist);

    WishlistBoundary getWishlistById(String email, String wishListName);

    void addProduct(String email, String wishListName, ProductBoundary productBoundary);

    WishlistBoundary[] getAll(FilterByEnum filterBy, String filterValue, SortByEnum sortBy, SortOrderEnum sortOrder, int size, int page);

    void deleteAll();
}
