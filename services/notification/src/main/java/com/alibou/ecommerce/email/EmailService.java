package com.alibou.ecommerce.email;

import com.alibou.ecommerce.kafka.order.Product;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alibou.ecommerce.email.EmailTemplates.ORDER_CONFIRMATION;
import static com.alibou.ecommerce.email.EmailTemplates.PAYMENT_CONFIRMATION;
import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@Slf4j

public class EmailService {


    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);


    /*
    * mailSender: oggetto che invia l’email.

      templateEngine: motore Thymeleaf per compilare il contenuto HTML delle email.
    *
    *
    * 5:13:10
    */
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    public EmailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }


    //Metodo asincrono che invia l’email di conferma pagamento.
    @Async
    public void sendPaymentSuccessEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference
    ) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, UTF_8.name());
        messageHelper.setFrom("contact@aliboucoding.com");

        final String templateName = PAYMENT_CONFIRMATION.getTemplate();

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("amount", amount);
        variables.put("orderReference", orderReference);


        Context context = new Context();
        context.setVariables(variables);
        messageHelper.setSubject(PAYMENT_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);

            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            logger.info(String.format("INFO - Email successfully sent to %s with template %s ", destinationEmail, templateName));
        } catch (MessagingException e) {
            logger.warn("WARNING - Cannot send Email to {} ", destinationEmail);
        }

    }



    /*
    * Riceve dati sull’ordine o pagamento.

      Compila un template HTML con Thymeleaf.

      Invia un’email HTML al cliente.

      Esegue tutto asincronamente per non bloccare il server.
    *
    * */
    //manda email di conferma ordine
    @Async
    public void sendOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal amount,
            String orderReference,
            //Invia l’email con la
            //conferma dell’ordine
            //e dettagli sui prodotti
            //acquistati.
            List<Product> products
    ) throws MessagingException {



        //messaggio vuoto
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, UTF_8.name());
        messageHelper.setFrom("contact@aliboucoding.com");

/*
* Usa un template HTML diverso (per la conferma ordine).



* */
        final String templateName = ORDER_CONFIRMATION.getTemplate();

        //Aggiunge anche la lista dei
        // prodotti da mostrare nel
        // corpo dell’email.

        Map<String, Object> variables = new HashMap<>();
        variables.put("customerName", customerName);
        variables.put("totalAmount", amount);
        variables.put("orderReference", orderReference);
        variables.put("products", products);

        Context context = new Context();
        context.setVariables(variables);
        messageHelper.setSubject(ORDER_CONFIRMATION.getSubject());

        //Compila il template, invia e logga.
        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate, true);

            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            logger.info(String.format("INFO - Email successfully sent to %s with template %s ", destinationEmail, templateName));
            } catch (MessagingException e) {
            logger.warn("WARNING - Cannot send Email to {} ", destinationEmail);
        }

    }
}
