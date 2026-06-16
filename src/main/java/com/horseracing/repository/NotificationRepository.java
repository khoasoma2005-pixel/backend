package com.horseracing.repository;

import com.horseracing.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("SELECT n FROM Notification n WHERE n.user.id = :userId ORDER BY n.createdAt DESC")
    Page<Notification> findByUserId(Long userId, Pageable pageable);
    
    @Query("SELECT n FROM Notification n WHERE n.user.id = :userId AND n.isRead = 0 " +
           "ORDER BY n.createdAt DESC")
    Page<Notification> findUnreadByUserId(Long userId, Pageable pageable);
    
    @Query("SELECT COUNT(n) FROM Notification n WHERE n.user.id = :userId AND n.isRead = 0")
    Integer countUnreadByUserId(Long userId);
}
