package co.com.sofka.guitarsback.infraestructure.db.springdata.repository;

import co.com.sofka.guitarsback.infraestructure.db.springdata.dto.OrderDTO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDataRepository extends ReactiveMongoRepository<OrderDTO, String> {
}
