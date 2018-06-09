package com.shark.ringtone.repository;



import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shark.ringtone.model.Content;

/**
 * @author manoj.s
 *
 */
@Repository
public interface ContentRepository extends JpaRepository<Content, String> {
	
	@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
	@Query(value = "SELECT count(co)>0 FROM Content co WHERE co.contentid = :contentid AND co.startdate <=LOCALTIMESTAMP"
			+ " AND co.enddate >= LOCALTIMESTAMP AND co.artworkurl !=null AND co.artworkurl !=''")
	boolean existsById(@Param("contentid")String contentid);
	
	@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
	@Query(value = "SELECT co FROM Content co WHERE co.contentid = :contentid AND co.startdate <=LOCALTIMESTAMP"
			+ " AND co.enddate >= LOCALTIMESTAMP AND co.artworkurl !=null AND co.artworkurl !=''")
	Content getOne(@Param("contentid")String contentid);
	
	
	//Cut allowed
	
	@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
	@Query(value = "SELECT count(co)>0 FROM Content co WHERE co.contentid = :contentid AND co.startdate <=LOCALTIMESTAMP"
			+ " AND co.enddate >= LOCALTIMESTAMP AND co.artworkurl !=null AND co.artworkurl !=''"
			+ " AND co.cut_allowed = :cut_allowed ")
	boolean existsById_cut(@Param("contentid")String contentid,@Param("cut_allowed")int cut_allowed);
	
	@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
	@Query(value = "SELECT co FROM Content co WHERE co.contentid = :contentid AND co.startdate <=LOCALTIMESTAMP"
			+ " AND co.enddate >= LOCALTIMESTAMP AND co.artworkurl !=null AND co.artworkurl !=''"
			+ " AND co.cut_allowed = :cut_allowed ")
	Content getOne_cut(@Param("contentid")String contentid,@Param("cut_allowed")int cut_allowed);

}
