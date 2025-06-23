package me.danhthanhf.distant_class.repository;

import me.danhthanhf.distant_class.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsById(Long id);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
