package com.microservice.inventory_microservice.domain.serviceimpl;

import com.microservice.inventory_microservice.domain.service.StorageService;
import com.microservice.inventory_microservice.source.exception.StorageException;
import com.microservice.inventory_microservice.source.exception.StorageFileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

    @Override
    public String store(MultipartFile file, String folderPath) {
        String imgPath = null;

        // Generar un UUID
        String uuid = UUID.randomUUID().toString();
        // Obtener la extensión del archivo original
        String originalFilename = file.getOriginalFilename();
        String extension = "";

        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        // Crear el nuevo nombre de archivo usando el UUID
        String newFilename = uuid + extension;
        imgPath = newFilename;

        Path rootLocation = Paths.get(folderPath);
        Path destinationFile = rootLocation.resolve(Paths.get(newFilename))
                .normalize().toAbsolutePath();
        if (!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) {
            // This is a security check
            throw new StorageException(
                    "Cannot store file outside current directory.");
        }

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, destinationFile,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
        return imgPath;
    }

    @Override
    public Stream<Path> loadAll(String folderPath) {
        try {
            Path rootLocation = Paths.get(folderPath);
            return Files.walk(rootLocation, 1)
                    .filter(path -> !path.equals(rootLocation))
                    .map(rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }
    }

    @Override
    public Path load(String filename, String folderPath) {
        Path rootLocation = Paths.get(folderPath);
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename, String folderPath) {
        try {
            Path file = load(filename, folderPath);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll(String folderPath) {
        Path rootLocation = Paths.get(folderPath);
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}
