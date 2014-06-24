package com.melawai.ppuc.services;

import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.AksesMenu;
import com.melawai.ppuc.persistence.AksesMenuMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Wed Jun 18 22:59:05 ICT 2014
 * @Description: Services for table akses_menu
 * @Revision	:
 */

@Service("aksesmenuManager")
public class AksesMenuManager {

	private static Logger logger = Logger.getLogger(AksesMenuManager.class);

	@Autowired
	private AksesMenuMapper aksesmenuMapper;

	/** Ambil DATA berdasarkan group_kd, menu_id **/
	public AksesMenu get(String group_kd, Long menu_id) {
		return aksesmenuMapper.get(group_kd, menu_id);
	}

	/** Apakah data dengan group_kd, menu_id ini ada? **/
	public boolean exists(String group_kd, Long menu_id) {	
		return get(group_kd, menu_id)!=null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(String group_kd, Long menu_id) {
		aksesmenuMapper.remove(group_kd, menu_id);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		AksesMenu aksesmenu=new AksesMenu();
		aksesmenu.setSearch(search);
		return aksesmenuMapper.selectPagingCount(aksesmenu);
	}

	/** Ambil data paging **/
	public List<AksesMenu> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		AksesMenu aksesmenu=new AksesMenu();
		aksesmenu.setSearch(search);
		 if(sort!=null)aksesmenu.setSort(sort+" "+sortOrder);
		aksesmenu.setPage(page);
		aksesmenu.setRowcount(rowcount);
		return aksesmenuMapper.selectPagingList(aksesmenu);
	}

	/** Save Model **/
	@Transactional
	public AksesMenu save(AksesMenu aksesmenu) {
		if (aksesmenu.get?==null) {
			aksesmenuMapper.insert(aksesmenu);
		} else {
			aksesmenuMapper.update(aksesmenu);
		} 
		return aksesmenu;
	}
	/** Others Method **/

	}
