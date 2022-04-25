package co.com.sofka.guitarrasBack.infraestructure.rest.spring.controller;

import co.com.sofka.guitarrasBack.application.service.OrdenService;
import co.com.sofka.guitarrasBack.domain.entity.Guitarra;
import co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto.GuitarraDTO;
import co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto.OrdenDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrdenController {

    private final OrdenService ordenService;

    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @PostMapping("/orden/crear/{luthier}/{UID}")
    public Mono<OrdenDTO> save(
            @RequestBody GuitarraDTO guitarraDTO,
            @PathVariable("luthier") Boolean luthier,
            @PathVariable("UID") String UID
    ) {
        return ordenService.guardarOrden(modelMapper().map(guitarraDTO, Guitarra.class),
                        luthier,UID)
                .map(orden1 -> modelMapper().map(orden1, OrdenDTO.class));
    }

    @GetMapping("/orden/actualizar/{id}/{UID}/{comprobante}")
    public Mono<OrdenDTO> update(
            @PathVariable("id") String id,
            @PathVariable("UID") String UID,
            @PathVariable("comprobante") String comprobante
    ){
        return ordenService.update(id,UID,comprobante)
                .map(orden -> modelMapper().map(orden, OrdenDTO.class));
    }

    @GetMapping("/orden")
    public Flux<OrdenDTO> findAll() {
        return ordenService.findAll()
                .map(orden -> modelMapper().map(orden, OrdenDTO.class));
    }

    @GetMapping("/orden/id/{id}")
    public Mono<OrdenDTO> findById(@PathVariable("id") String id){
        return ordenService.findById(id)
                .map(orden -> modelMapper().map(orden, OrdenDTO.class));
    }
}
