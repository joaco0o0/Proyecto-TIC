package uy.edu.um.airport.entities.Aerolinea;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(path = "api/v1/aerolineas")
public class AerolineaController {
    private final AerolineaService aerolineaService;

    public AerolineaController(AerolineaService aerolineaService) {
        this.aerolineaService = aerolineaService;
    }
    public List<Aerolinea> getAerolineas(){
        return aerolineaService.getAerolineas();
    }
}
