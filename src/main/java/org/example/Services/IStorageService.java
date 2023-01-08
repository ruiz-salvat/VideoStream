package org.example.Services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface IStorageService {

    void init();

    boolean save(MultipartFile file, String slug) throws IOException;

    boolean delete(String slug) throws IOException;

    Resource load(String filename);

    void deleteAll();

    Stream<Path> loadAll();

}
