package org.example.Repositories;

import org.example.Entities.IndexCarousel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IIndexCarouselRepository extends JpaRepository<IndexCarousel, Long> {

}