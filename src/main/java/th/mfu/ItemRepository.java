package th.mfu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.Domain.Item;

public interface ItemRepository extends CrudRepository<Item, Long>{
    
}