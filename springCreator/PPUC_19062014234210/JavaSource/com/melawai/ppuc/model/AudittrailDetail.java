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
 * @Description: Model for table sys_audittrail_detail
 * @Revision	:
 */

public class AudittrailDetail extends BaseObject implements Serializable  {

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotNull
	public Long id_audittrail_detail;

	@NotNull
	public Long id_audittrail;

	@Size(max=50)
	public String field;

	@Size(max=65535)
	public String before;

	@Size(max=65535)
	public String after;

	@Size(max=50)
	public String user_create;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_create;

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public AudittrailDetail(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public Long getId_audittrail_detail(){ return id_audittrail_detail; }
	public void setId_audittrail_detail(Long id_audittrail_detail){ this.id_audittrail_detail = id_audittrail_detail; }

	public Long getId_audittrail(){ return id_audittrail; }
	public void setId_audittrail(Long id_audittrail){ this.id_audittrail = id_audittrail; }

	public String getField(){ return field; }
	public void setField(String field){ this.field = field; }

	public String getBefore(){ return before; }
	public void setBefore(String before){ this.before = before; }

	public String getAfter(){ return after; }
	public void setAfter(String after){ this.after = after; }

	public String getUser_create(){ return user_create; }
	public void setUser_create(String user_create){ this.user_create = user_create; }

	public Date getTgl_create(){ return tgl_create; }
	public void setTgl_create(Date tgl_create){ this.tgl_create = tgl_create; }


	public String getItemId() {return ""+id_audittrail_detail;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
