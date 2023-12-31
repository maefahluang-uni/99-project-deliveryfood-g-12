package th.mfu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.Domain.Buyer;

public interface BuyerRepository extends CrudRepository<Buyer, Long>{

    boolean existsByEmail(String email);

    Buyer findByEmail(String email);

    Buyer findByPassword(String password);
    
}