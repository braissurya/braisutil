package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Audittrail;
import com.melawai.ppuc.persistence.AudittrailMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:38 ICT 2014
 * @Description: Services for table sys_audittrail
 * @Revision	:
 */

@Service("audittrailManager")
public class AudittrailManager {

	private static Logger logger = Logger.getLogger(AudittrailManager.class);

	@Autowired
	private AudittrailMapper audittrailMapper;

	/** Ambil DATA berdasarkan id_audittrail **/
	public Audittrail get(Long id_audittrail) {
		return audittrailMapper.get(id_audittrail);
	}

	/** Apakah data dengan id_audittrail ini ada? **/
	public boolean exists(Long id_audittrail) {	
		return get(id_audittrail)!=null;
	}

	/** Delete data berdasarkan id_audittrail **/
	@Transactional
	public void remove(Long id_audittrail) {
		audittrailMapper.remove(id_audittrail);
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
		if (audittrail.getId_audittrail()==null) {
			audittrailMapper.insert(audittrail);
		} else {
			audittrailMapper.update(audittrail);
		} 
		return audittrail;
	}
	/** Others Method **/

	}
