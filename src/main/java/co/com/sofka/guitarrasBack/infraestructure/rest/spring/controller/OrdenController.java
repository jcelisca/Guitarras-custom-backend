package co.com.sofka.guitarrasBack.infraestructure.rest.spring.controller;

import co.com.sofka.guitarrasBack.application.service.OrdenService;
import co.com.sofka.guitarrasBack.domain.Orden;
import co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto.OrdenDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
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

    @GetMapping("/orden")
    public Flux<OrdenDTO> findAll() {
        return service.findAll()
                .map(orden -> modelMapper().map(orden, OrdenDTO.class));
    }
}
