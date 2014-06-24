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
 * @Description: Model for table message_template
 * @Revision	:
 */

public class MessageTemplate extends BaseObject implements Serializable  {

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotNull
	public Long id_template;

	@Size(max=50)
	public String nm_template;

	@Size(max=400)
	public String parameter;

	@Size(max=200)
	public String keterangan;

	@Size(max=45)
	public String f_aktif;

	@Size(max=50)
	public String user_create;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_create;

	@Size(max=50)
	public String user_update;

	@Size(max=45)
	public String tgl_update;

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public MessageTemplate(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public Long getId_template(){ return id_template; }
	public void setId_template(Long id_template){ this.id_template = id_template; }

	public String getNm_template(){ return nm_template; }
	public void setNm_template(String nm_template){ this.nm_template = nm_template; }

	public String getParameter(){ return parameter; }
	public void setParameter(String parameter){ this.parameter = parameter; }

	public String getKeterangan(){ return keterangan; }
	public void setKeterangan(String keterangan){ this.keterangan = keterangan; }

	public String getF_aktif(){ return f_aktif; }
	public void setF_aktif(String f_aktif){ this.f_aktif = f_aktif; }

	public String getUser_create(){ return user_create; }
	public void setUser_create(String user_create){ this.user_create = user_create; }

	public Date getTgl_create(){ return tgl_create; }
	public void setTgl_create(Date tgl_create){ this.tgl_create = tgl_create; }

	public String getUser_update(){ return user_update; }
	public void setUser_update(String user_update){ this.user_update = user_update; }

	public String getTgl_update(){ return tgl_update; }
	public void setTgl_update(String tgl_update){ this.tgl_update = tgl_update; }


	public String getItemId() {return ""+id_template;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
