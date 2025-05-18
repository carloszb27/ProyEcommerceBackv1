package com.proyectos.ProyectoEcommerce.service.implementation;

import com.proyectos.ProyectoEcommerce.persistence.entity.FileEntity;
import com.proyectos.ProyectoEcommerce.persistence.entity.ResponseFile;
import com.proyectos.ProyectoEcommerce.persistence.repository.FileRepository;
import com.proyectos.ProyectoEcommerce.service.interfaces.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    @Override
    public FileEntity store(MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        FileEntity fileEntity = FileEntity.builder()
                .name(fileName)
                .type(file.getContentType())
                .data(file.getBytes())
                .build();

        return fileRepository.save(fileEntity);
    }

    @Override
    public FileEntity getFile(UUID id) throws FileNotFoundException {

        FileEntity file = fileRepository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("No se encontro el archivo"));

        return file;
    }

    @Override
    public List<ResponseFile> getAllFiles() {

        List<ResponseFile> files = fileRepository.findAll().stream()
                .map(file -> {
                    String fileDownloadUri = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/fileManager/files/")
                            .path(file.getId().toString())
                            .toUriString();
                    return ResponseFile.builder()
                            .name(file.getName())
                            .url(fileDownloadUri)
                            .type(file.getType())
                            .size(file.getData().length)
                            .build();
                } ).collect(Collectors.toList());

        return files;
    }
}
