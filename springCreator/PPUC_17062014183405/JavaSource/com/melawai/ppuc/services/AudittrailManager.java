package com.melawai.ppuc.services;

import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Audittrail;
import com.melawai.ppuc.persistence.AudittrailMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Tue Jun 17 18:34:34 ICT 2014
 * @Description: Services for table audittrail
 * @Revision	:
 */

@Service("audittrailManager")
public class AudittrailManager {

	private static Logger logger = Logger.getLogger(AudittrailManager.class);

	@Autowired
	private AudittrailMapper audittrailMapper;

	/** Ambil DATA berdasarkan id **/
	public Audittrail get(Long id) {
		return audittrailMapper.get(id);
	}

	/** Apakah data dengan id ini ada? **/
	public boolean exists(Long id) {	
		return get(id)!=null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(Long id) {
		audittrailMapper.remove(id);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		Audittrail audittrail=new Audittrail();
		audittrail.setSearch(search);
		return audittrailMapper.selectPagingCount(audittrail);
	}

	/** Ambil data paging **/
	public List<Audittrail> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		Audittrail audittrail=new Audittrail();
		audittrail.setSearch(search);
		 if(sort!=null)audittrail.setSort(sort+" "+sortOrder);
		audittrail.setPage(page);
		audittrail.setRowcount(rowcount);
		return audittrailMapper.selectPagingList(audittrail);
	}

	/** Save Model **/
	@Transactional
	public Audittrail save(Audittrail audittrail) {
		if (audittrail.getId()==null) {
			audittrailMapper.insert(audittrail);
		} else {
			audittrailMapper.update(audittrail);
		} 
		return audittrail;
	}
	/** Others Method **/

	}
