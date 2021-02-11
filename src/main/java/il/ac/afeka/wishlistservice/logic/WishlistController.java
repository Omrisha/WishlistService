package il.ac.afeka.wishlistservice.logic;

import il.ac.afeka.wishlistservice.boundries.AddProductBoundary;
import il.ac.afeka.wishlistservice.boundries.ProductBoundary;
import il.ac.afeka.wishlistservice.boundries.WishlistBoundary;
import il.ac.afeka.wishlistservice.enums.FilterByEnum;
import il.ac.afeka.wishlistservice.enums.SortByEnum;
import il.ac.afeka.wishlistservice.enums.SortOrderEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Filter;

@RestController
public class WishlistController {
    private WishlistService service;

    @Autowired
    public WishlistController(WishlistService service) {
        this.service = service;
    }

    @RequestMapping(path = "/wishlist",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public WishlistBoundary create(@RequestBody WishlistBoundary wishlist) {
        return this.service.create(wishlist);
    }

    @RequestMapping(path = "/wishlist/{email}/{wishlistName}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public WishlistBoundary getWishlistById(@PathVariable("email") String email,
                                            @PathVariable("wishlistName") String wishListName) {
        return this.service.getWishlistById(email, wishListName);
    }

    @RequestMapping(path = "/wishlist/{email}/{wishlistName}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void addProdudct(@PathVariable("email") String email,
                                            @PathVariable("wishlistName") String wishListName,
                                            @RequestBody AddProductBoundary productBoundary) {
        this.service.addProduct(email, wishListName, productBoundary);
    }

    @RequestMapping(path = "/wishlist/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WishlistBoundary[] getAll(
            @RequestParam(value = "filterBy", required = false, defaultValue = "none") FilterByEnum filterBy,
            @RequestParam(value = "filterValue", required = false, defaultValue = "") String filterValue,
            @RequestParam(value = "sortBy", required = false, defaultValue = "name") SortByEnum sortBy,
            @RequestParam(value = "sortOrder", required = false, defaultValue = "ASC") SortOrderEnum sortOrder,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page) {
        return this.service.getAll(filterBy, filterValue, sortBy, sortOrder, size, page);
    }

    @RequestMapping(path = "/wishlist",
                    method = RequestMethod.DELETE)
    public void deleteAll() {
        this.service.deleteAll();
    }
}
