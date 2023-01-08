package org.example.Repositories;

import org.example.Entities.Video;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVideoRepository extends CrudRepository<Video, Long> {

    Video findBySlug(String slug);

    boolean existsBySlug(String slug);

    void deleteBySlug(String slug);

}