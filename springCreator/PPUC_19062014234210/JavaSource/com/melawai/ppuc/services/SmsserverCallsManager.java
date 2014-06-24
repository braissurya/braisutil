package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.SmsserverCalls;
import com.melawai.ppuc.persistence.SmsserverCallsMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:37 ICT 2014
 * @Description: Services for table smsserver_calls
 * @Revision	:
 */

@Service("smsservercallsManager")
public class SmsserverCallsManager {

	private static Logger logger = Logger.getLogger(SmsserverCallsManager.class);

	@Autowired
	private SmsserverCallsMapper smsservercallsMapper;

	/** Ambil DATA berdasarkan id **/
	public SmsserverCalls get(Long id) {
		return smsservercallsMapper.get(id);
	}

	/** Apakah data dengan id ini ada? **/
	public boolean exists(Long id) {	
		return get(id)!=null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(Long id) {
		smsservercallsMapper.remove(id);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		SmsserverCalls smsservercalls=new SmsserverCalls();
		smsservercalls.setSearch(search);
		return smsservercallsMapper.selectPagingCount(smsservercalls);
	}

	/** Ambil data paging **/
	public List<SmsserverCalls> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		SmsserverCalls smsservercalls=new SmsserverCalls();
		smsservercalls.setSearch(search);
		 if(sort!=null)smsservercalls.setSort(sort+" "+sortOrder);
		smsservercalls.setPage(page);
		smsservercalls.setRowcount(rowcount);
		return smsservercallsMapper.selectPagingList(smsservercalls);
	}

	/** Save Model **/
	@Transactional
	public SmsserverCalls save(SmsserverCalls smsservercalls) {
		if (smsservercalls.getId()==null) {
			smsservercallsMapper.insert(smsservercalls);
		} else {
			smsservercallsMapper.update(smsservercalls);
		} 
		return smsservercalls;
	}
	/** Others Method **/

	}
