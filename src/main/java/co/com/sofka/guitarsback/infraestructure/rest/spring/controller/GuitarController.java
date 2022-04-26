package co.com.sofka.guitarsback.infraestructure.rest.spring.controller;

import co.com.sofka.guitarsback.application.service.GuitarService;
import co.com.sofka.guitarsback.domain.entity.Guitar;
import co.com.sofka.guitarsback.infraestructure.db.springdata.dto.GuitarDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "https://guitarras-custom-first.web.app/login")
public class GuitarController {

    private final GuitarService service;

    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @PostMapping("/guitarra/crear")
    public Mono<GuitarDTO> save(@RequestBody Guitar guitar) {
        return service.save(guitar)
                .map(guitarra -> modelMapper().map(guitarra, GuitarDTO.class));
    }


    @GetMapping("/guitarra")
    public Flux<GuitarDTO> findAll() {
        return service.findAll()
                .map(guitarra -> modelMapper().map(guitarra, GuitarDTO.class));
    }

    @GetMapping("/guitarra/{tipo}/{modelo}/{marca}")
    public Flux<GuitarDTO> filtrarGuitarra(
            @PathVariable("tipo") String tipo,
            @PathVariable("modelo") String modelo,
            @PathVariable("marca") String marca
    ) {
        return service.findByTipo(tipo)
                .filter(guitarra -> guitarra.getModelo().equals(modelo) && guitarra.getMarca().equals(marca))
                .map(g -> modelMapper().map(g, GuitarDTO.class));
    }

    @GetMapping("/guitarra/{tipo}/{modelo}/{marca}/{numCuerdas}/{tipoCuerdas}/{afinacion}")
    public Flux<GuitarDTO> filtrarGuitarraCompleto(
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
                .map(g -> modelMapper().map(g, GuitarDTO.class));
    }

    @GetMapping("/guitarra/marca/{marca}")
    public Flux<GuitarDTO> findByMarca(
            @PathVariable("marca") String marca
    ) {
        return service.findByMarca(marca)
                .map(g -> modelMapper().map(g, GuitarDTO.class));
    }

    @GetMapping("/guitarra/modelo/{modelo}")
    public Flux<GuitarDTO> findByModelo(
            @PathVariable("modelo") String modelo
    ) {
        return service.findByModelo(modelo)
                .map(g -> modelMapper().map(g, GuitarDTO.class));
    }

    @GetMapping("/guitarra/id/{id}")
    public Mono<GuitarDTO> findById(
            @PathVariable("id") String id
    ) {
        return service.findById(id)
                .map(g -> modelMapper().map(g, GuitarDTO.class));
    }
}
