package th.mfu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.Domain.Seller;

public interface SellerRepository extends CrudRepository<Seller, Long>{
    
}