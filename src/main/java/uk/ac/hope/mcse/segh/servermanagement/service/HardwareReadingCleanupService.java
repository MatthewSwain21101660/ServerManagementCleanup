package uk.ac.hope.mcse.segh.servermanagement.service;

import org.springframework.scheduling.annotation.Scheduled;
import uk.ac.hope.mcse.segh.servermanagement.model.HardwareReading;

import java.util.List;

public interface HardwareReadingCleanupService {
    void cleanupReading();
}