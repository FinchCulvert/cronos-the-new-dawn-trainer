package com.cronos.training.service;

import com.cronos.training.model.Trainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for TrainerManager.
 */
class TrainerManagerTest {

    private TrainerManager manager;

    @BeforeEach
    void setUp() {
        manager = new TrainerManager();
    }

    @Test
    void testAddAndGetTrainer() {
        Trainer trainer = new Trainer("T001", "Aria", "Combat", 45);
        manager.addTrainer(trainer);
        assertEquals(trainer, manager.getTrainer("T001"));
    }

    @Test
    void testRemoveTrainer() {
        Trainer trainer = new Trainer("T002", "Kael", "Tech", 30);
        manager.addTrainer(trainer);
        assertTrue(manager.removeTrainer("T002"));
        assertNull(manager.getTrainer("T002"));
    }

    @Test
    void testRemoveNonExistentTrainer() {
        assertFalse(manager.removeTrainer("NONEXISTENT"));
    }

    @Test
    void testGetAllTrainers() {
        Trainer t1 = new Trainer("T003", "Lena", "Survival", 70);
        Trainer t2 = new Trainer("T004", "Dorn", "Combat", 20);
        manager.addTrainer(t1);
        manager.addTrainer(t2);
        List<Trainer> all = manager.getAllTrainers();
        assertEquals(2, all.size());
        assertTrue(all.contains(t1));
        assertTrue(all.contains(t2));
    }

    @Test
    void testFindBySpecialization() {
        Trainer t1 = new Trainer("T005", "Mira", "Tech", 60);
        Trainer t2 = new Trainer("T006", "Rex", "Combat", 80);
        Trainer t3 = new Trainer("T007", "Nova", "Tech", 25);
        manager.addTrainer(t1);
        manager.addTrainer(t2);
        manager.addTrainer(t3);

        List<Trainer> techTrainers = manager.findTrainersBySpecialization("Tech");
        assertEquals(2, techTrainers.size());
        assertTrue(techTrainers.contains(t1));
        assertTrue(techTrainers.contains(t3));
    }

    @Test
    void testFindByMinExperience() {
        Trainer t1 = new Trainer("T008", "Zara", "Survival", 55);
        Trainer t2 = new Trainer("T009", "Thorn", "Combat", 90);
        Trainer t3 = new Trainer("T010", "Ivy", "Tech", 30);
        manager.addTrainer(t1);
        manager.addTrainer(t2);
        manager.addTrainer(t3);

        List<Trainer> experienced = manager.findTrainersByMinExperience(50);
        assertEquals(2, experienced.size());
        assertTrue(experienced.contains(t1));
        assertTrue(experienced.contains(t2));
    }

    @Test
    void testAddDuplicateTrainerReplaces() {
        Trainer t1 = new Trainer("T011", "Echo", "Combat", 10);
        Trainer t2 = new Trainer("T011", "Echo", "Tech", 99); // Same ID, different data
        manager.addTrainer(t1);
        manager.addTrainer(t2);
        assertEquals(1, manager.getTrainerCount());
        assertEquals("Tech", manager.getTrainer("T011").getSpecialization());
        assertEquals(99, manager.getTrainer("T011").getExperienceLevel());
    }

    @Test
    void testClear() {
        manager.addTrainer(new Trainer("T012", "Finn", "Combat", 50));
        manager.addTrainer(new Trainer("T013", "Lyra", "Survival", 40));
        manager.clear();
        assertEquals(0, manager.getTrainerCount());
    }

    @Test
    void testAddNullTrainerThrowsException() {
        assertThrows(NullPointerException.class, () -> manager.addTrainer(null));
    }
}
