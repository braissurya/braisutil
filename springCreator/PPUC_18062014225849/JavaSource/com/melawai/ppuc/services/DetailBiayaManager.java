package com.melawai.ppuc.services;

import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.DetailBiaya;
import com.melawai.ppuc.persistence.DetailBiayaMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Wed Jun 18 22:59:06 ICT 2014
 * @Description: Services for table detail_biaya
 * @Revision	:
 */

@Service("detailbiayaManager")
public class DetailBiayaManager {

	private static Logger logger = Logger.getLogger(DetailBiayaManager.class);

	@Autowired
	private DetailBiayaMapper detailbiayaMapper;

	/** Ambil DATA berdasarkan kd_biaya **/
	public DetailBiaya get(String kd_biaya) {
		return detailbiayaMapper.get(kd_biaya);
	}

	/** Apakah data dengan kd_biaya ini ada? **/
	public boolean exists(String kd_biaya) {	
		return get(kd_biaya)!=null;
	}

	/** Delete data berdasarkan kd_biaya **/
	@Transactional
	public void remove(String kd_biaya) {
		detailbiayaMapper.remove(kd_biaya);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		DetailBiaya detailbiaya=new DetailBiaya();
		detailbiaya.setSearch(search);
		return detailbiayaMapper.selectPagingCount(detailbiaya);
	}

	/** Ambil data paging **/
	public List<DetailBiaya> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		DetailBiaya detailbiaya=new DetailBiaya();
		detailbiaya.setSearch(search);
		 if(sort!=null)detailbiaya.setSort(sort+" "+sortOrder);
		detailbiaya.setPage(page);
		detailbiaya.setRowcount(rowcount);
		return detailbiayaMapper.selectPagingList(detailbiaya);
	}

	/** Save Model **/
	@Transactional
	public DetailBiaya save(DetailBiaya detailbiaya) {
		if (detailbiaya.get?==null) {
			detailbiayaMapper.insert(detailbiaya);
		} else {
			detailbiayaMapper.update(detailbiaya);
		} 
		return detailbiaya;
	}
	/** Others Method **/

	}
