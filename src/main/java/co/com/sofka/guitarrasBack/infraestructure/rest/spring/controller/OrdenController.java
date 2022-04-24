package co.com.sofka.guitarrasBack.infraestructure.rest.spring.controller;

import co.com.sofka.guitarrasBack.application.service.OrdenService;
import co.com.sofka.guitarrasBack.domain.Orden;
import co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto.OrdenDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class OrdenController {

    private final OrdenService service;
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @PostMapping("/orden/crear")
    public Mono<OrdenDTO> save(@RequestBody OrdenDTO orden) {
        return service.save(modelMapper().map(orden, Orden.class))
                .map(orden1 -> modelMapper().map(orden1, OrdenDTO.class));
    }
}
