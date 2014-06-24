package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.GroupUser;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:33 ICT 2014
 * @Description: Mapper Interface for table group_user
 * @Revision	:
 */

public interface GroupUserMapper {

	public void insert(GroupUser groupuser) throws DataAccessException;

	public void update(GroupUser groupuser) throws DataAccessException;

	public void remove(String group_kd) throws DataAccessException;

	public GroupUser get(String group_kd) throws DataAccessException;

	public List<GroupUser> getAll() throws DataAccessException;

	public List<GroupUser> selectPagingList(GroupUser groupuser) throws DataAccessException;

	public Integer selectPagingCount(GroupUser groupuser) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
