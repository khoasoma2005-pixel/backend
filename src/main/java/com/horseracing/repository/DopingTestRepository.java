package com.horseracing.repository;

import com.horseracing.entity.DopingTest;
import com.horseracing.enums.DopingResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DopingTestRepository extends JpaRepository<DopingTest, Long> {
    List<DopingTest> findByHorseId(Long horseId);
    
    List<DopingTest> findByRaceId(Long raceId);
    
    @Query("SELECT d FROM DopingTest d WHERE d.horse.id = :horseId AND d.result = :result")
    List<DopingTest> findByHorseIdAndResult(Long horseId, DopingResult result);
}
