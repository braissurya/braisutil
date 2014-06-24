package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.HakBiaya;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:34 ICT 2014
 * @Description: Mapper Interface for table hak_biaya
 * @Revision	:
 */

public interface HakBiayaMapper {

	public void insert(HakBiaya hakbiaya) throws DataAccessException;

	public void update(HakBiaya hakbiaya) throws DataAccessException;

	public void remove(@Param("divisi_kd") String divisi_kd, @Param("subdiv_kd") String subdiv_kd, @Param("kd_group") String kd_group, @Param("kd_biaya") String kd_biaya) throws DataAccessException;

	public HakBiaya get(@Param("divisi_kd") String divisi_kd, @Param("subdiv_kd") String subdiv_kd, @Param("kd_group") String kd_group, @Param("kd_biaya") String kd_biaya) throws DataAccessException;

	public List<HakBiaya> getAll() throws DataAccessException;

	public List<HakBiaya> selectPagingList(HakBiaya hakbiaya) throws DataAccessException;

	public Integer selectPagingCount(HakBiaya hakbiaya) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
