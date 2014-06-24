package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.DetailBiaya;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:31 ICT 2014
 * @Description: Mapper Interface for table detail_biaya
 * @Revision	:
 */

public interface DetailBiayaMapper {

	public void insert(DetailBiaya detailbiaya) throws DataAccessException;

	public void update(DetailBiaya detailbiaya) throws DataAccessException;

	public void remove(String kd_biaya) throws DataAccessException;

	public DetailBiaya get(String kd_biaya) throws DataAccessException;

	public List<DetailBiaya> getAll() throws DataAccessException;

	public List<DetailBiaya> selectPagingList(DetailBiaya detailbiaya) throws DataAccessException;

	public Integer selectPagingCount(DetailBiaya detailbiaya) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
