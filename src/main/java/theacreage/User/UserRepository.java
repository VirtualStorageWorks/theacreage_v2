package theacreage.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :username OR u.username = :username")
    User findByEmailOrUsername(@Param("username") String username);

}