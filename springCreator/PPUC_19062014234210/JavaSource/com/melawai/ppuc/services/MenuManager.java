package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Menu;
import com.melawai.ppuc.persistence.MenuMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:35 ICT 2014
 * @Description: Services for table menu
 * @Revision	:
 */

@Service("menuManager")
public class MenuManager {

	private static Logger logger = Logger.getLogger(MenuManager.class);

	@Autowired
	private MenuMapper menuMapper;

	/** Ambil DATA berdasarkan menu_id **/
	public Menu get(Long menu_id) {
		return menuMapper.get(menu_id);
	}

	/** Apakah data dengan menu_id ini ada? **/
	public boolean exists(Long menu_id) {	
		return get(menu_id)!=null;
	}

	/** Delete data berdasarkan menu_id **/
	@Transactional
	public void remove(Long menu_id) {
		menuMapper.remove(menu_id);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		Menu menu=new Menu();
		menu.setSearch(search);
		return menuMapper.selectPagingCount(menu);
	}

	/** Ambil data paging **/
	public List<Menu> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		Menu menu=new Menu();
		menu.setSearch(search);
		 if(sort!=null)menu.setSort(sort+" "+sortOrder);
		menu.setPage(page);
		menu.setRowcount(rowcount);
		return menuMapper.selectPagingList(menu);
	}

	/** Save Model **/
	@Transactional
	public Menu save(Menu menu) {
		if (menu.getMenu_id()==null) {
			menuMapper.insert(menu);
		} else {
			menuMapper.update(menu);
		} 
		return menu;
	}
	/** Others Method **/

	}
