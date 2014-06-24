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
 * @since		: Wed Jun 18 23:10:17 ICT 2014
 * @Description: Services for table ppuc_h
 * @Revision	:
 */

@Service("ppuchManager")
public class PpucHManager {

	private static Logger logger = Logger.getLogger(PpucHManager.class);

	@Autowired
	private PpucHMapper ppuchMapper;

	/** Ambil DATA berdasarkan no_ppuc, tgl_ppuc, divisi_kd, subdiv_kd, dept_kd, lok_kd **/
	public PpucH get(String no_ppuc, Date tgl_ppuc, String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd) {
		return ppuchMapper.get(no_ppuc, tgl_ppuc, divisi_kd, subdiv_kd, dept_kd, lok_kd);
	}

	/** Apakah data dengan no_ppuc, tgl_ppuc, divisi_kd, subdiv_kd, dept_kd, lok_kd ini ada? **/
	public boolean exists(String no_ppuc, Date tgl_ppuc, String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd) {	
		return get(no_ppuc, tgl_ppuc, divisi_kd, subdiv_kd, dept_kd, lok_kd)!=null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(String no_ppuc, Date tgl_ppuc, String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd) {
		ppuchMapper.remove(no_ppuc, tgl_ppuc, divisi_kd, subdiv_kd, dept_kd, lok_kd);
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
