package com.cronos.training.model;

import java.util.Objects;

/**
 * Represents a trainer in the Cronos: The New Dawn game.
 * Each trainer has a unique ID, name, specialization, and experience level.
 */
public class Trainer {
    private final String id;
    private String name;
    private String specialization; // e.g., "Combat", "Tech", "Survival"
    private int experienceLevel; // 1-100

    /**
     * Constructs a new Trainer with required fields.
     *
     * @param id          Unique identifier for the trainer
     * @param name        Display name
     * @param specialization Area of expertise
     * @param experienceLevel Starting experience (1-100)
     */
    public Trainer(String id, String name, String specialization, int experienceLevel) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.experienceLevel = Math.max(1, Math.min(100, experienceLevel));
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getExperienceLevel() {
        return experienceLevel;
    }

    /**
     * Sets the experience level, clamped between 1 and 100.
     */
    public void setExperienceLevel(int experienceLevel) {
        this.experienceLevel = Math.max(1, Math.min(100, experienceLevel));
    }

    /**
     * Increases the trainer's experience by a given amount, capped at 100.
     *
     * @param points Amount to add (must be positive)
     * @return The new experience level
     */
    public int gainExperience(int points) {
        if (points > 0) {
            this.experienceLevel = Math.min(100, this.experienceLevel + points);
        }
        return this.experienceLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trainer)) return false;
        Trainer trainer = (Trainer) o;
        return id.equals(trainer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", experienceLevel=" + experienceLevel +
                '}';
    }
}
