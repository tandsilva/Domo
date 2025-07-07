//
//package com.exozonia.domo.exception;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//public class CustomExceptionHandler {
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
//        return ResponseEntity.badRequest().body(ex.getMessage());
//    }
//
//    // opcional: handler genérico para qualquer erro não tratado
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleAll(Exception ex) {
//        ex.printStackTrace(); // mostra o erro no console
//        return ResponseEntity.status(500).body("Erro inesperado: " + ex.getMessage());
//    }
//}
