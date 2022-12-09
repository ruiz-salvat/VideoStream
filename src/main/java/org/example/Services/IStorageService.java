package org.example.Services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface IStorageService {

    public void init();

    public boolean save(MultipartFile file, String title) throws IOException;

    public Resource load(String filename);

    public void deleteAll();

    public Stream<Path> loadAll();

}
