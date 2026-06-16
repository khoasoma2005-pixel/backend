package com.horseracing.repository;

import com.horseracing.entity.RaceRegistration;
import com.horseracing.enums.RegistrationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RaceRegistrationRepository extends JpaRepository<RaceRegistration, Long> {
    @Query("SELECT r FROM RaceRegistration r WHERE r.race.id = :raceId")
    Page<RaceRegistration> findByRaceId(Long raceId, Pageable pageable);
    
    @Query("SELECT r FROM RaceRegistration r WHERE r.owner.id = :ownerId")
    Page<RaceRegistration> findByOwnerId(Long ownerId, Pageable pageable);
    
    @Query("SELECT r FROM RaceRegistration r WHERE r.race.id = :raceId AND r.status = :status")
    Page<RaceRegistration> findByRaceIdAndStatus(Long raceId, RegistrationStatus status, Pageable pageable);
    
    @Query("SELECT r FROM RaceRegistration r WHERE r.owner.id = :ownerId AND r.status = :status")
    Page<RaceRegistration> findByOwnerIdAndStatus(Long ownerId, RegistrationStatus status, Pageable pageable);
    
    Optional<RaceRegistration> findByRaceIdAndHorseId(Long raceId, Long horseId);
    Optional<RaceRegistration> findByRaceIdAndJockeyId(Long raceId, Long jockeyId);
    
    @Query("SELECT COUNT(r) FROM RaceRegistration r WHERE r.race.id = :raceId AND r.status = 'APPROVED'")
    Integer countApprovedByRaceId(Long raceId);
}
