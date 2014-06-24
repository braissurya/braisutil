package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.GroupBiaya;
import com.melawai.ppuc.persistence.GroupBiayaMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Wed Jun 18 23:10:15 ICT 2014
 * @Description: Services for table group_biaya
 * @Revision	:
 */

@Service("groupbiayaManager")
public class GroupBiayaManager {

	private static Logger logger = Logger.getLogger(GroupBiayaManager.class);

	@Autowired
	private GroupBiayaMapper groupbiayaMapper;

	/** Ambil DATA berdasarkan kd_group **/
	public GroupBiaya get(String kd_group) {
		return groupbiayaMapper.get(kd_group);
	}

	/** Apakah data dengan kd_group ini ada? **/
	public boolean exists(String kd_group) {	
		return get(kd_group)!=null;
	}

	/** Delete data berdasarkan kd_group **/
	@Transactional
	public void remove(String kd_group) {
		groupbiayaMapper.remove(kd_group);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		GroupBiaya groupbiaya=new GroupBiaya();
		groupbiaya.setSearch(search);
		return groupbiayaMapper.selectPagingCount(groupbiaya);
	}

	/** Ambil data paging **/
	public List<GroupBiaya> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		GroupBiaya groupbiaya=new GroupBiaya();
		groupbiaya.setSearch(search);
		 if(sort!=null)groupbiaya.setSort(sort+" "+sortOrder);
		groupbiaya.setPage(page);
		groupbiaya.setRowcount(rowcount);
		return groupbiayaMapper.selectPagingList(groupbiaya);
	}

	/** Save Model **/
	@Transactional
	public GroupBiaya save(GroupBiaya groupbiaya) {
		if (groupbiaya.get?==null) {
			groupbiayaMapper.insert(groupbiaya);
		} else {
			groupbiayaMapper.update(groupbiaya);
		} 
		return groupbiaya;
	}
	/** Others Method **/

	}
