package co.com.sofka.guitarrasBack.infraestructure.rest.spring.controller;

import co.com.sofka.guitarrasBack.application.service.GuitarraService;
import co.com.sofka.guitarrasBack.domain.Guitarra;
import co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto.GuitarraDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class GuitarraController {

    private final GuitarraService service;

    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @PostMapping("/guitarra/crear")
    public Mono<GuitarraDTO> save(@RequestBody GuitarraDTO guitarra) {
        return service.save(modelMapper().map(guitarra, Guitarra.class))
                .map(guitarra1 -> modelMapper().map(guitarra1, GuitarraDTO.class));
    }

    @GetMapping("/guitarra")
    public Flux<GuitarraDTO> findAll() {
        return service.findAll()
                .map(guitarra -> modelMapper().map(guitarra, GuitarraDTO.class));
    }

    @GetMapping("/guitarra/{tipo}/{modelo}/{marca}/{numCuerdas}/{tipoCuerdas}/{afinacion}")
    public Flux<GuitarraDTO> findEspesificGuitar(
            @PathVariable("tipo") String tipo,
            @PathVariable("modelo") String modelo,
            @PathVariable("marca") String marca,
            @PathVariable("numCuerdas") Integer numCuerdas,
            @PathVariable("tipoCuerdas") String tipoCuerdas,
            @PathVariable("afinacion") String afinacion
    ) {
        return service.findEspesificGuitar(tipo, modelo, marca, numCuerdas, tipoCuerdas, afinacion)
                .map(guitarra -> modelMapper().map(guitarra, GuitarraDTO.class));
    }

    //@GetMapping("/guitarra/{tipo}/{modelo}/")
}
