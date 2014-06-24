package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Role;
import com.melawai.ppuc.persistence.RoleMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:37 ICT 2014
 * @Description: Services for table role
 * @Revision	:
 */

@Service("roleManager")
public class RoleManager {

	private static Logger logger = Logger.getLogger(RoleManager.class);

	@Autowired
	private RoleMapper roleMapper;

	/** Ambil DATA berdasarkan id_role **/
	public Role get(Long id_role) {
		return roleMapper.get(id_role);
	}

	/** Apakah data dengan id_role ini ada? **/
	public boolean exists(Long id_role) {	
		return get(id_role)!=null;
	}

	/** Delete data berdasarkan id_role **/
	@Transactional
	public void remove(Long id_role) {
		roleMapper.remove(id_role);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		Role role=new Role();
		role.setSearch(search);
		return roleMapper.selectPagingCount(role);
	}

	/** Ambil data paging **/
	public List<Role> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		Role role=new Role();
		role.setSearch(search);
		 if(sort!=null)role.setSort(sort+" "+sortOrder);
		role.setPage(page);
		role.setRowcount(rowcount);
		return roleMapper.selectPagingList(role);
	}

	/** Save Model **/
	@Transactional
	public Role save(Role role) {
		if (role.get?==null) {
			roleMapper.insert(role);
		} else {
			roleMapper.update(role);
		} 
		return role;
	}
	/** Others Method **/

	}
