package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.PpucH;
import com.melawai.ppuc.persistence.PpucHMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:37 ICT 2014
 * @Description: Services for table ppuc_h
 * @Revision	:
 */

@Service("ppuchManager")
public class PpucHManager {

	private static Logger logger = Logger.getLogger(PpucHManager.class);

	@Autowired
	private PpucHMapper ppuchMapper;

	/** Ambil DATA berdasarkan divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc **/
	public PpucH get(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String no_ppuc, Date tgl_ppuc) {
		return ppuchMapper.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc);
	}

	/** Apakah data dengan divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc ini ada? **/
	public boolean exists(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String no_ppuc, Date tgl_ppuc) {	
		return get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc)!=null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String no_ppuc, Date tgl_ppuc) {
		ppuchMapper.remove(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		PpucH ppuch=new PpucH();
		ppuch.setSearch(search);
		return ppuchMapper.selectPagingCount(ppuch);
	}

	/** Ambil data paging **/
	public List<PpucH> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		PpucH ppuch=new PpucH();
		ppuch.setSearch(search);
		 if(sort!=null)ppuch.setSort(sort+" "+sortOrder);
		ppuch.setPage(page);
		ppuch.setRowcount(rowcount);
		return ppuchMapper.selectPagingList(ppuch);
	}

	/** Save Model **/
	@Transactional
	public PpucH save(PpucH ppuch) {
		if (ppuch.get?==null) {
			ppuchMapper.insert(ppuch);
		} else {
			ppuchMapper.update(ppuch);
		} 
		return ppuch;
	}
	/** Others Method **/

	}
