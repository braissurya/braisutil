package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.Role;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:37 ICT 2014
 * @Description: Mapper Interface for table role
 * @Revision	:
 */

public interface RoleMapper {

	public void insert(Role role) throws DataAccessException;

	public void update(Role role) throws DataAccessException;

	public void remove(Long id_role) throws DataAccessException;

	public Role get(Long id_role) throws DataAccessException;

	public List<Role> getAll() throws DataAccessException;

	public List<Role> selectPagingList(Role role) throws DataAccessException;

	public Integer selectPagingCount(Role role) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
