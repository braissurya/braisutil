package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.ImgPpucH;
import com.melawai.ppuc.persistence.ImgPpucHMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:34 ICT 2014
 * @Description: Services for table img_ppuc_h
 * @Revision	:
 */

@Service("imgppuchManager")
public class ImgPpucHManager {

	private static Logger logger = Logger.getLogger(ImgPpucHManager.class);

	@Autowired
	private ImgPpucHMapper imgppuchMapper;

	/** Ambil DATA berdasarkan divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, no_realisasi **/
	public ImgPpucH get(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String no_ppuc, Date tgl_ppuc, String no_realisasi) {
		return imgppuchMapper.get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, no_realisasi);
	}

	/** Apakah data dengan divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, no_realisasi ini ada? **/
	public boolean exists(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String no_ppuc, Date tgl_ppuc, String no_realisasi) {	
		return get(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, no_realisasi)!=null;
	}

	/** Delete data berdasarkan id **/
	@Transactional
	public void remove(String divisi_kd, String subdiv_kd, String dept_kd, String lok_kd, String no_ppuc, Date tgl_ppuc, String no_realisasi) {
		imgppuchMapper.remove(divisi_kd, subdiv_kd, dept_kd, lok_kd, no_ppuc, tgl_ppuc, no_realisasi);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		ImgPpucH imgppuch=new ImgPpucH();
		imgppuch.setSearch(search);
		return imgppuchMapper.selectPagingCount(imgppuch);
	}

	/** Ambil data paging **/
	public List<ImgPpucH> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		ImgPpucH imgppuch=new ImgPpucH();
		imgppuch.setSearch(search);
		 if(sort!=null)imgppuch.setSort(sort+" "+sortOrder);
		imgppuch.setPage(page);
		imgppuch.setRowcount(rowcount);
		return imgppuchMapper.selectPagingList(imgppuch);
	}

	/** Save Model **/
	@Transactional
	public ImgPpucH save(ImgPpucH imgppuch) {
		if (imgppuch.get?==null) {
			imgppuchMapper.insert(imgppuch);
		} else {
			imgppuchMapper.update(imgppuch);
		} 
		return imgppuch;
	}
	/** Others Method **/

	}
