package com.melawai.ppuc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.melawai.ppuc.model.BaseObject;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:36 ICT 2014
 * @Description: Model for table message_template_detail
 * @Revision	:
 */

public class MessageTemplateDetail extends BaseObject implements Serializable  {

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotNull
	public Long id_template_detail;

	@NotNull
	public Long id_template;

	@Size(max=200)
	public String keterangan;

	@Size(max=65535)
	public String template_sms;

	@Size(max=65535)
	public String template_web;

	@Size(max=65535)
	public String template_subject;

	@Size(max=65535)
	public String template_email;

	public Long f_aktif;

	@Size(max=50)
	public String user_create;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_create;

	@Size(max=50)
	public String user_update;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_update;

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public MessageTemplateDetail(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public Long getId_template_detail(){ return id_template_detail; }
	public void setId_template_detail(Long id_template_detail){ this.id_template_detail = id_template_detail; }

	public Long getId_template(){ return id_template; }
	public void setId_template(Long id_template){ this.id_template = id_template; }

	public String getKeterangan(){ return keterangan; }
	public void setKeterangan(String keterangan){ this.keterangan = keterangan; }

	public String getTemplate_sms(){ return template_sms; }
	public void setTemplate_sms(String template_sms){ this.template_sms = template_sms; }

	public String getTemplate_web(){ return template_web; }
	public void setTemplate_web(String template_web){ this.template_web = template_web; }

	public String getTemplate_subject(){ return template_subject; }
	public void setTemplate_subject(String template_subject){ this.template_subject = template_subject; }

	public String getTemplate_email(){ return template_email; }
	public void setTemplate_email(String template_email){ this.template_email = template_email; }

	public Long getF_aktif(){ return f_aktif; }
	public void setF_aktif(Long f_aktif){ this.f_aktif = f_aktif; }

	public String getUser_create(){ return user_create; }
	public void setUser_create(String user_create){ this.user_create = user_create; }

	public Date getTgl_create(){ return tgl_create; }
	public void setTgl_create(Date tgl_create){ this.tgl_create = tgl_create; }

	public String getUser_update(){ return user_update; }
	public void setUser_update(String user_update){ this.user_update = user_update; }

	public Date getTgl_update(){ return tgl_update; }
	public void setTgl_update(Date tgl_update){ this.tgl_update = tgl_update; }


	public String getItemId() {return ""+id_template_detail;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
