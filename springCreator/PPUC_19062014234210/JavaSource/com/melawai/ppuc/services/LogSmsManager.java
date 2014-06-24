package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.LogSms;
import com.melawai.ppuc.persistence.LogSmsMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:38 ICT 2014
 * @Description: Services for table sys_log_sms
 * @Revision	:
 */

@Service("logsmsManager")
public class LogSmsManager {

	private static Logger logger = Logger.getLogger(LogSmsManager.class);

	@Autowired
	private LogSmsMapper logsmsMapper;

	/** Ambil DATA berdasarkan id_log_sms **/
	public LogSms get(Long id_log_sms) {
		return logsmsMapper.get(id_log_sms);
	}

	/** Apakah data dengan id_log_sms ini ada? **/
	public boolean exists(Long id_log_sms) {	
		return get(id_log_sms)!=null;
	}

	/** Delete data berdasarkan id_log_sms **/
	@Transactional
	public void remove(Long id_log_sms) {
		logsmsMapper.remove(id_log_sms);
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
		if (logsms.getId_log_sms()==null) {
			logsmsMapper.insert(logsms);
		} else {
			logsmsMapper.update(logsms);
		} 
		return logsms;
	}
	/** Others Method **/

	}
