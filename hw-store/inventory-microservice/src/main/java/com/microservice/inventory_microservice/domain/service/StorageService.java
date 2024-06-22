package com.microservice.inventory_microservice.domain.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    String store(MultipartFile file, String folderPath);
    Stream<Path> loadAll(String folderPath);
    Path load(String filename, String folderPath);
    Resource loadAsResource(String filename, String folderPath);
    void deleteAll(String folderPath);
}
