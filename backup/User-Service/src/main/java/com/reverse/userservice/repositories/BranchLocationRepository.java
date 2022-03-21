package com.reverse.userservice.repositories;

import com.reverse.userservice.models.BranchLocation;
import lombok.Generated;
import org.springframework.data.jpa.repository.JpaRepository;


@Generated
/**
 * BranchLocationRepository extends JpaRepository, which means it
 * comes with many built-in methods, such as findAll(), save(),
 * saveAll(), ectMore specific look-up methods can be generated in
 * Intellij by right-clicking, navigating to 'Generate...' and
 * selecting 'repository methods'
 *
 * Happy coding!
 * - Asher Rosenbaum
 */
public interface BranchLocationRepository extends JpaRepository<BranchLocation, Long> {
}
