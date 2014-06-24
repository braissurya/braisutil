package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.Counter;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:38 ICT 2014
 * @Description: Mapper Interface for table sys_counter
 * @Revision	:
 */

public interface CounterMapper {

	public void insert(Counter counter) throws DataAccessException;

	public void update(Counter counter) throws DataAccessException;

	public void remove(Long id) throws DataAccessException;

	public Counter get(Long id) throws DataAccessException;

	public List<Counter> getAll() throws DataAccessException;

	public List<Counter> selectPagingList(Counter counter) throws DataAccessException;

	public Integer selectPagingCount(Counter counter) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
