package org.example.Services;

import org.example.Exceptions.EmptyFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class StorageService implements IStorageService {

    @Autowired
    private Environment env;

    private final Path root;

    public StorageService(Environment env) {
        String dataPath = env.getProperty("dataPath");
        if (dataPath == null || dataPath.isEmpty())
            throw new RuntimeException("empty property: dataPath");
        root = Paths.get(dataPath);
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload");
        }
    }

    @Override
    public boolean save(MultipartFile file, String slug) throws IOException {
        if (file.getSize() < 1)
            throw new EmptyFileException();

        Files.write(root.resolve(slug + ".mp4"), file.getBytes());
        return true;
    }

    @Override
    public boolean delete(String slug) throws IOException {
        Files.delete(root.resolve(slug + ".mp4"));
        return true;
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(root, 1).filter(path -> !path.equals(root)).map(root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files");
        }
    }
}
