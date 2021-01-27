package il.ac.afeka.wishlistservice.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import il.ac.afeka.wishlistservice.boundries.WishlistBoundary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

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
}
