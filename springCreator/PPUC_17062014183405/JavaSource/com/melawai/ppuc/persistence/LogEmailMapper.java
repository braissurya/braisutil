package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.LogEmail;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Tue Jun 17 18:34:39 ICT 2014
 * @Description: Mapper Interface for table log_email
 * @Revision	:
 */

public interface LogEmailMapper {

	public void insert(LogEmail logemail) throws DataAccessException;

	public void update(LogEmail logemail) throws DataAccessException;

	public void remove(Long id) throws DataAccessException;

	public LogEmail get(Long id) throws DataAccessException;

	public List<LogEmail> getAll() throws DataAccessException;

	public List<LogEmail> selectPagingList(LogEmail logemail) throws DataAccessException;

	public Integer selectPagingCount(LogEmail logemail) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
