package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.AksesMenu;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Mon Jun 16 00:24:04 ICT 2014
 * @Description: Mapper Interface for table akses_menu
 * @Revision	:
 */

public interface AksesMenuMapper {

	public void insert(AksesMenu aksesmenu) throws DataAccessException;

	public void update(AksesMenu aksesmenu) throws DataAccessException;

	public void remove(@Param("group_kd") String group_kd, @Param("menu_id") Long menu_id) throws DataAccessException;

	public AksesMenu get(@Param("group_kd") String group_kd, @Param("menu_id") Long menu_id) throws DataAccessException;

	public List<AksesMenu> getAll() throws DataAccessException;

	public List<AksesMenu> selectPagingList(AksesMenu aksesmenu) throws DataAccessException;

	public Integer selectPagingCount(AksesMenu aksesmenu) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
