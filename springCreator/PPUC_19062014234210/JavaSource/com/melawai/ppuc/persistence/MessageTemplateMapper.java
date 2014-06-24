package com.melawai.ppuc.persistence;

import java.util.Date;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.apache.ibatis.annotations.Param;
import com.melawai.ppuc.model.MessageTemplate;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:36 ICT 2014
 * @Description: Mapper Interface for table message_template
 * @Revision	:
 */

public interface MessageTemplateMapper {

	public void insert(MessageTemplate messagetemplate) throws DataAccessException;

	public void update(MessageTemplate messagetemplate) throws DataAccessException;

	public void remove(Long id_template) throws DataAccessException;

	public MessageTemplate get(Long id_template) throws DataAccessException;

	public List<MessageTemplate> getAll() throws DataAccessException;

	public List<MessageTemplate> selectPagingList(MessageTemplate messagetemplate) throws DataAccessException;

	public Integer selectPagingCount(MessageTemplate messagetemplate) throws DataAccessException;

	// QUERY CUSTOM yang lain dibawah sini


}
