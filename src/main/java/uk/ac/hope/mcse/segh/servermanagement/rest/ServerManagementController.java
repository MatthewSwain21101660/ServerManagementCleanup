package uk.ac.hope.mcse.segh.servermanagement.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import uk.ac.hope.mcse.segh.servermanagement.service.HardwareReadingCleanupService;
import uk.ac.hope.mcse.segh.servermanagement.model.HardwareReading;
import uk.ac.hope.mcse.segh.servermanagement.repo.HardwareReadingRepository;

import java.util.List;


@RestController
class ServerManagementController {

    private HardwareReadingRepository repository;
    private final HardwareReadingCleanupService hardwareReadingCleanupService;

    @Autowired
    public ServerManagementController(HardwareReadingCleanupService hardwareReadingCleanupService) {
        this.hardwareReadingCleanupService = hardwareReadingCleanupService;
    }

    //Calls the cleanupReading function
    @Scheduled(fixedRate = 1000)
    @Async
    void cleanupReading() { hardwareReadingCleanupService.cleanupReading(); }
}



