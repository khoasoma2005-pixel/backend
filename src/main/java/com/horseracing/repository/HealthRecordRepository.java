package com.horseracing.repository;

import com.horseracing.entity.HealthRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthRecordRepository extends JpaRepository<HealthRecord, Long> {
    List<HealthRecord> findByHorseId(Long horseId);
    
    List<HealthRecord> findByRaceId(Long raceId);
    
    @Query("SELECT h FROM HealthRecord h WHERE h.horse.id = :horseId AND h.race.id = :raceId")
    List<HealthRecord> findByHorseIdAndRaceId(Long horseId, Long raceId);
}
