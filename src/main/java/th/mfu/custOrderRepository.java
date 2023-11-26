package th.mfu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.Domain.CustOrder;

public interface CustOrderRepository extends CrudRepository<CustOrder, Long>{

    Object findAllById(Long id);
    
}