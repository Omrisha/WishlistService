package il.ac.afeka.wishlistservice.logic;

import il.ac.afeka.wishlistservice.boundries.WishlistBoundary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class WishlistKafkaConsumer {
    private WishlistService wishlistService;

    @Autowired
    public WishlistKafkaConsumer(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    public Consumer<WishlistBoundary> receiveAndHandleRemoteMessage() {
        return r -> this.wishlistService.create(r);
    }
}
