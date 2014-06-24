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
 * @Description: Model for table sys_log_email
 * @Revision	:
 */

public class LogEmail extends BaseObject implements Serializable  {

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotNull
	public Long id_log_email;

	@Size(max=3)
	public String divisi_kd;

	@Size(max=3)
	public String subdiv_kd;

	@Size(max=3)
	public String dept_kd;

	@Size(max=5)
	public String lok_kd;

	@Size(max=9)
	public String no_ppuc;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style="M-")
	public Date tgl_ppuc;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date send_date;

	@Size(max=8)
	public String send_time;

	@Size(max=1)
	public String status;

	@Size(max=65535)
	public String isi_email;

	@Size(max=100)
	public String mail_maker;

	@Size(max=100)
	public String mail_approval;

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
	public LogEmail(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public Long getId_log_email(){ return id_log_email; }
	public void setId_log_email(Long id_log_email){ this.id_log_email = id_log_email; }

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

	public Date getSend_date(){ return send_date; }
	public void setSend_date(Date send_date){ this.send_date = send_date; }

	public String getSend_time(){ return send_time; }
	public void setSend_time(String send_time){ this.send_time = send_time; }

	public String getStatus(){ return status; }
	public void setStatus(String status){ this.status = status; }

	public String getIsi_email(){ return isi_email; }
	public void setIsi_email(String isi_email){ this.isi_email = isi_email; }

	public String getMail_maker(){ return mail_maker; }
	public void setMail_maker(String mail_maker){ this.mail_maker = mail_maker; }

	public String getMail_approval(){ return mail_approval; }
	public void setMail_approval(String mail_approval){ this.mail_approval = mail_approval; }

	public String getUser_create(){ return user_create; }
	public void setUser_create(String user_create){ this.user_create = user_create; }

	public Date getTgl_create(){ return tgl_create; }
	public void setTgl_create(Date tgl_create){ this.tgl_create = tgl_create; }


	public String getItemId() {return ""+id_log_email;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
