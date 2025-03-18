package com.academico.models.service.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;

public class MensagemSistema {


    public static <T> ResponseEntity<?> showMensagem(String mensagem, HttpStatus status, HttpServletRequest request) {

        return mensagem(new Mensagem.Builder()
                .status(status.value()) 
                .mensagem(mensagem)    
                .path(request.getRequestURI()) 
                .metodo(request.getMethod())); 
    }

    private static <T> ResponseEntity<?> mensagem(Mensagem.Builder builder) {

        Mensagem mensagem = builder.build();
        return ResponseEntity.status(mensagem.getStatus())
                .body(mensagem); 
    }
}
