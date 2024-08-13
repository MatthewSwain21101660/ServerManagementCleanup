package uk.ac.hope.mcse.segh.servermanagement.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.ac.hope.mcse.segh.servermanagement.model.HardwareReading;

import java.math.BigInteger;
import java.util.List;

public interface HardwareReadingRepository extends MongoRepository <HardwareReading, BigInteger>, DeleteOldReading {
    //Using a custom method to delete all entries that are older than a minute and arent entries on the minute mark
    void deleteOldReadingNotEndingWith(String dateTime);
}
