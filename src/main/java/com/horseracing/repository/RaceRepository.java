package com.horseracing.repository;

import com.horseracing.entity.Race;
import com.horseracing.enums.RaceStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
    @Query("SELECT r FROM Race r WHERE r.deleted = 0 AND r.season.id = :seasonId")
    Page<Race> findBySeasonId(Long seasonId, Pageable pageable);
    
    @Query("SELECT r FROM Race r WHERE r.deleted = 0 AND r.status = :status")
    Page<Race> findByStatus(RaceStatus status, Pageable pageable);
    
    @Query("SELECT r FROM Race r WHERE r.deleted = 0 AND " +
           "LOWER(r.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Race> findByKeyword(String keyword, Pageable pageable);
    
    @Query("SELECT r FROM Race r WHERE r.deleted = 0 AND r.raceDate > CURRENT_TIMESTAMP " +
           "ORDER BY r.raceDate ASC")
    List<Race> findUpcoming(Pageable pageable);
    
    @Query("SELECT r FROM Race r WHERE r.deleted = 0 AND r.status = 'COMPLETED' " +
           "ORDER BY r.raceDate DESC")
    List<Race> findRecent(Pageable pageable);
    
    @Query("SELECT r FROM Race r WHERE r.deleted = 0")
    Page<Race> findAllActive(Pageable pageable);
}
