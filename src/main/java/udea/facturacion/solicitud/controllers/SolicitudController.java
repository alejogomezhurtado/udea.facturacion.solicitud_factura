package udea.facturacion.solicitud.controllers;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udea.facturacion.solicitud.infraestructura.Publicador;
import udea.facturacion.solicitud.modelo.DtoSolicitud;

@RestController
public class SolicitudController {

    private Publicador publicador = new Publicador();

    @RequestMapping(method = RequestMethod.POST, value="/solicitud")
    public @ResponseBody ResponseEntity<DtoSolicitud> nuevaSolicitud(@RequestBody DtoSolicitud solicitud){
        publicador.publicarMensaje("facturacion.solicitada", "", new Gson().toJson(solicitud));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}