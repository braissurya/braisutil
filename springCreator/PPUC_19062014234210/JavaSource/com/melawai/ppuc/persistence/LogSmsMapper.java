package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.LogSms;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:38 ICT 2014
 * @Description: Mapper Interface for table sys_log_sms
 * @Revision	:
 */

public interface LogSmsMapper {

	public void insert(LogSms logsms) throws DataAccessException;

	public void update(LogSms logsms) throws DataAccessException;

	public void remove(Long id_log_sms) throws DataAccessException;

	public LogSms get(Long id_log_sms) throws DataAccessException;

	public List<LogSms> getAll() throws DataAccessException;

	public List<LogSms> selectPagingList(LogSms logsms) throws DataAccessException;

	public Integer selectPagingCount(LogSms logsms) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
