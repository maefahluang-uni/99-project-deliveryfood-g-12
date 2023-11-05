package th.mfu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.Domain.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem,Long>{
 
} 