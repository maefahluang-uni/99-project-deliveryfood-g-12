package th.mfu.Domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.Domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{
    
}
