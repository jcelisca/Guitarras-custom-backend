package co.com.sofka.guitarrasBack.infraestructure.rest.spring.controller;

import co.com.sofka.guitarrasBack.application.service.GuitarraService;
import co.com.sofka.guitarrasBack.domain.Guitarra;
import co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto.GuitarraDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class GuitarraController {

    private final GuitarraService service;

    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @PostMapping("/guitarra/crear")
    public Mono<GuitarraDTO> save(@RequestBody GuitarraDTO guitarra){
        return service.save(modelMapper().map(guitarra, Guitarra.class))
                .map(guitarra1->modelMapper().map(guitarra1, GuitarraDTO.class));
    }

    //@GetMapping("/guitarra/{tipo}/{modelo}/")
}
