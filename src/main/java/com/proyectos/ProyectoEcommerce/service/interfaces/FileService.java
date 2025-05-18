package com.proyectos.ProyectoEcommerce.service.interfaces;

import com.proyectos.ProyectoEcommerce.persistence.entity.FileEntity;
import com.proyectos.ProyectoEcommerce.persistence.entity.ResponseFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FileService {

    FileEntity store(MultipartFile file) throws IOException;
    FileEntity getFile(UUID id) throws FileNotFoundException;

    List<ResponseFile> getAllFiles();
}
