package org.example.Repositories;

import org.example.Entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IVideoRepository extends JpaRepository<Video, Long> {

    Video findByTitle(String title);

    boolean existsByTitle(String title);

    @Query(nativeQuery = true, value="SELECT title FROM video")
    List<String> getAllEntries();

}