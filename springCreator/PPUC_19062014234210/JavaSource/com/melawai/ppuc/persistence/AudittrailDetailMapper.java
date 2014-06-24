package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.AudittrailDetail;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:38 ICT 2014
 * @Description: Mapper Interface for table sys_audittrail_detail
 * @Revision	:
 */

public interface AudittrailDetailMapper {

	public void insert(AudittrailDetail audittraildetail) throws DataAccessException;

	public void update(AudittrailDetail audittraildetail) throws DataAccessException;

	public void remove(Long id_audittrail_detail) throws DataAccessException;

	public AudittrailDetail get(Long id_audittrail_detail) throws DataAccessException;

	public List<AudittrailDetail> getAll() throws DataAccessException;

	public List<AudittrailDetail> selectPagingList(AudittrailDetail audittraildetail) throws DataAccessException;

	public Integer selectPagingCount(AudittrailDetail audittraildetail) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
