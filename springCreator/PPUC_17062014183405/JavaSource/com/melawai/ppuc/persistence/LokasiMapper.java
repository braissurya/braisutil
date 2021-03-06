package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.Lokasi;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Tue Jun 17 18:34:41 ICT 2014
 * @Description: Mapper Interface for table lokasi
 * @Revision	:
 */

public interface LokasiMapper {

	public void insert(Lokasi lokasi) throws DataAccessException;

	public void update(Lokasi lokasi) throws DataAccessException;

	public void remove(String lok_kd) throws DataAccessException;

	public Lokasi get(String lok_kd) throws DataAccessException;

	public List<Lokasi> getAll() throws DataAccessException;

	public List<Lokasi> selectPagingList(Lokasi lokasi) throws DataAccessException;

	public Integer selectPagingCount(Lokasi lokasi) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
