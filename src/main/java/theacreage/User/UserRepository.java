package theacreage.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.email=?1 and u.password=?2")
    User login(String email, String password);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    User findByEmailAndPassword(String email, String password);

    User findUserByEmail(String username);

}