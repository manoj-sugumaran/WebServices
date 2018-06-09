package com.shark.ringtone.repository;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shark.ringtone.model.User;
/**
 * @author manoj.s
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query(value = "SELECT count(U)>0 FROM User U WHERE U.firebase_uid = :firebase_uid AND U.is_active = 1")
	boolean existsActiveUser(@Param("firebase_uid")String firebase_uid);
}
