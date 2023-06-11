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

    private Path root;
    private String dataPath;
    private boolean isRootInitialized;

    @Autowired
    public StorageService() {
        initializeRoot("");
        isRootInitialized = false;
    }

    @Override
    public void initializeRoot(String dataPath) {
        if (dataPath == null) {
            if (env != null) {
                this.dataPath = env.getProperty("dataPath");
                if (this.dataPath == null || this.dataPath.isEmpty())
                    throw new RuntimeException("empty property: dataPath");
                else
                    isRootInitialized = true;
            } else {
//                throw new RuntimeException("environment variables not loaded");
                this.dataPath = "";
                isRootInitialized = true;
            }
        } else {
            this.dataPath = dataPath;
        }

        root = Paths.get(this.dataPath);
    }

    @Override
    public void init() { // TODO: remove?
        try {
            Files.createDirectories(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload");
        }
    }

    @Override
    public boolean save(MultipartFile file, String slug, String fileExtension) throws IOException {
        if (!isRootInitialized)
            initializeRoot(null);

        if (file.getSize() < 1)
            throw new EmptyFileException();

        Files.write(root.resolve(slug + fileExtension), file.getBytes());
        return true;
    }

    @Override
    public boolean delete(String slug) throws IOException {
        Files.delete(root.resolve(slug + ".mp4"));
        Files.delete(root.resolve(slug + ".jpg"));
        return true;
    }
}
