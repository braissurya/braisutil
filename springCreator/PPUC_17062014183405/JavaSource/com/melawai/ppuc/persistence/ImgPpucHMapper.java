package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.ImgPpucH;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Tue Jun 17 18:34:39 ICT 2014
 * @Description: Mapper Interface for table img_ppuc_h
 * @Revision	:
 */

public interface ImgPpucHMapper {

	public void insert(ImgPpucH imgppuch) throws DataAccessException;

	public void update(ImgPpucH imgppuch) throws DataAccessException;

	public void remove(@Param("no_ppuc") String no_ppuc, @Param("tgl_ppuc") Date tgl_ppuc, @Param("divisi_kd") String divisi_kd, @Param("subdiv_kd") String subdiv_kd, @Param("dept_kd") String dept_kd, @Param("lok_kd") String lok_kd) throws DataAccessException;

	public ImgPpucH get(@Param("no_ppuc") String no_ppuc, @Param("tgl_ppuc") Date tgl_ppuc, @Param("divisi_kd") String divisi_kd, @Param("subdiv_kd") String subdiv_kd, @Param("dept_kd") String dept_kd, @Param("lok_kd") String lok_kd) throws DataAccessException;

	public List<ImgPpucH> getAll() throws DataAccessException;

	public List<ImgPpucH> selectPagingList(ImgPpucH imgppuch) throws DataAccessException;

	public Integer selectPagingCount(ImgPpucH imgppuch) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
