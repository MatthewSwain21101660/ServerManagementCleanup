package uk.ac.hope.mcse.segh.servermanagement.repo;

public interface DeleteOldReading {
    void deleteOldReadingNotEndingWith(String cutoffDateTime);
}
