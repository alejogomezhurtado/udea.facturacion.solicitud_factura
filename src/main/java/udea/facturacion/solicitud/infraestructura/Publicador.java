package udea.facturacion.solicitud.infraestructura;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class Publicador {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RabbitConf.class);
    RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);

    public void publicarMensaje(String exchange, String routingKey, String message){
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
    public void publicarMensajeAsync(String exchange, String routingKey, String message){
        CompletableFuture.runAsync(() -> rabbitTemplate.convertAndSend(exchange, routingKey, message));
    }

}
