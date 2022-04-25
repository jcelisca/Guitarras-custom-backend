package co.com.sofka.guitarrasBack.infraestructure.rest.spring.controller;

import co.com.sofka.guitarrasBack.application.service.GuitarraService;
import co.com.sofka.guitarrasBack.application.service.OrdenService;
import co.com.sofka.guitarrasBack.domain.entity.Guitarra;
import co.com.sofka.guitarrasBack.domain.entity.Orden;
import co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto.CarritoDTO;
import co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto.GuitarraDTO;
import co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto.LuthierDTO;
import co.com.sofka.guitarrasBack.infraestructure.db.springdata.dto.OrdenDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrdenController {

    private final OrdenService ordenService;
    private final GuitarraService guitarraService;

    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @PostMapping("/orden/crear/{luthier}/{UID}/{comprobante}")
    public Mono<OrdenDTO> save(
            @RequestBody GuitarraDTO guitarraDTO,
            @PathVariable("luthier") Boolean luthier,
            @PathVariable("UID") String UID,
            @PathVariable("comprobante") String comprobante
    ) {

        OrdenDTO ordenDTO = new OrdenDTO();
        ordenDTO.setFecha(LocalDate.now());

        LuthierDTO luthierDTO = new LuthierDTO();
        luthierDTO.calcularPrecio(luthier);

        List<CarritoDTO> lista = new ArrayList<>();
        CarritoDTO carrito = new CarritoDTO();

        carrito.setGuitarra(modelMapper().map(guitarraDTO, GuitarraDTO.class));
        carrito.setLuthier(luthierDTO);
        carrito.setCantidad(1);
        carrito.setTotal(luthierDTO.getPrecio());
        lista.add(carrito);

        ordenDTO.setCarrito(lista);

        ordenDTO.setTotal(ordenService.sumaDePrecios(lista) + guitarraDTO.getPrecio());

        ordenDTO.setComprobante(comprobante);

        ordenDTO.setUID(UID);

        return ordenService.save(modelMapper().map(ordenDTO, Orden.class))
                .map(orden1 -> modelMapper().map(orden1, OrdenDTO.class));
    }

    @GetMapping("/orden")
    public Flux<OrdenDTO> findAll() {
        return ordenService.findAll()
                .map(orden -> modelMapper().map(orden, OrdenDTO.class));
    }
}
