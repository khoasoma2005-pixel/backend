package com.horseracing.repository;

import com.horseracing.entity.RequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestHistoryRepository extends JpaRepository<RequestHistory, Long> {
    @Query("SELECT r FROM RequestHistory r WHERE r.targetType = :targetType AND r.targetId = :targetId " +
           "ORDER BY r.createdAt DESC")
    List<RequestHistory> findByTargetTypeAndTargetId(String targetType, Long targetId);
}
