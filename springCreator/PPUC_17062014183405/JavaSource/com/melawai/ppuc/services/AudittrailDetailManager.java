package com.melawai.ppuc.services;

import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.AudittrailDetail;
import com.melawai.ppuc.persistence.AudittrailDetailMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Tue Jun 17 18:34:34 ICT 2014
 * @Description: Services for table audittrail_detail
 * @Revision	:
 */

@Service("audittraildetailManager")
public class AudittrailDetailManager {

	private static Logger logger = Logger.getLogger(AudittrailDetailManager.class);

	@Autowired
	private AudittrailDetailMapper audittraildetailMapper;

	/** Ambil DATA berdasarkan id **/
	public AudittrailDetail get(Long id) {
		return audittraildetailMapper.get(id);
	}

	/** Apakah data dengan id ini ada? **/
	public boolean exists(Long id) {	
		return get(id)!=null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(Long id) {
		audittraildetailMapper.remove(id);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		AudittrailDetail audittraildetail=new AudittrailDetail();
		audittraildetail.setSearch(search);
		return audittraildetailMapper.selectPagingCount(audittraildetail);
	}

	/** Ambil data paging **/
	public List<AudittrailDetail> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		AudittrailDetail audittraildetail=new AudittrailDetail();
		audittraildetail.setSearch(search);
		 if(sort!=null)audittraildetail.setSort(sort+" "+sortOrder);
		audittraildetail.setPage(page);
		audittraildetail.setRowcount(rowcount);
		return audittraildetailMapper.selectPagingList(audittraildetail);
	}

	/** Save Model **/
	@Transactional
	public AudittrailDetail save(AudittrailDetail audittraildetail) {
		if (audittraildetail.getId()==null) {
			audittraildetailMapper.insert(audittraildetail);
		} else {
			audittraildetailMapper.update(audittraildetail);
		} 
		return audittraildetail;
	}
	/** Others Method **/

	}
