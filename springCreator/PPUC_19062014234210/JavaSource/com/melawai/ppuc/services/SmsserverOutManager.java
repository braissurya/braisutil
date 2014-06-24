package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.SmsserverOut;
import com.melawai.ppuc.persistence.SmsserverOutMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:38 ICT 2014
 * @Description: Services for table smsserver_out
 * @Revision	:
 */

@Service("smsserveroutManager")
public class SmsserverOutManager {

	private static Logger logger = Logger.getLogger(SmsserverOutManager.class);

	@Autowired
	private SmsserverOutMapper smsserveroutMapper;

	/** Ambil DATA berdasarkan id **/
	public SmsserverOut get(Long id) {
		return smsserveroutMapper.get(id);
	}

	/** Apakah data dengan id ini ada? **/
	public boolean exists(Long id) {	
		return get(id)!=null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(Long id) {
		smsserveroutMapper.remove(id);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		SmsserverOut smsserverout=new SmsserverOut();
		smsserverout.setSearch(search);
		return smsserveroutMapper.selectPagingCount(smsserverout);
	}

	/** Ambil data paging **/
	public List<SmsserverOut> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		SmsserverOut smsserverout=new SmsserverOut();
		smsserverout.setSearch(search);
		 if(sort!=null)smsserverout.setSort(sort+" "+sortOrder);
		smsserverout.setPage(page);
		smsserverout.setRowcount(rowcount);
		return smsserveroutMapper.selectPagingList(smsserverout);
	}

	/** Save Model **/
	@Transactional
	public SmsserverOut save(SmsserverOut smsserverout) {
		if (smsserverout.getId()==null) {
			smsserveroutMapper.insert(smsserverout);
		} else {
			smsserveroutMapper.update(smsserverout);
		} 
		return smsserverout;
	}
	/** Others Method **/

	}
