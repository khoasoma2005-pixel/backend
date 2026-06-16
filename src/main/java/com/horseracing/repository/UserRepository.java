package com.horseracing.repository;

import com.horseracing.entity.User;
import com.horseracing.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    
    @Query("SELECT u FROM User u WHERE u.deleted = 0 AND " +
           "(LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(u.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<User> findByKeyword(String keyword, Pageable pageable);
    
    @Query("SELECT u FROM User u WHERE u.deleted = 0 AND u.role = :role")
    Page<User> findByRole(Role role, Pageable pageable);
    
    @Query("SELECT u FROM User u WHERE u.deleted = 0")
    Page<User> findAllActive(Pageable pageable);
}
