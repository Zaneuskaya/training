package com.senla.pdp.api.service.repository;

import com.senla.pdp.model.Event;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event,String> {

    Event findBy_id(ObjectId _id);


}
