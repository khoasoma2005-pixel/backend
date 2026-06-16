package com.horseracing.repository;

import com.horseracing.entity.HorseRanking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HorseRankingRepository extends JpaRepository<HorseRanking, Long> {
    Optional<HorseRanking> findBySeasonIdAndHorseId(Long seasonId, Long horseId);
    
    @Query("SELECT r FROM HorseRanking r WHERE r.season.id = :seasonId " +
           "ORDER BY r.totalPoints DESC, r.totalWins DESC")
    Page<HorseRanking> findBySeasonId(Long seasonId, Pageable pageable);
}
