package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.UserDivisi;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:39 ICT 2014
 * @Description: Mapper Interface for table user_divisi
 * @Revision	:
 */

public interface UserDivisiMapper {

	public void insert(UserDivisi userdivisi) throws DataAccessException;

	public void update(UserDivisi userdivisi) throws DataAccessException;

	public void remove(Long id_user_divisi) throws DataAccessException;

	public UserDivisi get(Long id_user_divisi) throws DataAccessException;

	public List<UserDivisi> getAll() throws DataAccessException;

	public List<UserDivisi> selectPagingList(UserDivisi userdivisi) throws DataAccessException;

	public Integer selectPagingCount(UserDivisi userdivisi) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
