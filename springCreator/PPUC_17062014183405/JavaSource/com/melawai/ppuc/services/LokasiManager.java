package com.melawai.ppuc.services;

import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Lokasi;
import com.melawai.ppuc.persistence.LokasiMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Tue Jun 17 18:34:41 ICT 2014
 * @Description: Services for table lokasi
 * @Revision	:
 */

@Service("lokasiManager")
public class LokasiManager {

	private static Logger logger = Logger.getLogger(LokasiManager.class);

	@Autowired
	private LokasiMapper lokasiMapper;

	/** Ambil DATA berdasarkan lok_kd **/
	public Lokasi get(String lok_kd) {
		return lokasiMapper.get(lok_kd);
	}

	/** Apakah data dengan lok_kd ini ada? **/
	public boolean exists(String lok_kd) {	
		return get(lok_kd)!=null;
	}

	/** Delete data berdasarkan lok_kd **/
	@Transactional
	public void remove(String lok_kd) {
		lokasiMapper.remove(lok_kd);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		Lokasi lokasi=new Lokasi();
		lokasi.setSearch(search);
		return lokasiMapper.selectPagingCount(lokasi);
	}

	/** Ambil data paging **/
	public List<Lokasi> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		Lokasi lokasi=new Lokasi();
		lokasi.setSearch(search);
		 if(sort!=null)lokasi.setSort(sort+" "+sortOrder);
		lokasi.setPage(page);
		lokasi.setRowcount(rowcount);
		return lokasiMapper.selectPagingList(lokasi);
	}

	/** Save Model **/
	@Transactional
	public Lokasi save(Lokasi lokasi) {
		if (lokasi.get?==null) {
			lokasiMapper.insert(lokasi);
		} else {
			lokasiMapper.update(lokasi);
		} 
		return lokasi;
	}
	/** Others Method **/

	}
