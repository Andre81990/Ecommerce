package com.alibou.ecommerce.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;



/*
* quando l'app si avvia, Spring legge questa
*  classe per creare e configurare oggetti.
*
* */
@Configuration
public class KafkaOrderTopicConfig {

    @Bean
    public NewTopic orderTopic() {
        return TopicBuilder
                .name("order-topic")
                /*
                *  Completa la costruzione dell'oggetto NewTopic con le configurazioni specificate
                *  (in questo caso, solo il nome) e lo restituisce come bean Spring.
                * */
                .build();
    }
}
