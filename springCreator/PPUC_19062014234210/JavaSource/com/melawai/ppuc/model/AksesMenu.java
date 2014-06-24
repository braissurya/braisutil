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
 * @Description: Model for table sys_akses_menu
 * @Revision	:
 */

public class AksesMenu extends BaseObject implements Serializable  {

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotNull
	@Size(max=5)
	public String group_kd;

	@NotNull
	public Long menu_id;

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
	public AksesMenu(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public String getGroup_kd(){ return group_kd; }
	public void setGroup_kd(String group_kd){ this.group_kd = group_kd; }

	public Long getMenu_id(){ return menu_id; }
	public void setMenu_id(Long menu_id){ this.menu_id = menu_id; }

	public String getUser_create(){ return user_create; }
	public void setUser_create(String user_create){ this.user_create = user_create; }

	public Date getTgl_create(){ return tgl_create; }
	public void setTgl_create(Date tgl_create){ this.tgl_create = tgl_create; }


	public String getItemId() {return ""+group_kd+"/"+menu_id;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
