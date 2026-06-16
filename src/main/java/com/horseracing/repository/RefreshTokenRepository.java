package com.horseracing.repository;

import com.horseracing.entity.RefreshToken;
import com.horseracing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    
    void deleteByUserAndIsRevokedGreaterThan(User user, Integer revoked);
    
    void deleteByExpiryDateBefore(LocalDateTime date);
}
