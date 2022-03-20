package com.reverse.userservice.repositories;

import com.reverse.userservice.models.User;
import lombok.Generated;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Generated
/**
 * UserRepository extends JpaRepository, which means it comes with
 * many built-in methods, such as findAll(), save(), saveAll(), etc.
 * More specific look-up methods can be generated in Intellij by
 * right-clicking, navigating to 'Generate...' and selecting
 * 'repository methods'
 *
 * Happy coding!
 * - Asher Rosenbaum
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     *
     * @return complementary method to findById
     * all other methods employed by the UserServiceImpl are from the JpaRepository or its parent classes/associated interfaces
     */
    User findByUsername(String username);
    
}
