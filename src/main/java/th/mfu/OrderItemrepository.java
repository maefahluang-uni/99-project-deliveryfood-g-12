package th.mfu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.Domain.OrderItem;

public interface OrderItemrepository extends CrudRepository<OrderItem,Long>{
 
} 