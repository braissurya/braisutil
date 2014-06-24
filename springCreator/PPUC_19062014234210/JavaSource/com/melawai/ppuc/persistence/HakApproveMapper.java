package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.HakApprove;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:33 ICT 2014
 * @Description: Mapper Interface for table hak_approve
 * @Revision	:
 */

public interface HakApproveMapper {

	public void insert(HakApprove hakapprove) throws DataAccessException;

	public void update(HakApprove hakapprove) throws DataAccessException;

	public void remove(@Param("user_id") String user_id, @Param("divisi_kd") String divisi_kd, @Param("subdiv_kd") String subdiv_kd, @Param("dept_kd") String dept_kd, @Param("kd_group") String kd_group, @Param("kd_biaya") String kd_biaya) throws DataAccessException;

	public HakApprove get(@Param("user_id") String user_id, @Param("divisi_kd") String divisi_kd, @Param("subdiv_kd") String subdiv_kd, @Param("dept_kd") String dept_kd, @Param("kd_group") String kd_group, @Param("kd_biaya") String kd_biaya) throws DataAccessException;

	public List<HakApprove> getAll() throws DataAccessException;

	public List<HakApprove> selectPagingList(HakApprove hakapprove) throws DataAccessException;

	public Integer selectPagingCount(HakApprove hakapprove) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
