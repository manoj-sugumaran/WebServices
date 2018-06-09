/**
 * 
 */
package com.shark.ringtone.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shark.ringtone.model.DownloadedTracks;


/**
 * @author manoj.s
 *
 */
@Repository
public interface DownloadedTracksRepository extends JpaRepository<DownloadedTracks, String>  {
	
	@Query(value = "SELECT D FROM DownloadedTracks D WHERE D.firebase_uid = :firebase_uid AND D.downloadedtime>= :starttime AND D.downloadedtime <= :endtime order by downloadedtime desc")
    List<DownloadedTracks> getDownloadHisory(@Param("firebase_uid")String firebase_uid, @Param("starttime") Date startdate,@Param("endtime") Date enddate, Pageable pageable);

}
