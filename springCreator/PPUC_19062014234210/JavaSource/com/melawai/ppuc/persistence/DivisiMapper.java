package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.Divisi;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:32 ICT 2014
 * @Description: Mapper Interface for table divisi
 * @Revision	:
 */

public interface DivisiMapper {

	public void insert(Divisi divisi) throws DataAccessException;

	public void update(Divisi divisi) throws DataAccessException;

	public void remove(String divisi_kd) throws DataAccessException;

	public Divisi get(String divisi_kd) throws DataAccessException;

	public List<Divisi> getAll() throws DataAccessException;

	public List<Divisi> selectPagingList(Divisi divisi) throws DataAccessException;

	public Integer selectPagingCount(Divisi divisi) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
