package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.Counter;
import com.melawai.ppuc.persistence.CounterMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:38 ICT 2014
 * @Description: Services for table sys_counter
 * @Revision	:
 */

@Service("counterManager")
public class CounterManager {

	private static Logger logger = Logger.getLogger(CounterManager.class);

	@Autowired
	private CounterMapper counterMapper;

	/** Ambil DATA berdasarkan id **/
	public Counter get(Long id) {
		return counterMapper.get(id);
	}

	/** Apakah data dengan id ini ada? **/
	public boolean exists(Long id) {	
		return get(id)!=null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(Long id) {
		counterMapper.remove(id);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		Counter counter=new Counter();
		counter.setSearch(search);
		return counterMapper.selectPagingCount(counter);
	}

	/** Ambil data paging **/
	public List<Counter> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		Counter counter=new Counter();
		counter.setSearch(search);
		 if(sort!=null)counter.setSort(sort+" "+sortOrder);
		counter.setPage(page);
		counter.setRowcount(rowcount);
		return counterMapper.selectPagingList(counter);
	}

	/** Save Model **/
	@Transactional
	public Counter save(Counter counter) {
		if (counter.get?==null) {
			counterMapper.insert(counter);
		} else {
			counterMapper.update(counter);
		} 
		return counter;
	}
	/** Others Method **/

	}
