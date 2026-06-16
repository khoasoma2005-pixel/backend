package com.horseracing.repository;

import com.horseracing.entity.Lane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LaneRepository extends JpaRepository<Lane, Long> {
    List<Lane> findByRaceId(Long raceId);
    
    Optional<Lane> findByRegistrationId(Long registrationId);
    
    @Query("SELECT l FROM Lane l WHERE l.race.id = :raceId AND l.laneNumber = :laneNumber")
    Optional<Lane> findByRaceIdAndLaneNumber(Long raceId, Integer laneNumber);
    
    @Query("SELECT COALESCE(MAX(l.laneNumber), 0) FROM Lane l WHERE l.race.id = :raceId")
    Integer getMaxLaneNumberForRace(Long raceId);
}
