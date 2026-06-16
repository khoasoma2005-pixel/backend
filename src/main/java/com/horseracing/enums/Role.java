package com.horseracing.enums;

/**
 * User roles in the system.
 * Note: SRS defines organizer with 3 departments (Coordinator, Referee, Veterinarian)
 * but no separate permissions required. All use ORGANIZER role.
 * Future expansion may add: REFEREE, VETERINARIAN with specific permissions.
 */
public enum Role {
    ADMIN,
    ORGANIZER,
    HORSE_OWNER,
    SPECTATOR
}