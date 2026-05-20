package com.cronos.training.service;

import com.cronos.training.model.Trainer;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Manages a collection of trainers for Cronos: The New Dawn.
 * Provides CRUD operations and filtering based on specialization or experience.
 */
public class TrainerManager {
    private final Map<String, Trainer> trainers = new HashMap<>();

    /**
     * Adds a trainer to the manager. If a trainer with the same ID exists,
     * it will be replaced.
     *
     * @param trainer The trainer to add
     */
    public void addTrainer(Trainer trainer) {
        Objects.requireNonNull(trainer, "Trainer cannot be null");
        trainers.put(trainer.getId(), trainer);
    }

    /**
     * Removes a trainer by ID.
     *
     * @param trainerId The ID of the trainer to remove
     * @return true if a trainer was removed, false otherwise
     */
    public boolean removeTrainer(String trainerId) {
        return trainers.remove(trainerId) != null;
    }

    /**
     * Retrieves a trainer by ID.
     *
     * @param trainerId The ID to look up
     * @return The trainer, or null if not found
     */
    public Trainer getTrainer(String trainerId) {
        return trainers.get(trainerId);
    }

    /**
     * Returns all trainers currently managed.
     *
     * @return A new list containing all trainers
     */
    public List<Trainer> getAllTrainers() {
        return new ArrayList<>(trainers.values());
    }

    /**
     * Finds all trainers with a specific specialization.
     *
     * @param specialization The specialization to filter by (case-sensitive)
     * @return List of matching trainers
     */
    public List<Trainer> findTrainersBySpecialization(String specialization) {
        return trainers.values().stream()
                .filter(t -> t.getSpecialization().equals(specialization))
                .collect(Collectors.toList());
    }

    /**
     * Finds trainers with experience level at or above a threshold.
     *
     * @param minExperience Minimum experience level (inclusive)
     * @return List of trainers meeting the criteria
     */
    public List<Trainer> findTrainersByMinExperience(int minExperience) {
        return trainers.values().stream()
                .filter(t -> t.getExperienceLevel() >= minExperience)
                .collect(Collectors.toList());
    }

    /**
     * Returns the number of trainers managed.
     */
    public int getTrainerCount() {
        return trainers.size();
    }

    /**
     * Clears all trainers from the manager.
     */
    public void clear() {
        trainers.clear();
    }
}
