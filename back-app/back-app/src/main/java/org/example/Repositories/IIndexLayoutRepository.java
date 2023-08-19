package org.example.Repositories;

import org.example.Entities.IndexLayout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IIndexLayoutRepository extends JpaRepository<IndexLayout, Long> {

}