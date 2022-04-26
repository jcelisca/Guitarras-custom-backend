package co.com.sofka.guitarsback.infraestructure.db.springdata.repository;

import co.com.sofka.guitarsback.application.repository.OrderRepository;
import co.com.sofka.guitarsback.domain.entity.Order;
import co.com.sofka.guitarsback.infraestructure.db.springdata.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class OrderDTOService implements OrderRepository {

    private final OrderDataRepository repository;

    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public Mono<Order> save(Order order) {
        return this.repository.save(
                modelMapper().map(order, OrderDTO.class)
        ).map(ordenDTO -> modelMapper().map(ordenDTO, Order.class));
    }

    @Override
    public Flux<Order> findAll() {
        return repository.findAll()
                .map(ordenDTO -> modelMapper().map(ordenDTO, Order.class));
    }

    @Override
    public Mono<Order> findById(String id) {
        return repository.findById(id)
                .map(ordenDTO -> modelMapper().map(ordenDTO, Order.class));
    }

}
