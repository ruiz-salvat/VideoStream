package org.example.Services;

import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface IStorageService {

    void initializeRoot(String dataPath);

    void init();

    boolean save(MultipartFile file, String slug, String fileExtension) throws IOException;

    boolean delete(String slug) throws IOException;

}
