package th.mfu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import th.mfu.Domain.Rider;

public interface RiderRepository extends CrudRepository<Rider, Long>{
    
}