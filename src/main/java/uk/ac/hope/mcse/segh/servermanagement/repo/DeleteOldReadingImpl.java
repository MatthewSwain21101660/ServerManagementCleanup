package uk.ac.hope.mcse.segh.servermanagement.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import uk.ac.hope.mcse.segh.servermanagement.model.HardwareReading;

@Repository
public class DeleteOldReadingImpl implements DeleteOldReading {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void deleteOldReadingNotEndingWith(String cutoffDateTime) {
       // Create a query to find documents with dateTime less than cutoffDateTime
        // and dateTime not ending with the specified suffix ":00"
        Query query = new Query(new Criteria().andOperator(
                Criteria.where("dateTime").lt(cutoffDateTime),
                Criteria.where("dateTime").not().regex(":00$")
        ));

        // Delete all matching documents
        mongoTemplate.remove(query, HardwareReading.class);
    }
}