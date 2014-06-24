package com.melawai.ppuc.services;

import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.LogEmail;
import com.melawai.ppuc.persistence.LogEmailMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Tue Jun 17 18:34:40 ICT 2014
 * @Description: Services for table log_email
 * @Revision	:
 */

@Service("logemailManager")
public class LogEmailManager {

	private static Logger logger = Logger.getLogger(LogEmailManager.class);

	@Autowired
	private LogEmailMapper logemailMapper;

	/** Ambil DATA berdasarkan id **/
	public LogEmail get(Long id) {
		return logemailMapper.get(id);
	}

	/** Apakah data dengan id ini ada? **/
	public boolean exists(Long id) {	
		return get(id)!=null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(Long id) {
		logemailMapper.remove(id);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		LogEmail logemail=new LogEmail();
		logemail.setSearch(search);
		return logemailMapper.selectPagingCount(logemail);
	}

	/** Ambil data paging **/
	public List<LogEmail> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		LogEmail logemail=new LogEmail();
		logemail.setSearch(search);
		 if(sort!=null)logemail.setSort(sort+" "+sortOrder);
		logemail.setPage(page);
		logemail.setRowcount(rowcount);
		return logemailMapper.selectPagingList(logemail);
	}

	/** Save Model **/
	@Transactional
	public LogEmail save(LogEmail logemail) {
		if (logemail.getId()==null) {
			logemailMapper.insert(logemail);
		} else {
			logemailMapper.update(logemail);
		} 
		return logemail;
	}
	/** Others Method **/

	}
