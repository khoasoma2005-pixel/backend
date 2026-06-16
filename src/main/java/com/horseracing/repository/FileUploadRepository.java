package com.horseracing.repository;

import com.horseracing.entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {
    @Query("SELECT f FROM FileUpload f WHERE f.targetType = :targetType AND f.targetId = :targetId")
    List<FileUpload> findByTargetTypeAndTargetId(String targetType, Long targetId);
    
    List<FileUpload> findByUploadedById(Long userId);
}
