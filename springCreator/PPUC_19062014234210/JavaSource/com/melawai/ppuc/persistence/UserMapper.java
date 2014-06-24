package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.User;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:39 ICT 2014
 * @Description: Mapper Interface for table user
 * @Revision	:
 */

public interface UserMapper {

	public void insert(User user) throws DataAccessException;

	public void update(User user) throws DataAccessException;

	public void remove(String user_id) throws DataAccessException;

	public User get(String user_id) throws DataAccessException;

	public List<User> getAll() throws DataAccessException;

	public List<User> selectPagingList(User user) throws DataAccessException;

	public Integer selectPagingCount(User user) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini

	public User loadUserByUsername(String user_id) throws DataAccessException;

}
