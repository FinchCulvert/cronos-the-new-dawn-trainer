package com.cronos.training.io;

import com.cronos.training.model.Trainer;
import com.cronos.training.service.TrainerManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Handles persistent storage of trainer data to/from JSON files.
 * Used to save and load trainer rosters for Cronos: The New Dawn.
 */
public class TrainerDataStore {
    private final Gson gson;

    public TrainerDataStore() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Saves all trainers from a TrainerManager to a JSON file.
     *
     * @param manager The trainer manager containing the data
     * @param filePath Path to the output JSON file
     * @throws IOException If file writing fails
     */
    public void saveTrainers(TrainerManager manager, String filePath) throws IOException {
        List<Trainer> trainerList = manager.getAllTrainers();
        String json = gson.toJson(trainerList);
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
        }
    }

    /**
     * Loads trainers from a JSON file and adds them to the given manager.
     * Existing trainers in the manager are preserved unless IDs collide.
     *
     * @param manager The trainer manager to populate
     * @param filePath Path to the JSON file
     * @throws IOException If file reading fails
     */
    public void loadTrainers(TrainerManager manager, String filePath) throws IOException {
        Type listType = new TypeToken<List<Trainer>>() {}.getType();
        try (FileReader reader = new FileReader(filePath)) {
            List<Trainer> trainerList = gson.fromJson(reader, listType);
            if (trainerList != null) {
                for (Trainer trainer : trainerList) {
                    manager.addTrainer(trainer);
                }
            }
        }
    }

    /**
     * Exports a single trainer to a JSON string (utility method).
     *
     * @param trainer The trainer to serialize
     * @return JSON string representation
     */
    public String exportTrainerToJson(Trainer trainer) {
        return gson.toJson(trainer);
    }

    /**
     * Imports a trainer from a JSON string.
     *
     * @param json The JSON string representing a Trainer
     * @return The deserialized Trainer object
     */
    public Trainer importTrainerFromJson(String json) {
        return gson.fromJson(json, Trainer.class);
    }
}
