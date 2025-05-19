package com.alibou.ecommerce.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
//@Slf4j
public class NotificationProducer {

  private final KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplate;


  private static final Logger logger = LoggerFactory.getLogger(NotificationProducer.class);



  public void sendNotification(PaymentNotificationRequest request) {
    logger.info("Sending notification with body = < {} >", request);
    Message<PaymentNotificationRequest> message = MessageBuilder

            //Payload: l’oggetto request
            .withPayload(request)
            .setHeader(TOPIC, "payment-topic")
            .build();

    kafkaTemplate.send(message);

    /*
    * [Spring Boot App] ---> (KafkaTemplate) ---> [Kafka Broker]
                                       ↑
                      PaymentNotificationRequest serializzato

    *
    * */
  }
}
