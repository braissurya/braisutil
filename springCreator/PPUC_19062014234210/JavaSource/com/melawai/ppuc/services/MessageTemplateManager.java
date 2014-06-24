package com.melawai.ppuc.services;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melawai.ppuc.model.MessageTemplate;
import com.melawai.ppuc.persistence.MessageTemplateMapper;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:36 ICT 2014
 * @Description: Services for table message_template
 * @Revision	:
 */

@Service("messagetemplateManager")
public class MessageTemplateManager {

	private static Logger logger = Logger.getLogger(MessageTemplateManager.class);

	@Autowired
	private MessageTemplateMapper messagetemplateMapper;

	/** Ambil DATA berdasarkan id_template **/
	public MessageTemplate get(Long id_template) {
		return messagetemplateMapper.get(id_template);
	}

	/** Apakah data dengan id_template ini ada? **/
	public boolean exists(Long id_template) {	
		return get(id_template)!=null;
	}

	/** Delete data berdasarkan id_template **/
	@Transactional
	public void remove(Long id_template) {
		messagetemplateMapper.remove(id_template);
	}

	/** Ambil jumlah seluruh data **/
	public int selectPagingCount(String search) {
		MessageTemplate messagetemplate=new MessageTemplate();
		messagetemplate.setSearch(search);
		return messagetemplateMapper.selectPagingCount(messagetemplate);
	}

	/** Ambil data paging **/
	public List<MessageTemplate> selectPagingList(String search, String sort,String sortOrder, int page, int rowcount) {
		MessageTemplate messagetemplate=new MessageTemplate();
		messagetemplate.setSearch(search);
		 if(sort!=null)messagetemplate.setSort(sort+" "+sortOrder);
		messagetemplate.setPage(page);
		messagetemplate.setRowcount(rowcount);
		return messagetemplateMapper.selectPagingList(messagetemplate);
	}

	/** Save Model **/
	@Transactional
	public MessageTemplate save(MessageTemplate messagetemplate) {
		if (messagetemplate.getId_template()==null) {
			messagetemplateMapper.insert(messagetemplate);
		} else {
			messagetemplateMapper.update(messagetemplate);
		} 
		return messagetemplate;
	}
	/** Others Method **/

	}
