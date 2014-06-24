package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.SmsserverCalls;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:37 ICT 2014
 * @Description: Mapper Interface for table smsserver_calls
 * @Revision	:
 */

public interface SmsserverCallsMapper {

	public void insert(SmsserverCalls smsservercalls) throws DataAccessException;

	public void update(SmsserverCalls smsservercalls) throws DataAccessException;

	public void remove(Long id) throws DataAccessException;

	public SmsserverCalls get(Long id) throws DataAccessException;

	public List<SmsserverCalls> getAll() throws DataAccessException;

	public List<SmsserverCalls> selectPagingList(SmsserverCalls smsservercalls) throws DataAccessException;

	public Integer selectPagingCount(SmsserverCalls smsservercalls) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
