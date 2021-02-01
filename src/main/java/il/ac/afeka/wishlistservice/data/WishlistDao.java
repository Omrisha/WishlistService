package il.ac.afeka.wishlistservice.data;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WishlistDao extends PagingAndSortingRepository<WishlistEntity, String> {
    List<WishlistEntity> findAllByUser(@Param("user") UserEntity user, Pageable request);
}
