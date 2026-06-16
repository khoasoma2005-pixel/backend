package com.horseracing.repository;

import com.horseracing.entity.AuditLog;
import com.horseracing.enums.AuditAction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    @Query("SELECT a FROM AuditLog a WHERE a.user.id = :userId " +
           "ORDER BY a.createdAt DESC")
    Page<AuditLog> findByUserId(Long userId, Pageable pageable);
    
    @Query("SELECT a FROM AuditLog a WHERE a.action = :action " +
           "ORDER BY a.createdAt DESC")
    Page<AuditLog> findByAction(AuditAction action, Pageable pageable);
    
    @Query("SELECT a FROM AuditLog a WHERE a.createdAt BETWEEN :fromDate AND :toDate " +
           "ORDER BY a.createdAt DESC")
    Page<AuditLog> findByDateRange(LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable);
    
    @Query("SELECT a FROM AuditLog a WHERE a.user.id = :userId AND a.action = :action " +
           "ORDER BY a.createdAt DESC")
    Page<AuditLog> findByUserIdAndAction(Long userId, AuditAction action, Pageable pageable);
}
