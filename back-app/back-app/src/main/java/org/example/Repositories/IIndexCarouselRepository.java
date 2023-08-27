package org.example.Repositories;

import org.example.Entities.IndexCarousel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IIndexCarouselRepository extends JpaRepository<IndexCarousel, Long> {

    boolean existsByImageFilePath(String imageFilePath);

    IndexCarousel findByImageFilePath(String imageFilePath);

    List<IndexCarousel> findByIndexLayout_Id(Long id);

}