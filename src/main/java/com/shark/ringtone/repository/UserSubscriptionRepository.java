/**
 * 
 */
package com.shark.ringtone.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shark.ringtone.model.UserSubscription;

/**
 * @author manoj.s
 *
 */
@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, String> {

	@Query(value = "SELECT U FROM UserSubscription U WHERE U.firebase_uid = :firebase_uid AND U.status = 1")
	UserSubscription getActiveUserSubscription(@Param("firebase_uid")String firebase_uid);
	
	@Query(value = "SELECT U FROM UserSubscription U WHERE U.firebase_uid = :firebase_uid")
	UserSubscription getUserSubscriptionfromFirebaseuid(@Param("firebase_uid")String firebase_uid);
	
	@Query(value = "SELECT count(U)>0 FROM UserSubscription U WHERE U.firebase_uid = :firebase_uid")
	boolean existsByFirebaseuid(@Param("firebase_uid")String firebase_uid);
	
	@Query(value = "SELECT count(U)>0 FROM UserSubscription U WHERE U.firebase_uid = :firebase_uid AND U.status = 1")
	boolean existsActiveSubscription(@Param("firebase_uid")String firebase_uid);
	
}
