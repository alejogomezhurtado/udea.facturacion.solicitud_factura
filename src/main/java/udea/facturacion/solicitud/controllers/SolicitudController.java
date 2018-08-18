package udea.facturacion.solicitud.controllers;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udea.facturacion.solicitud.infraestructura.Publicador;
import udea.facturacion.solicitud.modelo.DtoSolicitud;

@RestController
public class SolicitudController {
    private static final Logger logger = LogManager.getLogger(SolicitudController.class);
    private Publicador publicador = new Publicador();

    @RequestMapping(method = RequestMethod.POST, value="/solicitud")
    public @ResponseBody ResponseEntity<DtoSolicitud> nuevaSolicitud(@RequestBody DtoSolicitud solicitud){
        logger.info("Ingresa Solicitud \n{"+solicitud.toString()+"}");
        publicador.publicarMensajeAsync("facturacion.solicitada", "", new Gson().toJson(solicitud));
        logger.info("Solicitud Enviando a Facturacion");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}