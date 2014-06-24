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
 * @Description: Model for table ppuc_h
 * @Revision	:
 */

public class PpucH extends BaseObject implements Serializable  {

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotNull
	@Size(max=3)
	public String divisi_kd;

	@NotNull
	@Size(max=3)
	public String subdiv_kd;

	@NotNull
	@Size(max=3)
	public String dept_kd;

	@NotNull
	@Size(max=5)
	public String lok_kd;

	@NotNull
	@Size(max=9)
	public String no_ppuc;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style="M-")
	public Date tgl_ppuc;

	@NotNull
	@Size(max=10)
	public String no_batch;

	@Size(max=50)
	public String user_create;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_create;

	@Size(max=8)
	public String jam_create;

	@Size(max=50)
	public String user_confirm;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_confirm;

	@Size(max=8)
	public String jam_confirm;

	@Size(max=25)
	public String hp_asal_create;

	@Size(max=25)
	public String hp_tujuan_create;

	@Size(max=100)
	public String email_asal_create;

	@Size(max=100)
	public String email_tujuan_create;

	@Size(max=5)
	public String divisi_kd_apprv;

	@Size(max=5)
	public String subdiv_kd_apprv;

	@Size(max=5)
	public String dept_kd_apprv;

	@Size(max=50)
	public String user_approve;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_approve;

	@Size(max=8)
	public String jam_approve;

	public Long f_approval;

	@Size(max=25)
	public String hp_asal_approve;

	@Size(max=25)
	public String hp_tujuan_approve;

	@Size(max=100)
	public String email_asal_approve;

	@Size(max=100)
	public String email_tujuan_approve;

	@Size(max=25)
	public String no_realisasi;

	@Size(max=50)
	public String user_realisasi;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_realisasi;

	@Size(max=8)
	public String jam_realisasi;

	@Size(max=50)
	public String user_conf_real;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_conf_real;

	@Size(max=8)
	public String jam_conf_real;

	@Size(max=50)
	public String user_conf_oc;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_conf_oc;

	@Size(max=8)
	public String jam_conf_oc;

	@Size(max=25)
	public String hp_asal_conf_oc;

	@Size(max=25)
	public String hp_tujuan_conf_oc;

	@Size(max=100)
	public String email_asal_conf_oc;

	@Size(max=100)
	public String email_tujuan_conf_oc;

	public Long f_batal;

	@Size(max=50)
	public String user_batal;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_batal;

	@Size(max=8)
	public String jam_batal;

	@Size(max=65535)
	public String alasan_batal;

	@Size(max=25)
	public String hp_asal_batal;

	@Size(max=25)
	public String hp_tujuan_batal;

	@Size(max=100)
	public String email_asal_batal;

	@Size(max=100)
	public String email_tujuan_batal;

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public PpucH(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public String getDivisi_kd(){ return divisi_kd; }
	public void setDivisi_kd(String divisi_kd){ this.divisi_kd = divisi_kd; }

	public String getSubdiv_kd(){ return subdiv_kd; }
	public void setSubdiv_kd(String subdiv_kd){ this.subdiv_kd = subdiv_kd; }

	public String getDept_kd(){ return dept_kd; }
	public void setDept_kd(String dept_kd){ this.dept_kd = dept_kd; }

	public String getLok_kd(){ return lok_kd; }
	public void setLok_kd(String lok_kd){ this.lok_kd = lok_kd; }

	public String getNo_ppuc(){ return no_ppuc; }
	public void setNo_ppuc(String no_ppuc){ this.no_ppuc = no_ppuc; }

	public Date getTgl_ppuc(){ return tgl_ppuc; }
	public void setTgl_ppuc(Date tgl_ppuc){ this.tgl_ppuc = tgl_ppuc; }

	public String getNo_batch(){ return no_batch; }
	public void setNo_batch(String no_batch){ this.no_batch = no_batch; }

	public String getUser_create(){ return user_create; }
	public void setUser_create(String user_create){ this.user_create = user_create; }

	public Date getTgl_create(){ return tgl_create; }
	public void setTgl_create(Date tgl_create){ this.tgl_create = tgl_create; }

	public String getJam_create(){ return jam_create; }
	public void setJam_create(String jam_create){ this.jam_create = jam_create; }

	public String getUser_confirm(){ return user_confirm; }
	public void setUser_confirm(String user_confirm){ this.user_confirm = user_confirm; }

	public Date getTgl_confirm(){ return tgl_confirm; }
	public void setTgl_confirm(Date tgl_confirm){ this.tgl_confirm = tgl_confirm; }

	public String getJam_confirm(){ return jam_confirm; }
	public void setJam_confirm(String jam_confirm){ this.jam_confirm = jam_confirm; }

	public String getHp_asal_create(){ return hp_asal_create; }
	public void setHp_asal_create(String hp_asal_create){ this.hp_asal_create = hp_asal_create; }

	public String getHp_tujuan_create(){ return hp_tujuan_create; }
	public void setHp_tujuan_create(String hp_tujuan_create){ this.hp_tujuan_create = hp_tujuan_create; }

	public String getEmail_asal_create(){ return email_asal_create; }
	public void setEmail_asal_create(String email_asal_create){ this.email_asal_create = email_asal_create; }

	public String getEmail_tujuan_create(){ return email_tujuan_create; }
	public void setEmail_tujuan_create(String email_tujuan_create){ this.email_tujuan_create = email_tujuan_create; }

	public String getDivisi_kd_apprv(){ return divisi_kd_apprv; }
	public void setDivisi_kd_apprv(String divisi_kd_apprv){ this.divisi_kd_apprv = divisi_kd_apprv; }

	public String getSubdiv_kd_apprv(){ return subdiv_kd_apprv; }
	public void setSubdiv_kd_apprv(String subdiv_kd_apprv){ this.subdiv_kd_apprv = subdiv_kd_apprv; }

	public String getDept_kd_apprv(){ return dept_kd_apprv; }
	public void setDept_kd_apprv(String dept_kd_apprv){ this.dept_kd_apprv = dept_kd_apprv; }

	public String getUser_approve(){ return user_approve; }
	public void setUser_approve(String user_approve){ this.user_approve = user_approve; }

	public Date getTgl_approve(){ return tgl_approve; }
	public void setTgl_approve(Date tgl_approve){ this.tgl_approve = tgl_approve; }

	public String getJam_approve(){ return jam_approve; }
	public void setJam_approve(String jam_approve){ this.jam_approve = jam_approve; }

	public Long getF_approval(){ return f_approval; }
	public void setF_approval(Long f_approval){ this.f_approval = f_approval; }

	public String getHp_asal_approve(){ return hp_asal_approve; }
	public void setHp_asal_approve(String hp_asal_approve){ this.hp_asal_approve = hp_asal_approve; }

	public String getHp_tujuan_approve(){ return hp_tujuan_approve; }
	public void setHp_tujuan_approve(String hp_tujuan_approve){ this.hp_tujuan_approve = hp_tujuan_approve; }

	public String getEmail_asal_approve(){ return email_asal_approve; }
	public void setEmail_asal_approve(String email_asal_approve){ this.email_asal_approve = email_asal_approve; }

	public String getEmail_tujuan_approve(){ return email_tujuan_approve; }
	public void setEmail_tujuan_approve(String email_tujuan_approve){ this.email_tujuan_approve = email_tujuan_approve; }

	public String getNo_realisasi(){ return no_realisasi; }
	public void setNo_realisasi(String no_realisasi){ this.no_realisasi = no_realisasi; }

	public String getUser_realisasi(){ return user_realisasi; }
	public void setUser_realisasi(String user_realisasi){ this.user_realisasi = user_realisasi; }

	public Date getTgl_realisasi(){ return tgl_realisasi; }
	public void setTgl_realisasi(Date tgl_realisasi){ this.tgl_realisasi = tgl_realisasi; }

	public String getJam_realisasi(){ return jam_realisasi; }
	public void setJam_realisasi(String jam_realisasi){ this.jam_realisasi = jam_realisasi; }

	public String getUser_conf_real(){ return user_conf_real; }
	public void setUser_conf_real(String user_conf_real){ this.user_conf_real = user_conf_real; }

	public Date getTgl_conf_real(){ return tgl_conf_real; }
	public void setTgl_conf_real(Date tgl_conf_real){ this.tgl_conf_real = tgl_conf_real; }

	public String getJam_conf_real(){ return jam_conf_real; }
	public void setJam_conf_real(String jam_conf_real){ this.jam_conf_real = jam_conf_real; }

	public String getUser_conf_oc(){ return user_conf_oc; }
	public void setUser_conf_oc(String user_conf_oc){ this.user_conf_oc = user_conf_oc; }

	public Date getTgl_conf_oc(){ return tgl_conf_oc; }
	public void setTgl_conf_oc(Date tgl_conf_oc){ this.tgl_conf_oc = tgl_conf_oc; }

	public String getJam_conf_oc(){ return jam_conf_oc; }
	public void setJam_conf_oc(String jam_conf_oc){ this.jam_conf_oc = jam_conf_oc; }

	public String getHp_asal_conf_oc(){ return hp_asal_conf_oc; }
	public void setHp_asal_conf_oc(String hp_asal_conf_oc){ this.hp_asal_conf_oc = hp_asal_conf_oc; }

	public String getHp_tujuan_conf_oc(){ return hp_tujuan_conf_oc; }
	public void setHp_tujuan_conf_oc(String hp_tujuan_conf_oc){ this.hp_tujuan_conf_oc = hp_tujuan_conf_oc; }

	public String getEmail_asal_conf_oc(){ return email_asal_conf_oc; }
	public void setEmail_asal_conf_oc(String email_asal_conf_oc){ this.email_asal_conf_oc = email_asal_conf_oc; }

	public String getEmail_tujuan_conf_oc(){ return email_tujuan_conf_oc; }
	public void setEmail_tujuan_conf_oc(String email_tujuan_conf_oc){ this.email_tujuan_conf_oc = email_tujuan_conf_oc; }

	public Long getF_batal(){ return f_batal; }
	public void setF_batal(Long f_batal){ this.f_batal = f_batal; }

	public String getUser_batal(){ return user_batal; }
	public void setUser_batal(String user_batal){ this.user_batal = user_batal; }

	public Date getTgl_batal(){ return tgl_batal; }
	public void setTgl_batal(Date tgl_batal){ this.tgl_batal = tgl_batal; }

	public String getJam_batal(){ return jam_batal; }
	public void setJam_batal(String jam_batal){ this.jam_batal = jam_batal; }

	public String getAlasan_batal(){ return alasan_batal; }
	public void setAlasan_batal(String alasan_batal){ this.alasan_batal = alasan_batal; }

	public String getHp_asal_batal(){ return hp_asal_batal; }
	public void setHp_asal_batal(String hp_asal_batal){ this.hp_asal_batal = hp_asal_batal; }

	public String getHp_tujuan_batal(){ return hp_tujuan_batal; }
	public void setHp_tujuan_batal(String hp_tujuan_batal){ this.hp_tujuan_batal = hp_tujuan_batal; }

	public String getEmail_asal_batal(){ return email_asal_batal; }
	public void setEmail_asal_batal(String email_asal_batal){ this.email_asal_batal = email_asal_batal; }

	public String getEmail_tujuan_batal(){ return email_tujuan_batal; }
	public void setEmail_tujuan_batal(String email_tujuan_batal){ this.email_tujuan_batal = email_tujuan_batal; }


	public String getItemId() {return ""+divisi_kd+"/"+subdiv_kd+"/"+dept_kd+"/"+lok_kd+"/"+no_ppuc+"/"+tgl_ppuc;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
