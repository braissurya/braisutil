package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.SmsserverIn;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:38 ICT 2014
 * @Description: Mapper Interface for table smsserver_in
 * @Revision	:
 */

public interface SmsserverInMapper {

	public void insert(SmsserverIn smsserverin) throws DataAccessException;

	public void update(SmsserverIn smsserverin) throws DataAccessException;

	public void remove(Long id) throws DataAccessException;

	public SmsserverIn get(Long id) throws DataAccessException;

	public List<SmsserverIn> getAll() throws DataAccessException;

	public List<SmsserverIn> selectPagingList(SmsserverIn smsserverin) throws DataAccessException;

	public Integer selectPagingCount(SmsserverIn smsserverin) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
