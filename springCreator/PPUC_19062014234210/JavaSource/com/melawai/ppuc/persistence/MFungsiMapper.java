package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.MFungsi;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:37 ICT 2014
 * @Description: Mapper Interface for table m_fungsi
 * @Revision	:
 */

public interface MFungsiMapper {

	public void insert(MFungsi mfungsi) throws DataAccessException;

	public void update(MFungsi mfungsi) throws DataAccessException;

	public void remove(String kd_fungsi) throws DataAccessException;

	public MFungsi get(String kd_fungsi) throws DataAccessException;

	public List<MFungsi> getAll() throws DataAccessException;

	public List<MFungsi> selectPagingList(MFungsi mfungsi) throws DataAccessException;

	public Integer selectPagingCount(MFungsi mfungsi) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
