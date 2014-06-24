package com.melawai.ppuc.services;

import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.GroupUser;
import com.melawai.ppuc.persistence.GroupUserMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Mon Jun 16 00:24:06 ICT 2014
 * @Description: Services for table group_user
 * @Revision	:
 */

@Service("groupuserManager")
public class GroupUserManager {

	private static Logger logger = Logger.getLogger(GroupUserManager.class);

	@Autowired
	private GroupUserMapper groupuserMapper;

	/** Ambil DATA berdasarkan group_kd **/
	public GroupUser get(String group_kd) {
		return groupuserMapper.get(group_kd);
	}

	/** Apakah data dengan group_kd ini ada? **/
	public boolean exists(String group_kd) {	
		return get(group_kd)!=null;
	}

	/** Delete data berdasarkan group_kd **/
	@Transactional
	public void remove(String group_kd) {
		groupuserMapper.remove(group_kd);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		GroupUser groupuser=new GroupUser();
		groupuser.setSearch(search);
		return groupuserMapper.selectPagingCount(groupuser);
	}

	/** Ambil data paging **/
	public List<GroupUser> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		GroupUser groupuser=new GroupUser();
		groupuser.setSearch(search);
		 if(sort!=null)groupuser.setSort(sort+" "+sortOrder);
		groupuser.setPage(page);
		groupuser.setRowcount(rowcount);
		return groupuserMapper.selectPagingList(groupuser);
	}

	/** Save Model **/
	@Transactional
	public GroupUser save(GroupUser groupuser) {
		if (groupuser.get?==null) {
			groupuserMapper.insert(groupuser);
		} else {
			groupuserMapper.update(groupuser);
		} 
		return groupuser;
	}
	/** Others Method **/

	}
