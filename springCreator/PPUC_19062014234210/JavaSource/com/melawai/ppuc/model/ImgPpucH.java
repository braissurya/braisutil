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
 * @since		: Thu Jun 19 23:42:34 ICT 2014
 * @Description: Model for table img_ppuc_h
 * @Revision	:
 */

public class ImgPpucH extends BaseObject implements Serializable  {

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

	@Size(max=10)
	public String no_batch;

	@NotNull
	@Size(max=25)
	public String no_realisasi;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style="M-")
	public Date tgl_realisasi;

	@Size(max=50)
	public String user_entry;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_entry;

	@Size(max=8)
	public String jam_entry;

	public Long f_confirm;

	@Size(max=50)
	public String user_confirm;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_confirm;

	@Size(max=8)
	public String jam_confirm;

	public byte[] file_image;

	@Size(max=50)
	public String user_create;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style="MM")
	public Date tgl_create;

	@Size(max=100)
	public String file_name;

	@Size(max=45)
	public String file_ext;

	@Size(max=10)
	public String file_no;

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public ImgPpucH(){
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

	public String getNo_realisasi(){ return no_realisasi; }
	public void setNo_realisasi(String no_realisasi){ this.no_realisasi = no_realisasi; }

	public Date getTgl_realisasi(){ return tgl_realisasi; }
	public void setTgl_realisasi(Date tgl_realisasi){ this.tgl_realisasi = tgl_realisasi; }

	public String getUser_entry(){ return user_entry; }
	public void setUser_entry(String user_entry){ this.user_entry = user_entry; }

	public Date getTgl_entry(){ return tgl_entry; }
	public void setTgl_entry(Date tgl_entry){ this.tgl_entry = tgl_entry; }

	public String getJam_entry(){ return jam_entry; }
	public void setJam_entry(String jam_entry){ this.jam_entry = jam_entry; }

	public Long getF_confirm(){ return f_confirm; }
	public void setF_confirm(Long f_confirm){ this.f_confirm = f_confirm; }

	public String getUser_confirm(){ return user_confirm; }
	public void setUser_confirm(String user_confirm){ this.user_confirm = user_confirm; }

	public Date getTgl_confirm(){ return tgl_confirm; }
	public void setTgl_confirm(Date tgl_confirm){ this.tgl_confirm = tgl_confirm; }

	public String getJam_confirm(){ return jam_confirm; }
	public void setJam_confirm(String jam_confirm){ this.jam_confirm = jam_confirm; }

	public byte[] getFile_image(){ return file_image; }
	public void setFile_image(byte[] file_image){ this.file_image = file_image; }

	public String getUser_create(){ return user_create; }
	public void setUser_create(String user_create){ this.user_create = user_create; }

	public Date getTgl_create(){ return tgl_create; }
	public void setTgl_create(Date tgl_create){ this.tgl_create = tgl_create; }

	public String getFile_name(){ return file_name; }
	public void setFile_name(String file_name){ this.file_name = file_name; }

	public String getFile_ext(){ return file_ext; }
	public void setFile_ext(String file_ext){ this.file_ext = file_ext; }

	public String getFile_no(){ return file_no; }
	public void setFile_no(String file_no){ this.file_no = file_no; }


	public String getItemId() {return ""+divisi_kd+"/"+subdiv_kd+"/"+dept_kd+"/"+lok_kd+"/"+no_ppuc+"/"+tgl_ppuc+"/"+no_realisasi;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
