package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.Audittrail;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:38 ICT 2014
 * @Description: Mapper Interface for table sys_audittrail
 * @Revision	:
 */

public interface AudittrailMapper {

	public void insert(Audittrail audittrail) throws DataAccessException;

	public void update(Audittrail audittrail) throws DataAccessException;

	public void remove(Long id_audittrail) throws DataAccessException;

	public Audittrail get(Long id_audittrail) throws DataAccessException;

	public List<Audittrail> getAll() throws DataAccessException;

	public List<Audittrail> selectPagingList(Audittrail audittrail) throws DataAccessException;

	public Integer selectPagingCount(Audittrail audittrail) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
