package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.MessageTemplateDetail;
import com.melawai.ppuc.persistence.MessageTemplateDetailMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:36 ICT 2014
 * @Description: Services for table message_template_detail
 * @Revision	:
 */

@Service("messagetemplatedetailManager")
public class MessageTemplateDetailManager {

	private static Logger logger = Logger.getLogger(MessageTemplateDetailManager.class);

	@Autowired
	private MessageTemplateDetailMapper messagetemplatedetailMapper;

	/** Ambil DATA berdasarkan id_template_detail **/
	public MessageTemplateDetail get(Long id_template_detail) {
		return messagetemplatedetailMapper.get(id_template_detail);
	}

	/** Apakah data dengan id_template_detail ini ada? **/
	public boolean exists(Long id_template_detail) {	
		return get(id_template_detail)!=null;
	}

	/** Delete data berdasarkan id_template_detail **/
	@Transactional
	public void remove(Long id_template_detail) {
		messagetemplatedetailMapper.remove(id_template_detail);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		MessageTemplateDetail messagetemplatedetail=new MessageTemplateDetail();
		messagetemplatedetail.setSearch(search);
		return messagetemplatedetailMapper.selectPagingCount(messagetemplatedetail);
	}

	/** Ambil data paging **/
	public List<MessageTemplateDetail> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		MessageTemplateDetail messagetemplatedetail=new MessageTemplateDetail();
		messagetemplatedetail.setSearch(search);
		 if(sort!=null)messagetemplatedetail.setSort(sort+" "+sortOrder);
		messagetemplatedetail.setPage(page);
		messagetemplatedetail.setRowcount(rowcount);
		return messagetemplatedetailMapper.selectPagingList(messagetemplatedetail);
	}

	/** Save Model **/
	@Transactional
	public MessageTemplateDetail save(MessageTemplateDetail messagetemplatedetail) {
		if (messagetemplatedetail.getId_template_detail()==null) {
			messagetemplatedetailMapper.insert(messagetemplatedetail);
		} else {
			messagetemplatedetailMapper.update(messagetemplatedetail);
		} 
		return messagetemplatedetail;
	}
	/** Others Method **/

	}
