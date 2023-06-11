package Unit;

import org.example.Exceptions.EmptyFileException;
import org.example.Services.IStorageService;
import org.example.Services.StorageService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import static Util.Constants.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StorageServiceTest {

    @Autowired
    private Environment env;
    private IStorageService storageService;

    @Before
    public void setUp() {
        storageService = new StorageService();
        storageService.initializeRoot(TEST_DATA_PATH);
    }

    @Test
    public void save_ok() {
        MultipartFile file = new MockMultipartFile(TEST_SLUG, "some txt".getBytes());

        boolean isSavedImg;
        boolean isSavedMp4;

        try {
            isSavedImg = storageService.save(file, TEST_SLUG, ".jpg");
            isSavedMp4 = storageService.save(file, TEST_SLUG, ".mp4");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertTrue(isSavedImg);
        assertTrue(isSavedMp4);

        File fImg = new File(TEST_DATA_PATH  + TEST_SLUG + ".jpg");
        assertTrue(fImg.exists());

        File fMp4 = new File(TEST_DATA_PATH  + TEST_SLUG + ".mp4");
        assertTrue(fMp4.exists());
    }

    @Test(expected = EmptyFileException.class)
    public void save_emptyFile() {
        MultipartFile file = new MockMultipartFile(TEST_SLUG, "".getBytes());
        try {
            storageService.save(file, TEST_SLUG, ".jpg");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void delete_ok() {
        // assuming that save_ok() passes
        MultipartFile file = new MockMultipartFile(TEST_SLUG, "some txt".getBytes());
        try {
            storageService.save(file, TEST_SLUG, ".jpg");
            storageService.save(file, TEST_SLUG, ".mp4");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        boolean isDeleted;
        try {
            isDeleted = storageService.delete(TEST_SLUG);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertTrue(isDeleted);

        File fImg = new File(TEST_DATA_PATH  + TEST_SLUG + ".jpg");
        assertFalse(fImg.exists());

        File fMp4 = new File(TEST_DATA_PATH  + TEST_SLUG + ".mp4");
        assertFalse(fMp4.exists());
    }

    @Test(expected = NoSuchFileException.class)
    public void delete_notFound() throws IOException {
        storageService.delete("wrong_slug");
    }
}