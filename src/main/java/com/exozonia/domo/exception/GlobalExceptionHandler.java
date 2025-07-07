//package com.exozonia.domo.exeption;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    // Trata IllegalArgumentException (ex: campos inválidos no DTO)
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
//        return buildErrorResponse("Requisição inválida", ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//
//    // Trata erros inesperados, mas ignora erros internos do SpringDoc/Swagger
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Map<String, String>> handleException(Exception ex) {
//        // Evita interferir no Swagger
//        if (ex.getClass().getPackageName().startsWith("org.springdoc")) {
//            throw new RuntimeException(ex); // deixa o Spring lidar
//        }
//
//        return buildErrorResponse("Erro interno do servidor", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    // Método utilitário para montar o JSON de erro
//    private ResponseEntity<Map<String, String>> buildErrorResponse(String erro, String detalhes, HttpStatus status) {
//        Map<String, String> body = new HashMap<>();
//        body.put("erro", erro);
//        body.put("detalhes", detalhes);
//        return ResponseEntity.status(status).body(body);
//    }
//}
