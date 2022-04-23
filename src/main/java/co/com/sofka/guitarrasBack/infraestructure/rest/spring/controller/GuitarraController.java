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

    @GetMapping("/guitarra/search")
    public Flux<GuitarraDTO> filtrarGuitarra(@RequestBody GuitarraResponseDTO guitarraResponse) {
        return service.findByTipo(guitarraResponse.getTipo())
                .filter(guitarra -> guitarra.getModelo().equals(guitarraResponse.getModelo())
                        && guitarra.getMarca().equals(guitarraResponse.getMarca()))
                .map(g -> modelMapper().map(g, GuitarraDTO.class));
    }


    @GetMapping("/guitarra/search/completo")
    public Flux<GuitarraDTO> filtrarGuitarraCompleto(@RequestBody GuitarraResponseDTO guitarraResponse) {
        return service.findByTipo(guitarraResponse.getTipo())
                .filter(guitarra ->
                        guitarra.getModelo().equals(guitarraResponse.getModelo()) &&
                                guitarra.getMarca().equals(guitarraResponse.getMarca()) &&
                                guitarra.getNumCuerdas().equals(guitarraResponse.getNumCuerdas()) &&
                                guitarra.getTipoCuerda().equals(guitarraResponse.getTipoCuerda()) &&
                                guitarra.getAfinacion().equals(guitarraResponse.getAfinacion())
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
