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
 * @since		: Mon Jun 16 00:24:07 ICT 2014
 * @Description: Model for table hak_biaya
 * @Revision	:
 */

public class HakBiaya extends BaseObject implements Serializable  {

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotNull
	@Size(max=5)
	public String divisi_kd;

	@NotNull
	@Size(max=5)
	public String subdiv_kd;

	@NotNull
	@Size(max=5)
	public String kd_group;

	@NotNull
	@Size(max=30)
	public String kd_biaya;

	public Long f_aktif;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style="M-")
	public Date drtgl;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style="M-")
	public Date sptgl;

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
	public HakBiaya(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public String getDivisi_kd(){ return divisi_kd; }
	public void setDivisi_kd(String divisi_kd){ this.divisi_kd = divisi_kd; }

	public String getSubdiv_kd(){ return subdiv_kd; }
	public void setSubdiv_kd(String subdiv_kd){ this.subdiv_kd = subdiv_kd; }

	public String getKd_group(){ return kd_group; }
	public void setKd_group(String kd_group){ this.kd_group = kd_group; }

	public String getKd_biaya(){ return kd_biaya; }
	public void setKd_biaya(String kd_biaya){ this.kd_biaya = kd_biaya; }

	public Long getF_aktif(){ return f_aktif; }
	public void setF_aktif(Long f_aktif){ this.f_aktif = f_aktif; }

	public Date getDrtgl(){ return drtgl; }
	public void setDrtgl(Date drtgl){ this.drtgl = drtgl; }

	public Date getSptgl(){ return sptgl; }
	public void setSptgl(Date sptgl){ this.sptgl = sptgl; }

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


	public String getItemId() {return "divisi_kd/subdiv_kd/kd_group/kd_biaya";	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
