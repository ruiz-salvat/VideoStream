package org.example.Services;

import org.example.Exceptions.EmptyFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public boolean save(MultipartFile file, String slug, String fileExtension) throws IOException {
        if (file.getSize() < 1)
            throw new EmptyFileException();

        Files.write(root.resolve(slug + fileExtension), file.getBytes());
        return true;
    }

    @Override
    public boolean delete(String slug) throws IOException {
        Files.delete(root.resolve(slug + ".mp4"));
        return true;
    }
}
