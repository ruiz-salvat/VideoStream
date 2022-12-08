package org.example.Services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface IStorageService {

    public void init();

    public boolean save(MultipartFile file, String title);

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();

}
