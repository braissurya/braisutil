package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.GroupBiaya;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:32 ICT 2014
 * @Description: Mapper Interface for table group_biaya
 * @Revision	:
 */

public interface GroupBiayaMapper {

	public void insert(GroupBiaya groupbiaya) throws DataAccessException;

	public void update(GroupBiaya groupbiaya) throws DataAccessException;

	public void remove(String kd_group) throws DataAccessException;

	public GroupBiaya get(String kd_group) throws DataAccessException;

	public List<GroupBiaya> getAll() throws DataAccessException;

	public List<GroupBiaya> selectPagingList(GroupBiaya groupbiaya) throws DataAccessException;

	public Integer selectPagingCount(GroupBiaya groupbiaya) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
