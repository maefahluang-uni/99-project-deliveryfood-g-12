package th.mfu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.Domain.custOrder;

public interface custOrderRepository extends CrudRepository<custOrder, Long>{

    Object findAllById(Long id);
    
}