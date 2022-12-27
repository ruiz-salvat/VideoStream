package org.example.Repositories;

import org.example.Entities.Video;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVideoRepository extends CrudRepository<Video, Long> {

    Video findBySlug(String title);

    boolean existsBySlug(String title);

}