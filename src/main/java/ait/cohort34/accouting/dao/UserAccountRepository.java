package ait.cohort34.accouting.dao;

import ait.cohort34.accouting.model.UserAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserAccountRepository extends MongoRepository<UserAccount, String> {}
