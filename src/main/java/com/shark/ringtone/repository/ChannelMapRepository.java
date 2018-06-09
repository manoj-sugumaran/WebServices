package com.shark.ringtone.repository;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shark.ringtone.model.ChannelMap;
import com.shark.ringtone.model.ChannelMapKey;

/**
 * @author manoj.s
 *
 */
@Repository
public interface ChannelMapRepository extends JpaRepository<ChannelMap, ChannelMapKey> {
	
	@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
	@Query(value = "select c from ChannelMap c JOIN Content co ON c.channelMapKey.contentid = co.contentid where c.channelMapKey.channelid = :channelid AND co.startdate <=LOCALTIMESTAMP"
			+ " AND co.enddate >= LOCALTIMESTAMP AND co.artworkurl !=null AND co.artworkurl != '' order by c.contentorder desc")
    List<ChannelMap> findByChannelMapKeyChannelid(@Param("channelid") String channelid, Pageable pageable );
	
	@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
	@Query(value = "select c from ChannelMap c JOIN Content co ON c.channelMapKey.contentid = co.contentid where co.startdate <=LOCALTIMESTAMP"
			+ " AND co.enddate >= LOCALTIMESTAMP AND co.artworkurl !=null AND co.artworkurl !='' order by c.contentorder desc")
    List<ChannelMap> findByChannelMapMerchandized(Pageable pageable );
	
	@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
	@Query(value = "select c from ChannelMap c JOIN Content co ON c.channelMapKey.contentid = co.contentid"
			+ " where c.channelMapKey.channelid = :channelid AND c.context_start_date <= LOCALTIMESTAMP AND c.context_start_date!=null "
			+ " AND co.enddate >= LOCALTIMESTAMP AND co.artworkurl !=null AND co.artworkurl != '' "
			+ " AND co.song_teaser !=null and co.song_trivia !=null AND co.song_teaser !='' and co.song_trivia !='' order by c.context_start_date desc")
    List<ChannelMap> findByChannelMapStories(@Param("channelid") String channelid, Pageable pageable );
	
	//Cut allowed feature
	
	@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
	@Query(value = "select c from ChannelMap c JOIN Content co ON c.channelMapKey.contentid = co.contentid where c.channelMapKey.channelid = :channelid AND co.startdate <=LOCALTIMESTAMP"
			+ " AND co.enddate >= LOCALTIMESTAMP AND co.artworkurl !=null AND co.artworkurl != ''"
			+ " AND co.cut_allowed = :cut_allowed order by c.contentorder desc")
    List<ChannelMap> findByChannelMapKeyChannelid_cut(@Param("channelid") String channelid,@Param("cut_allowed")int cut_allowed, Pageable pageable );
	
	@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
	@Query(value = "select c from ChannelMap c JOIN Content co ON c.channelMapKey.contentid = co.contentid where co.startdate <=LOCALTIMESTAMP"
			+ " AND co.enddate >= LOCALTIMESTAMP AND co.artworkurl !=null AND co.artworkurl !=''"
			+ " AND co.cut_allowed = :cut_allowed order by c.contentorder desc")
    List<ChannelMap> findByChannelMapMerchandized_cut(@Param("cut_allowed")int cut_allowed,Pageable pageable );
	
	@QueryHints(@QueryHint(name = "org.hibernate.cacheable", value = "true"))
	@Query(value = "select c from ChannelMap c JOIN Content co ON c.channelMapKey.contentid = co.contentid"
			+ " where c.channelMapKey.channelid = :channelid AND c.context_start_date <= LOCALTIMESTAMP AND c.context_start_date!=null "
			+ " AND co.enddate >= LOCALTIMESTAMP AND co.artworkurl !=null AND co.artworkurl != '' "
			+ " AND co.song_teaser !=null and co.song_trivia !=null AND co.song_teaser !='' and co.song_trivia !=''"
			+ " AND co.cut_allowed = :cut_allowed order by c.context_start_date desc")
    List<ChannelMap> findByChannelMapStories_cut(@Param("channelid") String channelid,@Param("cut_allowed")int cut_allowed, Pageable pageable );
}
