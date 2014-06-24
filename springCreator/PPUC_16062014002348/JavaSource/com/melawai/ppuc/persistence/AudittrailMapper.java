package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.Audittrail;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Mon Jun 16 00:24:04 ICT 2014
 * @Description: Mapper Interface for table audittrail
 * @Revision	:
 */

public interface AudittrailMapper {

	public void insert(Audittrail audittrail) throws DataAccessException;

	public void update(Audittrail audittrail) throws DataAccessException;

	public void remove(Long id) throws DataAccessException;

	public Audittrail get(Long id) throws DataAccessException;

	public List<Audittrail> getAll() throws DataAccessException;

	public List<Audittrail> selectPagingList(Audittrail audittrail) throws DataAccessException;

	public Integer selectPagingCount(Audittrail audittrail) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
