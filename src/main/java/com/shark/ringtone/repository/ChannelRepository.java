package com.shark.ringtone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shark.ringtone.model.Channel;
/**
 * @author manoj.s
 *
 */
@Repository
public interface ChannelRepository extends JpaRepository<Channel, String> {
	
	
	@Query(value = "SELECT co FROM Channel co WHERE co.startdate <=LOCALTIMESTAMP"
			+ " AND co.enddate >= LOCALTIMESTAMP")
	List<Channel> findAll();
	
}
