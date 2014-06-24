package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.HakBiaya;
import com.melawai.ppuc.persistence.HakBiayaMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:34 ICT 2014
 * @Description: Services for table hak_biaya
 * @Revision	:
 */

@Service("hakbiayaManager")
public class HakBiayaManager {

	private static Logger logger = Logger.getLogger(HakBiayaManager.class);

	@Autowired
	private HakBiayaMapper hakbiayaMapper;

	/** Ambil DATA berdasarkan divisi_kd, subdiv_kd, kd_group, kd_biaya **/
	public HakBiaya get(String divisi_kd, String subdiv_kd, String kd_group, String kd_biaya) {
		return hakbiayaMapper.get(divisi_kd, subdiv_kd, kd_group, kd_biaya);
	}

	/** Apakah data dengan divisi_kd, subdiv_kd, kd_group, kd_biaya ini ada? **/
	public boolean exists(String divisi_kd, String subdiv_kd, String kd_group, String kd_biaya) {	
		return get(divisi_kd, subdiv_kd, kd_group, kd_biaya)!=null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(String divisi_kd, String subdiv_kd, String kd_group, String kd_biaya) {
		hakbiayaMapper.remove(divisi_kd, subdiv_kd, kd_group, kd_biaya);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		HakBiaya hakbiaya=new HakBiaya();
		hakbiaya.setSearch(search);
		return hakbiayaMapper.selectPagingCount(hakbiaya);
	}

	/** Ambil data paging **/
	public List<HakBiaya> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		HakBiaya hakbiaya=new HakBiaya();
		hakbiaya.setSearch(search);
		 if(sort!=null)hakbiaya.setSort(sort+" "+sortOrder);
		hakbiaya.setPage(page);
		hakbiaya.setRowcount(rowcount);
		return hakbiayaMapper.selectPagingList(hakbiaya);
	}

	/** Save Model **/
	@Transactional
	public HakBiaya save(HakBiaya hakbiaya) {
		if (hakbiaya.get?==null) {
			hakbiayaMapper.insert(hakbiaya);
		} else {
			hakbiayaMapper.update(hakbiaya);
		} 
		return hakbiaya;
	}
	/** Others Method **/

	}
