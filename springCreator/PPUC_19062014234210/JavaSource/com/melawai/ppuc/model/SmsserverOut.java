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
 * @since		: Thu Jun 19 23:42:38 ICT 2014
 * @Description: Model for table smsserver_out
 * @Revision	:
 */

public class SmsserverOut extends BaseObject implements Serializable  {

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotNull
	public Long id;

	@NotNull
	@Size(max=1)
	public String type;

	@NotNull
	@Size(max=16)
	public String recipient;

	@NotNull
	@Size(max=1000)
	public String text;

	@Size(max=100)
	public String wap_url;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date wap_expiry_date;

	@Size(max=1)
	public String wap_signal;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date create_date;

	@NotNull
	@Size(max=16)
	public String originator;

	@NotNull
	@Size(max=1)
	public String encoding;

	@NotNull
	public Long status_report;

	@NotNull
	public Long flash_sms;

	@NotNull
	public Long src_port;

	@NotNull
	public Long dst_port;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date sent_date;

	@Size(max=64)
	public String ref_no;

	@NotNull
	public Long priority;

	@NotNull
	@Size(max=1)
	public String status;

	@NotNull
	public Long errors;

	@NotNull
	@Size(max=64)
	public String gateway_id;

	@Size(max=20)
	public String user_id;

	public Long id_refrence;

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public SmsserverOut(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public Long getId(){ return id; }
	public void setId(Long id){ this.id = id; }

	public String getType(){ return type; }
	public void setType(String type){ this.type = type; }

	public String getRecipient(){ return recipient; }
	public void setRecipient(String recipient){ this.recipient = recipient; }

	public String getText(){ return text; }
	public void setText(String text){ this.text = text; }

	public String getWap_url(){ return wap_url; }
	public void setWap_url(String wap_url){ this.wap_url = wap_url; }

	public Date getWap_expiry_date(){ return wap_expiry_date; }
	public void setWap_expiry_date(Date wap_expiry_date){ this.wap_expiry_date = wap_expiry_date; }

	public String getWap_signal(){ return wap_signal; }
	public void setWap_signal(String wap_signal){ this.wap_signal = wap_signal; }

	public Date getCreate_date(){ return create_date; }
	public void setCreate_date(Date create_date){ this.create_date = create_date; }

	public String getOriginator(){ return originator; }
	public void setOriginator(String originator){ this.originator = originator; }

	public String getEncoding(){ return encoding; }
	public void setEncoding(String encoding){ this.encoding = encoding; }

	public Long getStatus_report(){ return status_report; }
	public void setStatus_report(Long status_report){ this.status_report = status_report; }

	public Long getFlash_sms(){ return flash_sms; }
	public void setFlash_sms(Long flash_sms){ this.flash_sms = flash_sms; }

	public Long getSrc_port(){ return src_port; }
	public void setSrc_port(Long src_port){ this.src_port = src_port; }

	public Long getDst_port(){ return dst_port; }
	public void setDst_port(Long dst_port){ this.dst_port = dst_port; }

	public Date getSent_date(){ return sent_date; }
	public void setSent_date(Date sent_date){ this.sent_date = sent_date; }

	public String getRef_no(){ return ref_no; }
	public void setRef_no(String ref_no){ this.ref_no = ref_no; }

	public Long getPriority(){ return priority; }
	public void setPriority(Long priority){ this.priority = priority; }

	public String getStatus(){ return status; }
	public void setStatus(String status){ this.status = status; }

	public Long getErrors(){ return errors; }
	public void setErrors(Long errors){ this.errors = errors; }

	public String getGateway_id(){ return gateway_id; }
	public void setGateway_id(String gateway_id){ this.gateway_id = gateway_id; }

	public String getUser_id(){ return user_id; }
	public void setUser_id(String user_id){ this.user_id = user_id; }

	public Long getId_refrence(){ return id_refrence; }
	public void setId_refrence(Long id_refrence){ this.id_refrence = id_refrence; }


	public String getItemId() {return ""+id;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
