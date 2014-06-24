package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.SmsserverIn;
import com.melawai.ppuc.persistence.SmsserverInMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:38 ICT 2014
 * @Description: Services for table smsserver_in
 * @Revision	:
 */

@Service("smsserverinManager")
public class SmsserverInManager {

	private static Logger logger = Logger.getLogger(SmsserverInManager.class);

	@Autowired
	private SmsserverInMapper smsserverinMapper;

	/** Ambil DATA berdasarkan id **/
	public SmsserverIn get(Long id) {
		return smsserverinMapper.get(id);
	}

	/** Apakah data dengan id ini ada? **/
	public boolean exists(Long id) {	
		return get(id)!=null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(Long id) {
		smsserverinMapper.remove(id);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		SmsserverIn smsserverin=new SmsserverIn();
		smsserverin.setSearch(search);
		return smsserverinMapper.selectPagingCount(smsserverin);
	}

	/** Ambil data paging **/
	public List<SmsserverIn> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		SmsserverIn smsserverin=new SmsserverIn();
		smsserverin.setSearch(search);
		 if(sort!=null)smsserverin.setSort(sort+" "+sortOrder);
		smsserverin.setPage(page);
		smsserverin.setRowcount(rowcount);
		return smsserverinMapper.selectPagingList(smsserverin);
	}

	/** Save Model **/
	@Transactional
	public SmsserverIn save(SmsserverIn smsserverin) {
		if (smsserverin.getId()==null) {
			smsserverinMapper.insert(smsserverin);
		} else {
			smsserverinMapper.update(smsserverin);
		} 
		return smsserverin;
	}
	/** Others Method **/

	}
