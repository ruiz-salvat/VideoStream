package org.example.Repositories;

import org.example.Entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVideoRepository extends JpaRepository<Video, Long> {

    Video findBySlug(String title);

    boolean existsBySlug(String title);

}