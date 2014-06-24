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
 * @Description: Model for table smsserver_in
 * @Revision	:
 */

public class SmsserverIn extends BaseObject implements Serializable  {

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotNull
	public Long id;

	@NotNull
	public Long process;

	@NotNull
	@Size(max=16)
	public String originator;

	@NotNull
	@Size(max=1)
	public String type;

	@NotNull
	@Size(max=1)
	public String encoding;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date message_date;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date receive_date;

	@NotNull
	@Size(max=1000)
	public String text;

	@Size(max=64)
	public String original_ref_no;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date original_receive_date;

	@Size(max=64)
	public String gateway_id;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style="M-")
	public Date PROCESS_DATE;

	@Size(max=20)
	public String user_id;

	@Size(max=500)
	public String PROSES_KET;

	public Long id_refrence;

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public SmsserverIn(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public Long getId(){ return id; }
	public void setId(Long id){ this.id = id; }

	public Long getProcess(){ return process; }
	public void setProcess(Long process){ this.process = process; }

	public String getOriginator(){ return originator; }
	public void setOriginator(String originator){ this.originator = originator; }

	public String getType(){ return type; }
	public void setType(String type){ this.type = type; }

	public String getEncoding(){ return encoding; }
	public void setEncoding(String encoding){ this.encoding = encoding; }

	public Date getMessage_date(){ return message_date; }
	public void setMessage_date(Date message_date){ this.message_date = message_date; }

	public Date getReceive_date(){ return receive_date; }
	public void setReceive_date(Date receive_date){ this.receive_date = receive_date; }

	public String getText(){ return text; }
	public void setText(String text){ this.text = text; }

	public String getOriginal_ref_no(){ return original_ref_no; }
	public void setOriginal_ref_no(String original_ref_no){ this.original_ref_no = original_ref_no; }

	public Date getOriginal_receive_date(){ return original_receive_date; }
	public void setOriginal_receive_date(Date original_receive_date){ this.original_receive_date = original_receive_date; }

	public String getGateway_id(){ return gateway_id; }
	public void setGateway_id(String gateway_id){ this.gateway_id = gateway_id; }

	public Date getProcess_date(){ return PROCESS_DATE; }
	public void setProcess_date(Date PROCESS_DATE){ this.PROCESS_DATE = PROCESS_DATE; }

	public String getUser_id(){ return user_id; }
	public void setUser_id(String user_id){ this.user_id = user_id; }

	public String getProses_ket(){ return PROSES_KET; }
	public void setProses_ket(String PROSES_KET){ this.PROSES_KET = PROSES_KET; }

	public Long getId_refrence(){ return id_refrence; }
	public void setId_refrence(Long id_refrence){ this.id_refrence = id_refrence; }


	public String getItemId() {return ""+id;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
