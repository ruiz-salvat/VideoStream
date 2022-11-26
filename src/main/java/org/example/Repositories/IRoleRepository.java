package org.example.Repositories;

import org.example.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String roleName);

    boolean existsByRoleName(String roleName);

    @Query(nativeQuery = true, value="SELECT role_name FROM role")
    List<String> getAllEntries();

}
