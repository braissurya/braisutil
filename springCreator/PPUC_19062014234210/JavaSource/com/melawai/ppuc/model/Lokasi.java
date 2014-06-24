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
 * @Description: Model for table lokasi
 * @Revision	:
 */

public class Lokasi extends BaseObject implements Serializable  {

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotNull
	@Size(max=5)
	public String lok_kd;

	@NotNull
	@Size(max=3)
	public String divisi_kd;

	@NotNull
	@Size(max=3)
	public String subdiv_kd;

	@NotNull
	@Size(max=3)
	public String dept_kd;

	@Size(max=50)
	public String lok_nm;

	@Size(max=45)
	public String f_tutup;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_tutup;

	public Long counter;

	public Long max_counter;

	@Size(max=50)
	public String user_update;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_update;

	@Size(max=8)
	public String jam_update;

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
	public Lokasi(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public String getLok_kd(){ return lok_kd; }
	public void setLok_kd(String lok_kd){ this.lok_kd = lok_kd; }

	public String getDivisi_kd(){ return divisi_kd; }
	public void setDivisi_kd(String divisi_kd){ this.divisi_kd = divisi_kd; }

	public String getSubdiv_kd(){ return subdiv_kd; }
	public void setSubdiv_kd(String subdiv_kd){ this.subdiv_kd = subdiv_kd; }

	public String getDept_kd(){ return dept_kd; }
	public void setDept_kd(String dept_kd){ this.dept_kd = dept_kd; }

	public String getLok_nm(){ return lok_nm; }
	public void setLok_nm(String lok_nm){ this.lok_nm = lok_nm; }

	public String getF_tutup(){ return f_tutup; }
	public void setF_tutup(String f_tutup){ this.f_tutup = f_tutup; }

	public Date getTgl_tutup(){ return tgl_tutup; }
	public void setTgl_tutup(Date tgl_tutup){ this.tgl_tutup = tgl_tutup; }

	public Long getCounter(){ return counter; }
	public void setCounter(Long counter){ this.counter = counter; }

	public Long getMax_counter(){ return max_counter; }
	public void setMax_counter(Long max_counter){ this.max_counter = max_counter; }

	public String getUser_update(){ return user_update; }
	public void setUser_update(String user_update){ this.user_update = user_update; }

	public Date getTgl_update(){ return tgl_update; }
	public void setTgl_update(Date tgl_update){ this.tgl_update = tgl_update; }

	public String getJam_update(){ return jam_update; }
	public void setJam_update(String jam_update){ this.jam_update = jam_update; }

	public String getUser_create(){ return user_create; }
	public void setUser_create(String user_create){ this.user_create = user_create; }

	public Date getTgl_create(){ return tgl_create; }
	public void setTgl_create(Date tgl_create){ this.tgl_create = tgl_create; }


	public String getItemId() {return ""+lok_kd;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
