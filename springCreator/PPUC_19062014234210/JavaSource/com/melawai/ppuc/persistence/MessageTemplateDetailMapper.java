package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.MessageTemplateDetail;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:36 ICT 2014
 * @Description: Mapper Interface for table message_template_detail
 * @Revision	:
 */

public interface MessageTemplateDetailMapper {

	public void insert(MessageTemplateDetail messagetemplatedetail) throws DataAccessException;

	public void update(MessageTemplateDetail messagetemplatedetail) throws DataAccessException;

	public void remove(Long id_template_detail) throws DataAccessException;

	public MessageTemplateDetail get(Long id_template_detail) throws DataAccessException;

	public List<MessageTemplateDetail> getAll() throws DataAccessException;

	public List<MessageTemplateDetail> selectPagingList(MessageTemplateDetail messagetemplatedetail) throws DataAccessException;

	public Integer selectPagingCount(MessageTemplateDetail messagetemplatedetail) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
