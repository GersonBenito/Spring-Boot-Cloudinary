package com.cloudinary.rest.controllers;

import com.cloudinary.rest.controllers.DTO.FileDTO;
import com.cloudinary.rest.services.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/file")
public class CloudinaryController {

    @Autowired
    CloudinaryService cloudinaryService;

    @PostMapping
    public ResponseEntity<?> upload(@RequestParam MultipartFile file){

        Map<String, Object> response = new HashMap<>();

        try{
            Map<Object, Object> result = cloudinaryService.upload(file);

            response.put("message", "Archivo guardado");
            response.put("Data", result);

            return ResponseEntity.ok(response);

        }catch (IOException e){
            response.put("message", "Error al guardar");
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Map<String, Object> response = new HashMap<>();
        try{
            Map<Object, Object> result = cloudinaryService.delete(id);
            response.put("message", "Archivo eliminado");
            response.put("Data", result);

            return ResponseEntity.ok(response);

        }catch (IOException e){
            response.put("message", "Error al eliminar archivo");
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
