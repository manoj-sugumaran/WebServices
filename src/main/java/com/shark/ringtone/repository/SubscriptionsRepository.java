/**
 * 
 */
package com.shark.ringtone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shark.ringtone.model.Subscription;

/**
 * @author manoj.s
 *
 */
@Repository
public interface SubscriptionsRepository extends JpaRepository<Subscription, String>{

}
