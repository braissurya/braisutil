package com.melawai.ppuc.services;

import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.HakApprove;
import com.melawai.ppuc.persistence.HakApproveMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Mon Jun 16 00:24:06 ICT 2014
 * @Description: Services for table hak_approve
 * @Revision	:
 */

@Service("hakapproveManager")
public class HakApproveManager {

	private static Logger logger = Logger.getLogger(HakApproveManager.class);

	@Autowired
	private HakApproveMapper hakapproveMapper;

	/** Ambil DATA berdasarkan user_id, divisi_kd, subdiv_kd, dept_kd, kd_group, kd_biaya **/
	public HakApprove get(String user_id, String divisi_kd, String subdiv_kd, String dept_kd, String kd_group, String kd_biaya) {
		return hakapproveMapper.get(user_id, divisi_kd, subdiv_kd, dept_kd, kd_group, kd_biaya);
	}

	/** Apakah data dengan user_id, divisi_kd, subdiv_kd, dept_kd, kd_group, kd_biaya ini ada? **/
	public boolean exists(String user_id, String divisi_kd, String subdiv_kd, String dept_kd, String kd_group, String kd_biaya) {	
		return get(user_id, divisi_kd, subdiv_kd, dept_kd, kd_group, kd_biaya)!=null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(String user_id, String divisi_kd, String subdiv_kd, String dept_kd, String kd_group, String kd_biaya) {
		hakapproveMapper.remove(user_id, divisi_kd, subdiv_kd, dept_kd, kd_group, kd_biaya);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		HakApprove hakapprove=new HakApprove();
		hakapprove.setSearch(search);
		return hakapproveMapper.selectPagingCount(hakapprove);
	}

	/** Ambil data paging **/
	public List<HakApprove> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		HakApprove hakapprove=new HakApprove();
		hakapprove.setSearch(search);
		 if(sort!=null)hakapprove.setSort(sort+" "+sortOrder);
		hakapprove.setPage(page);
		hakapprove.setRowcount(rowcount);
		return hakapproveMapper.selectPagingList(hakapprove);
	}

	/** Save Model **/
	@Transactional
	public HakApprove save(HakApprove hakapprove) {
		if (hakapprove.get?==null) {
			hakapproveMapper.insert(hakapprove);
		} else {
			hakapproveMapper.update(hakapprove);
		} 
		return hakapprove;
	}
	/** Others Method **/

	}
