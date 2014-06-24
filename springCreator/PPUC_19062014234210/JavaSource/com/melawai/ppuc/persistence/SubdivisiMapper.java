package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.Subdivisi;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:38 ICT 2014
 * @Description: Mapper Interface for table subdivisi
 * @Revision	:
 */

public interface SubdivisiMapper {

	public void insert(Subdivisi subdivisi) throws DataAccessException;

	public void update(Subdivisi subdivisi) throws DataAccessException;

	public void remove(String subdiv_kd) throws DataAccessException;

	public Subdivisi get(String subdiv_kd) throws DataAccessException;

	public List<Subdivisi> getAll() throws DataAccessException;

	public List<Subdivisi> selectPagingList(Subdivisi subdivisi) throws DataAccessException;

	public Integer selectPagingCount(Subdivisi subdivisi) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
