package com.melawai.ppuc.services;

import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.MFungsi;
import com.melawai.ppuc.persistence.MFungsiMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Tue Jun 17 18:34:45 ICT 2014
 * @Description: Services for table m_fungsi
 * @Revision	:
 */

@Service("mfungsiManager")
public class MFungsiManager {

	private static Logger logger = Logger.getLogger(MFungsiManager.class);

	@Autowired
	private MFungsiMapper mfungsiMapper;

	/** Ambil DATA berdasarkan kd_fungsi **/
	public MFungsi get(String kd_fungsi) {
		return mfungsiMapper.get(kd_fungsi);
	}

	/** Apakah data dengan kd_fungsi ini ada? **/
	public boolean exists(String kd_fungsi) {	
		return get(kd_fungsi)!=null;
	}

	/** Delete data berdasarkan kd_fungsi **/
	@Transactional
	public void remove(String kd_fungsi) {
		mfungsiMapper.remove(kd_fungsi);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		MFungsi mfungsi=new MFungsi();
		mfungsi.setSearch(search);
		return mfungsiMapper.selectPagingCount(mfungsi);
	}

	/** Ambil data paging **/
	public List<MFungsi> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		MFungsi mfungsi=new MFungsi();
		mfungsi.setSearch(search);
		 if(sort!=null)mfungsi.setSort(sort+" "+sortOrder);
		mfungsi.setPage(page);
		mfungsi.setRowcount(rowcount);
		return mfungsiMapper.selectPagingList(mfungsi);
	}

	/** Save Model **/
	@Transactional
	public MFungsi save(MFungsi mfungsi) {
		if (mfungsi.get?==null) {
			mfungsiMapper.insert(mfungsi);
		} else {
			mfungsiMapper.update(mfungsi);
		} 
		return mfungsi;
	}
	/** Others Method **/

	}
