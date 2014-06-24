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
 * @since		: Thu Jun 19 23:42:35 ICT 2014
 * @Description: Model for table menu
 * @Revision	:
 */

public class Menu extends BaseObject implements Serializable  {

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotNull
	public Long menu_id;

	public Long parent;

	@Size(max=50)
	public String nama;

	@Size(max=200)
	public String link;

	public Long level;

	public Long urut;

	@Size(max=50)
	public String path;

	public Long f_aktif;

	@Size(max=50)
	public String user_create;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_create;

	@Size(max=8)
	public String jam_create;

	@Size(max=50)
	public String user_nonaktif;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_nonaktif;

	@Size(max=8)
	public String jam_nonaktif;

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public Menu(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public Long getMenu_id(){ return menu_id; }
	public void setMenu_id(Long menu_id){ this.menu_id = menu_id; }

	public Long getParent(){ return parent; }
	public void setParent(Long parent){ this.parent = parent; }

	public String getNama(){ return nama; }
	public void setNama(String nama){ this.nama = nama; }

	public String getLink(){ return link; }
	public void setLink(String link){ this.link = link; }

	public Long getLevel(){ return level; }
	public void setLevel(Long level){ this.level = level; }

	public Long getUrut(){ return urut; }
	public void setUrut(Long urut){ this.urut = urut; }

	public String getPath(){ return path; }
	public void setPath(String path){ this.path = path; }

	public Long getF_aktif(){ return f_aktif; }
	public void setF_aktif(Long f_aktif){ this.f_aktif = f_aktif; }

	public String getUser_create(){ return user_create; }
	public void setUser_create(String user_create){ this.user_create = user_create; }

	public Date getTgl_create(){ return tgl_create; }
	public void setTgl_create(Date tgl_create){ this.tgl_create = tgl_create; }

	public String getJam_create(){ return jam_create; }
	public void setJam_create(String jam_create){ this.jam_create = jam_create; }

	public String getUser_nonaktif(){ return user_nonaktif; }
	public void setUser_nonaktif(String user_nonaktif){ this.user_nonaktif = user_nonaktif; }

	public Date getTgl_nonaktif(){ return tgl_nonaktif; }
	public void setTgl_nonaktif(Date tgl_nonaktif){ this.tgl_nonaktif = tgl_nonaktif; }

	public String getJam_nonaktif(){ return jam_nonaktif; }
	public void setJam_nonaktif(String jam_nonaktif){ this.jam_nonaktif = jam_nonaktif; }


	public String getItemId() {return ""+menu_id;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
