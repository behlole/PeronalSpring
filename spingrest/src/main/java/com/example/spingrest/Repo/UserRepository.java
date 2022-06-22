package com.example.spingrest.Repo;


import com.example.spingrest.Models.User;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public User insert(User user) {
        return mongoTemplate.insert(user);
    }

    public User deleteUser(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(new ObjectId(id)));
        return mongoTemplate.findAndRemove(query, User.class);
    }

    public User getUserById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, User.class);
    }

    public List<User> getAllUsers() {
        return mongoTemplate.findAll(User.class);
    }

    public String update(User user, String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("name", user.getName());
        update.set("age", user.getAge());
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, User.class);
        return updateResult.getMatchedCount() != 0 ? "Record Updated" : "Record Not Found";
    }
}
