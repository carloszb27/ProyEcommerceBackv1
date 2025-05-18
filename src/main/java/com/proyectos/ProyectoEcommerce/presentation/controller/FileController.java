package com.proyectos.ProyectoEcommerce.presentation.controller;

import com.proyectos.ProyectoEcommerce.persistence.entity.FileEntity;
import com.proyectos.ProyectoEcommerce.persistence.entity.ResponseFile;
import com.proyectos.ProyectoEcommerce.presentation.Response.CustomResponseBuilder;
import com.proyectos.ProyectoEcommerce.service.interfaces.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/fileManager")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        FileEntity fileEntity = fileService.store(file);
        //return ResponseEntity.ok(Map.of("message", "Archivo subido exitosamente"));
        return CustomResponseBuilder.getInstance().crearResponse(fileEntity, true, fileEntity.getId());
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<?> getFile(@PathVariable UUID id) throws FileNotFoundException {

        FileEntity fileEntity = fileService.getFile(id);

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, fileEntity.getType())
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+ fileEntity.getName()+"\"")
                .body(fileEntity.getData());
    }

    @GetMapping("/files")
    public ResponseEntity<?> getListFiles(){

        List<ResponseFile> lista = fileService.getAllFiles();

        return CustomResponseBuilder.getInstance().crearResponse(lista);
        //return ResponseEntity.ok(lista);
    }

}
