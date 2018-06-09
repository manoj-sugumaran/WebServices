package com.shark.ringtone.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shark.ringtone.model.Channel;
import com.shark.ringtone.model.Config;
/**
 * @author manoj.s
 *
 */
@Repository
public interface ConfigRepository extends JpaRepository<Config, String> {

}
