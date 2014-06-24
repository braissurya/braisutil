package com.melawai.ppuc.services;

import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.LogSms;
import com.melawai.ppuc.persistence.LogSmsMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Mon Jun 16 00:24:08 ICT 2014
 * @Description: Services for table log_sms
 * @Revision	:
 */

@Service("logsmsManager")
public class LogSmsManager {

	private static Logger logger = Logger.getLogger(LogSmsManager.class);

	@Autowired
	private LogSmsMapper logsmsMapper;

	/** Ambil DATA berdasarkan id **/
	public LogSms get(Long id) {
		return logsmsMapper.get(id);
	}

	/** Apakah data dengan id ini ada? **/
	public boolean exists(Long id) {	
		return get(id)!=null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(Long id) {
		logsmsMapper.remove(id);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		LogSms logsms=new LogSms();
		logsms.setSearch(search);
		return logsmsMapper.selectPagingCount(logsms);
	}

	/** Ambil data paging **/
	public List<LogSms> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		LogSms logsms=new LogSms();
		logsms.setSearch(search);
		 if(sort!=null)logsms.setSort(sort+" "+sortOrder);
		logsms.setPage(page);
		logsms.setRowcount(rowcount);
		return logsmsMapper.selectPagingList(logsms);
	}

	/** Save Model **/
	@Transactional
	public LogSms save(LogSms logsms) {
		if (logsms.getId()==null) {
			logsmsMapper.insert(logsms);
		} else {
			logsmsMapper.update(logsms);
		} 
		return logsms;
	}
	/** Others Method **/

	}
