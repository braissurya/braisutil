package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Subdivisi;
import com.melawai.ppuc.persistence.SubdivisiMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:38 ICT 2014
 * @Description: Services for table subdivisi
 * @Revision	:
 */

@Service("subdivisiManager")
public class SubdivisiManager {

	private static Logger logger = Logger.getLogger(SubdivisiManager.class);

	@Autowired
	private SubdivisiMapper subdivisiMapper;

	/** Ambil DATA berdasarkan subdiv_kd **/
	public Subdivisi get(String subdiv_kd) {
		return subdivisiMapper.get(subdiv_kd);
	}

	/** Apakah data dengan subdiv_kd ini ada? **/
	public boolean exists(String subdiv_kd) {	
		return get(subdiv_kd)!=null;
	}

	/** Delete data berdasarkan subdiv_kd **/
	@Transactional
	public void remove(String subdiv_kd) {
		subdivisiMapper.remove(subdiv_kd);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		Subdivisi subdivisi=new Subdivisi();
		subdivisi.setSearch(search);
		return subdivisiMapper.selectPagingCount(subdivisi);
	}

	/** Ambil data paging **/
	public List<Subdivisi> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		Subdivisi subdivisi=new Subdivisi();
		subdivisi.setSearch(search);
		 if(sort!=null)subdivisi.setSort(sort+" "+sortOrder);
		subdivisi.setPage(page);
		subdivisi.setRowcount(rowcount);
		return subdivisiMapper.selectPagingList(subdivisi);
	}

	/** Save Model **/
	@Transactional
	public Subdivisi save(Subdivisi subdivisi) {
		if (subdivisi.get?==null) {
			subdivisiMapper.insert(subdivisi);
		} else {
			subdivisiMapper.update(subdivisi);
		} 
		return subdivisi;
	}
	/** Others Method **/

	}
