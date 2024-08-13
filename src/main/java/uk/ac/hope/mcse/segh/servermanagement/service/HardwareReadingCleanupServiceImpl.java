package uk.ac.hope.mcse.segh.servermanagement.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import uk.ac.hope.mcse.segh.servermanagement.repo.HardwareReadingRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
public class HardwareReadingCleanupServiceImpl implements HardwareReadingCleanupService {

    private final HardwareReadingRepository repository;

    @Autowired
    public HardwareReadingCleanupServiceImpl(HardwareReadingRepository repository){
        this.repository = repository;
    }

    //Run every second to ensure the oneMinuetAgo is up to date
    @Scheduled(fixedRate = 1000)
    @Override
    public void cleanupReading() {
        //Take a reading to see what the time was one minute ago
        LocalDateTime oneMinuteAgo = LocalDateTime.now().minusMinutes(1);

        //Format it into the correct manner
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String formattedTime = oneMinuteAgo.format(dtf);

        //Call the custom method that deletes unuseful entries and provides it with the formatted time
        repository.deleteOldReadingNotEndingWith(formattedTime);
    }
}
