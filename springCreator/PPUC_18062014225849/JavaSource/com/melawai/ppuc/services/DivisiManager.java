package com.melawai.ppuc.services;

import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Divisi;
import com.melawai.ppuc.persistence.DivisiMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Wed Jun 18 22:59:06 ICT 2014
 * @Description: Services for table divisi
 * @Revision	:
 */

@Service("divisiManager")
public class DivisiManager {

	private static Logger logger = Logger.getLogger(DivisiManager.class);

	@Autowired
	private DivisiMapper divisiMapper;

	/** Ambil DATA berdasarkan divisi_kd **/
	public Divisi get(String divisi_kd) {
		return divisiMapper.get(divisi_kd);
	}

	/** Apakah data dengan divisi_kd ini ada? **/
	public boolean exists(String divisi_kd) {	
		return get(divisi_kd)!=null;
	}

	/** Delete data berdasarkan divisi_kd **/
	@Transactional
	public void remove(String divisi_kd) {
		divisiMapper.remove(divisi_kd);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		Divisi divisi=new Divisi();
		divisi.setSearch(search);
		return divisiMapper.selectPagingCount(divisi);
	}

	/** Ambil data paging **/
	public List<Divisi> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		Divisi divisi=new Divisi();
		divisi.setSearch(search);
		 if(sort!=null)divisi.setSort(sort+" "+sortOrder);
		divisi.setPage(page);
		divisi.setRowcount(rowcount);
		return divisiMapper.selectPagingList(divisi);
	}

	/** Save Model **/
	@Transactional
	public Divisi save(Divisi divisi) {
		if (divisi.get?==null) {
			divisiMapper.insert(divisi);
		} else {
			divisiMapper.update(divisi);
		} 
		return divisi;
	}
	/** Others Method **/

	}
