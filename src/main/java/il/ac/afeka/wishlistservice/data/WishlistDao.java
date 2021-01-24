package il.ac.afeka.wishlistservice.data;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface WishlistDao extends PagingAndSortingRepository<WishlistEntity, String> {
}
