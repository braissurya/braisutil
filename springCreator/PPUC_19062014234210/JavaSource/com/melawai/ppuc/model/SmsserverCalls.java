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
 * @since		: Thu Jun 19 23:42:37 ICT 2014
 * @Description: Model for table smsserver_calls
 * @Revision	:
 */

public class SmsserverCalls extends BaseObject implements Serializable  {

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotNull
	public Long id;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date call_date;

	@NotNull
	@Size(max=64)
	public String gateway_id;

	@NotNull
	@Size(max=16)
	public String caller_id;

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public SmsserverCalls(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public Long getId(){ return id; }
	public void setId(Long id){ this.id = id; }

	public Date getCall_date(){ return call_date; }
	public void setCall_date(Date call_date){ this.call_date = call_date; }

	public String getGateway_id(){ return gateway_id; }
	public void setGateway_id(String gateway_id){ this.gateway_id = gateway_id; }

	public String getCaller_id(){ return caller_id; }
	public void setCaller_id(String caller_id){ this.caller_id = caller_id; }


	public String getItemId() {return ""+id;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
