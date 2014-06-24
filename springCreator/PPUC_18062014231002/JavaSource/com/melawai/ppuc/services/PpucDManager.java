package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.PpucD;
import com.melawai.ppuc.persistence.PpucDMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Wed Jun 18 23:10:17 ICT 2014
 * @Description: Services for table ppuc_d
 * @Revision	:
 */

@Service("ppucdManager")
public class PpucDManager {

	private static Logger logger = Logger.getLogger(PpucDManager.class);

	@Autowired
	private PpucDMapper ppucdMapper;

	/** Ambil DATA berdasarkan no_ppuc, tgl_ppuc, divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_biaya **/
	public PpucD get(String no_ppuc, Date tgl_ppuc, String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String kd_biaya) {
		return ppucdMapper.get(no_ppuc, tgl_ppuc, divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_biaya);
	}

	/** Apakah data dengan no_ppuc, tgl_ppuc, divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_biaya ini ada? **/
	public boolean exists(String no_ppuc, Date tgl_ppuc, String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String kd_biaya) {	
		return get(no_ppuc, tgl_ppuc, divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_biaya)!=null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(String no_ppuc, Date tgl_ppuc, String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String kd_biaya) {
		ppucdMapper.remove(no_ppuc, tgl_ppuc, divisi_kd, subdiv_kd, dept_kd, lok_kd, kd_biaya);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		PpucD ppucd=new PpucD();
		ppucd.setSearch(search);
		return ppucdMapper.selectPagingCount(ppucd);
	}

	/** Ambil data paging **/
	public List<PpucD> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		PpucD ppucd=new PpucD();
		ppucd.setSearch(search);
		 if(sort!=null)ppucd.setSort(sort+" "+sortOrder);
		ppucd.setPage(page);
		ppucd.setRowcount(rowcount);
		return ppucdMapper.selectPagingList(ppucd);
	}

	/** Save Model **/
	@Transactional
	public PpucD save(PpucD ppucd) {
		if (ppucd.get?==null) {
			ppucdMapper.insert(ppucd);
		} else {
			ppucdMapper.update(ppucd);
		} 
		return ppucd;
	}
	/** Others Method **/

	}
