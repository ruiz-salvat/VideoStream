package org.example.Repositories;

import org.example.Entities.Video;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVideoRepository extends CrudRepository<Video, Long> {

    Video findBySlug(String slug);

    List<Video> findByIsInfoVideoIsFalse();

    boolean existsBySlug(String slug);

    void deleteBySlug(String slug);

}