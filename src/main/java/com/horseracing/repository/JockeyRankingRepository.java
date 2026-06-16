package com.horseracing.repository;

import com.horseracing.entity.JockeyRanking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JockeyRankingRepository extends JpaRepository<JockeyRanking, Long> {
    Optional<JockeyRanking> findBySeasonIdAndJockeyId(Long seasonId, Long jockeyId);
    
    @Query("SELECT r FROM JockeyRanking r WHERE r.season.id = :seasonId " +
           "ORDER BY r.totalPoints DESC, r.totalWins DESC")
    Page<JockeyRanking> findBySeasonId(Long seasonId, Pageable pageable);
}
