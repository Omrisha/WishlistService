package il.ac.afeka.wishlistservice.logic;

import il.ac.afeka.wishlistservice.boundries.WishlistBoundary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishlistController {
    private WishlistService service;

    @Autowired
    public WishlistController(WishlistService service) {
        this.service = service;
    }

    @RequestMapping(path = "/wishlist",
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public WishlistBoundary create(@RequestBody WishlistBoundary wishlist) {
        return this.service.create(wishlist);
    }
}
