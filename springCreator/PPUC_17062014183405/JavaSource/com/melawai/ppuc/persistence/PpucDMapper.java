package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.PpucD;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Tue Jun 17 18:34:46 ICT 2014
 * @Description: Mapper Interface for table ppuc_d
 * @Revision	:
 */

public interface PpucDMapper {

	public void insert(PpucD ppucd) throws DataAccessException;

	public void update(PpucD ppucd) throws DataAccessException;

	public void remove(@Param("no_ppuc") String no_ppuc, @Param("tgl_ppuc") Date tgl_ppuc, @Param("divisi_kd") String divisi_kd, @Param("subdiv_kd") String subdiv_kd, @Param("dept_kd") String dept_kd, @Param("lok_kd") String lok_kd, @Param("kd_biaya") String kd_biaya) throws DataAccessException;

	public PpucD get(@Param("no_ppuc") String no_ppuc, @Param("tgl_ppuc") Date tgl_ppuc, @Param("divisi_kd") String divisi_kd, @Param("subdiv_kd") String subdiv_kd, @Param("dept_kd") String dept_kd, @Param("lok_kd") String lok_kd, @Param("kd_biaya") String kd_biaya) throws DataAccessException;

	public List<PpucD> getAll() throws DataAccessException;

	public List<PpucD> selectPagingList(PpucD ppucd) throws DataAccessException;

	public Integer selectPagingCount(PpucD ppucd) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
