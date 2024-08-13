package uk.ac.hope.mcse.segh.servermanagement.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import uk.ac.hope.mcse.segh.servermanagement.model.HardwareReading;

@Repository
public class DeleteOldReadingImpl implements DeleteOldReading {

    //Uses the mongo template to ensure that the new method is compatible
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void deleteOldReadingNotEndingWith(String cutoffDateTime) {
        //Creates a query that looks for any record where the dateTime entry is after the provided current time and that does not end in ":00"
        //In this way, all entries other than ones less than a minute old and taken on the turn of a minute will be deleted
        Query query = new Query(new Criteria().andOperator(
                Criteria.where("dateTime").lt(cutoffDateTime),
                Criteria.where("dateTime").not().regex(":00$")
        ));

        //Removes all documents that match the query
        mongoTemplate.remove(query, HardwareReading.class);
    }
}