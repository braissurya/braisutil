package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.PpucH;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:37 ICT 2014
 * @Description: Mapper Interface for table ppuc_h
 * @Revision	:
 */

public interface PpucHMapper {

	public void insert(PpucH ppuch) throws DataAccessException;

	public void update(PpucH ppuch) throws DataAccessException;

	public void remove(@Param("divisi_kd") String divisi_kd, @Param("subdiv_kd") String subdiv_kd, @Param("dept_kd") String dept_kd, @Param("lok_kd") String lok_kd, @Param("no_ppuc") String no_ppuc, @Param("tgl_ppuc") Date tgl_ppuc) throws DataAccessException;

	public PpucH get(@Param("divisi_kd") String divisi_kd, @Param("subdiv_kd") String subdiv_kd, @Param("dept_kd") String dept_kd, @Param("lok_kd") String lok_kd, @Param("no_ppuc") String no_ppuc, @Param("tgl_ppuc") Date tgl_ppuc) throws DataAccessException;

	public List<PpucH> getAll() throws DataAccessException;

	public List<PpucH> selectPagingList(PpucH ppuch) throws DataAccessException;

	public Integer selectPagingCount(PpucH ppuch) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
