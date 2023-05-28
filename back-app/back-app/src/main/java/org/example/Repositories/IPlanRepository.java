package org.example.Repositories;

import org.example.Entities.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlanRepository extends JpaRepository<Plan, Long> {

}
