package com.melawai.ppuc.services;

import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Departmen;
import com.melawai.ppuc.persistence.DepartmenMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Mon Jun 16 00:24:05 ICT 2014
 * @Description: Services for table departmen
 * @Revision	:
 */

@Service("departmenManager")
public class DepartmenManager {

	private static Logger logger = Logger.getLogger(DepartmenManager.class);

	@Autowired
	private DepartmenMapper departmenMapper;

	/** Ambil DATA berdasarkan dept_kd **/
	public Departmen get(String dept_kd) {
		return departmenMapper.get(dept_kd);
	}

	/** Apakah data dengan dept_kd ini ada? **/
	public boolean exists(String dept_kd) {	
		return get(dept_kd)!=null;
	}

	/** Delete data berdasarkan dept_kd **/
	@Transactional
	public void remove(String dept_kd) {
		departmenMapper.remove(dept_kd);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		Departmen departmen=new Departmen();
		departmen.setSearch(search);
		return departmenMapper.selectPagingCount(departmen);
	}

	/** Ambil data paging **/
	public List<Departmen> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		Departmen departmen=new Departmen();
		departmen.setSearch(search);
		 if(sort!=null)departmen.setSort(sort+" "+sortOrder);
		departmen.setPage(page);
		departmen.setRowcount(rowcount);
		return departmenMapper.selectPagingList(departmen);
	}

	/** Save Model **/
	@Transactional
	public Departmen save(Departmen departmen) {
		if (departmen.get?==null) {
			departmenMapper.insert(departmen);
		} else {
			departmenMapper.update(departmen);
		} 
		return departmen;
	}
	/** Others Method **/

	}
