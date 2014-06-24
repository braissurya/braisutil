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
 * @Description: Model for table sys_audittrail
 * @Revision	:
 */

public class Audittrail extends BaseObject implements Serializable  {

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotNull
	public Long id_audittrail;

	@Size(max=10)
	public String type_audit;

	@Size(max=50)
	public String model;

	@Size(max=50)
	public String model_id;

	@Size(max=100)
	public String ip;

	@Size(max=65535)
	public String info;

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
	public Audittrail(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public Long getId_audittrail(){ return id_audittrail; }
	public void setId_audittrail(Long id_audittrail){ this.id_audittrail = id_audittrail; }

	public String getType_audit(){ return type_audit; }
	public void setType_audit(String type_audit){ this.type_audit = type_audit; }

	public String getModel(){ return model; }
	public void setModel(String model){ this.model = model; }

	public String getModel_id(){ return model_id; }
	public void setModel_id(String model_id){ this.model_id = model_id; }

	public String getIp(){ return ip; }
	public void setIp(String ip){ this.ip = ip; }

	public String getInfo(){ return info; }
	public void setInfo(String info){ this.info = info; }

	public String getUser_create(){ return user_create; }
	public void setUser_create(String user_create){ this.user_create = user_create; }

	public Date getTgl_create(){ return tgl_create; }
	public void setTgl_create(Date tgl_create){ this.tgl_create = tgl_create; }


	public String getItemId() {return ""+id_audittrail;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
