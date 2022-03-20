package com.reverse.userservice.repositories;

import com.reverse.userservice.models.Gender;
import lombok.Generated;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Generated
/**
 * GenderRepository extends JpaRepository, which means it comes with
 * many built-in methods, such as findAll(), save(), saveAll(), ect.
 * More specific look-up methods can be generated in Intellij by
 * right-clicking, navigating to 'Generate...' and selecting
 * 'repository methods'
 *
 * Happy coding!
 * - Asher Rosenbaum
 */
public interface GenderRepository extends JpaRepository<Gender, Long> {
    @Override
    List<Gender> findAll();
}
