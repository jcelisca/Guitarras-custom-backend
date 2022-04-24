package co.com.sofka.guitarrasBack.infraestructure.rest.spring.controller;

import co.com.sofka.guitarrasBack.application.service.GuitarraService;
import co.com.sofka.guitarrasBack.domain.Guitarra;
import co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto.GuitarraDTO;
import co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto.GuitarraResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

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

    @GetMapping("/guitarra/{tipo}/{modelo}/{marca}")
    public Flux<GuitarraDTO> filtrarGuitarra(
            @PathVariable("tipo") String tipo,
            @PathVariable("modelo") String modelo,
            @PathVariable("marca") String marca
    ) {
        return service.findByTipo(tipo)
                .filter(guitarra -> guitarra.getModelo().equals(modelo) && guitarra.getMarca().equals(marca))
                .map(g -> modelMapper().map(g, GuitarraDTO.class));
    }

    @GetMapping("/guitarra/{tipo}/{modelo}/{marca}/{numCuerdas}/{tipoCuerdas}/{afinacion}")
    public Flux<GuitarraDTO> filtrarGuitarraCompleto(
            @PathVariable("tipo") String tipo,
            @PathVariable("modelo") String modelo,
            @PathVariable("marca") String marca,
            @PathVariable("numCuerdas") Integer numCuerdas,
            @PathVariable("tipoCuerdas") String tipoCuerdas,
            @PathVariable("afinacion") String afinacion

    ) {
        return service.findByTipo(tipo)
                .filter(guitarra ->
                        guitarra.getModelo().equals(modelo) &&
                                guitarra.getMarca().equals(marca) &&
                                guitarra.getNumCuerdas().equals(numCuerdas) &&
                                guitarra.getTipoCuerda().equals(tipoCuerdas) &&
                                guitarra.getAfinacion().equals(afinacion)
                )
                .map(g -> modelMapper().map(g, GuitarraDTO.class));
    }

    @GetMapping("/guitarra/marca/{marca}")
    public Flux<GuitarraDTO> findByMarca(
            @PathVariable("marca") String marca
    ) {
        return service.findByMarca(marca)
                .map(g -> modelMapper().map(g, GuitarraDTO.class));
    }

    @GetMapping("/guitarra/modelo/{modelo}")
    public Flux<GuitarraDTO> findByModelo(
            @PathVariable("modelo") String modelo
    ) {
        return service.findByModelo(modelo)
                .map(g -> modelMapper().map(g, GuitarraDTO.class));
    }

    @GetMapping("/guitarra/id/{id}")
    public Mono<GuitarraDTO> findById(
            @PathVariable("id") String id
    ) {
        return service.findById(id)
                .map(g -> modelMapper().map(g, GuitarraDTO.class));
    }
}
