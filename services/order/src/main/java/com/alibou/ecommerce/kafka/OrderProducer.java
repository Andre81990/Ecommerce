package com.alibou.ecommerce.kafka;

import com.sun.tools.javac.Main;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
public class OrderProducer {


    private static final Logger logger = Logger.getLogger(OrderProducer.class.getName());



/**
Dichiara un oggetto kafkaTemplate di tipo KafkaTemplate<String, OrderConfirmation>.
Specifica che tipo di chiave e valore Kafka userà nei messaggi:
Chiave = String
Valore (cioè il payload) = OrderConfirmation
**/
    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;


    public void sendOrderConfirmation(OrderConfirmation orderConfirmation) {
        logger.info("Sending order confirmation");
        Message<OrderConfirmation> message = MessageBuilder
                /*Payload = i dati veri e propri che vuoi trasmettere.
                  In questo caso, una conferma d'ordine (OrderConfirmation).
                 */
                .withPayload(orderConfirmation)
                .setHeader(TOPIC, "order-topic")
                .build();

        kafkaTemplate.send(message);
    }
}
