package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.AudittrailDetail;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Mon Jun 16 00:24:04 ICT 2014
 * @Description: Mapper Interface for table audittrail_detail
 * @Revision	:
 */

public interface AudittrailDetailMapper {

	public void insert(AudittrailDetail audittraildetail) throws DataAccessException;

	public void update(AudittrailDetail audittraildetail) throws DataAccessException;

	public void remove(Long id) throws DataAccessException;

	public AudittrailDetail get(Long id) throws DataAccessException;

	public List<AudittrailDetail> getAll() throws DataAccessException;

	public List<AudittrailDetail> selectPagingList(AudittrailDetail audittraildetail) throws DataAccessException;

	public Integer selectPagingCount(AudittrailDetail audittraildetail) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
