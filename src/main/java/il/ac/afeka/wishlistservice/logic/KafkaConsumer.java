package il.ac.afeka.wishlistservice.logic;

import il.ac.afeka.wishlistservice.boundries.WishlistBoundary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Configuration
public class KafkaConsumer {
    private WishlistService wishlistService;

    @Autowired
    public KafkaConsumer(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @Bean
    public Consumer<WishlistBoundary> receiveAndHandleRemoteMessage(){
        return r->{
            System.err.println(r);
            WishlistBoundary rv = this.wishlistService.create(r);
            System.err.println("After creating: " + rv);
        };
    }
}
