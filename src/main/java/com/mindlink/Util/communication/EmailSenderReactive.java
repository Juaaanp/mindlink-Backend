package com.mindlink.Util.communication;

import org.springframework.web.reactive.function.client.WebClient;

import com.mindlink.Util.AuxiliarClasses.EmailRegisterRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmailSenderReactive {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://function-192-adso.azurewebsites.net/api/httptrigger1")
            .build();

    public void sendEmail(String name, List<String> interests, String email) {
        EmailRegisterRequest body = new EmailRegisterRequest();
        body.setSubject("Welcome to MindLink!");
        body.setTemplateName("mindLinkConfirmationTemplate.html");

        Map<String, Object> data = new HashMap<>();
        data.put("nombre", name);
        data.put("intereses", interests);
        body.setDataTemplate(data);

        body.setTo(email);

        String response = webClient.post()
                .uri("")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("Respuesta: " + response);
    }
}
