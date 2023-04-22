package org.example.Repositories;

import org.example.Entities.ApplicationUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IUserRepository extends CrudRepository<ApplicationUser, Long> {

    ApplicationUser findByEmail(String email);

    ApplicationUser findByUserName(String userName);

    boolean existsByEmail(String email);

    boolean existsByUserName(String userName);

    @Query(nativeQuery = true, value="SELECT user_name FROM user")
    List<String> getAllEntries();

}
