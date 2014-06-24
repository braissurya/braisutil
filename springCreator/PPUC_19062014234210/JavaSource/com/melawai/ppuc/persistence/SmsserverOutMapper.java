package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.SmsserverOut;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:38 ICT 2014
 * @Description: Mapper Interface for table smsserver_out
 * @Revision	:
 */

public interface SmsserverOutMapper {

	public void insert(SmsserverOut smsserverout) throws DataAccessException;

	public void update(SmsserverOut smsserverout) throws DataAccessException;

	public void remove(Long id) throws DataAccessException;

	public SmsserverOut get(Long id) throws DataAccessException;

	public List<SmsserverOut> getAll() throws DataAccessException;

	public List<SmsserverOut> selectPagingList(SmsserverOut smsserverout) throws DataAccessException;

	public Integer selectPagingCount(SmsserverOut smsserverout) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
