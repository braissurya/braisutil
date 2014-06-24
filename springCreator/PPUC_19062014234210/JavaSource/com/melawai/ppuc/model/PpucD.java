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
 * @Description: Model for table ppuc_d
 * @Revision	:
 */

public class PpucD extends BaseObject implements Serializable  {

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

	@Size(max=5)
	public String kd_group;

	@NotNull
	@Size(max=30)
	public String kd_biaya;

	@Size(max=10)
	public String no_batch;

	public Long qty;

	public double harga;

	public double total;

	public Long qty_old;

	public double harga_old;

	public double total_old;

	@Size(max=65535)
	public String keterangan;

	public Long qty_real_cbg;

	public double harga_real_cbg;

	public double total_real_cbg;

	public Long qty_real_oc;

	public double harga_real_oc;

	public double total_real_oc;

	public double nilai_charge;

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
	public PpucD(){
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

	public String getKd_group(){ return kd_group; }
	public void setKd_group(String kd_group){ this.kd_group = kd_group; }

	public String getKd_biaya(){ return kd_biaya; }
	public void setKd_biaya(String kd_biaya){ this.kd_biaya = kd_biaya; }

	public String getNo_batch(){ return no_batch; }
	public void setNo_batch(String no_batch){ this.no_batch = no_batch; }

	public Long getQty(){ return qty; }
	public void setQty(Long qty){ this.qty = qty; }

	public double getHarga(){ return harga; }
	public void setHarga(double harga){ this.harga = harga; }

	public double getTotal(){ return total; }
	public void setTotal(double total){ this.total = total; }

	public Long getQty_old(){ return qty_old; }
	public void setQty_old(Long qty_old){ this.qty_old = qty_old; }

	public double getHarga_old(){ return harga_old; }
	public void setHarga_old(double harga_old){ this.harga_old = harga_old; }

	public double getTotal_old(){ return total_old; }
	public void setTotal_old(double total_old){ this.total_old = total_old; }

	public String getKeterangan(){ return keterangan; }
	public void setKeterangan(String keterangan){ this.keterangan = keterangan; }

	public Long getQty_real_cbg(){ return qty_real_cbg; }
	public void setQty_real_cbg(Long qty_real_cbg){ this.qty_real_cbg = qty_real_cbg; }

	public double getHarga_real_cbg(){ return harga_real_cbg; }
	public void setHarga_real_cbg(double harga_real_cbg){ this.harga_real_cbg = harga_real_cbg; }

	public double getTotal_real_cbg(){ return total_real_cbg; }
	public void setTotal_real_cbg(double total_real_cbg){ this.total_real_cbg = total_real_cbg; }

	public Long getQty_real_oc(){ return qty_real_oc; }
	public void setQty_real_oc(Long qty_real_oc){ this.qty_real_oc = qty_real_oc; }

	public double getHarga_real_oc(){ return harga_real_oc; }
	public void setHarga_real_oc(double harga_real_oc){ this.harga_real_oc = harga_real_oc; }

	public double getTotal_real_oc(){ return total_real_oc; }
	public void setTotal_real_oc(double total_real_oc){ this.total_real_oc = total_real_oc; }

	public double getNilai_charge(){ return nilai_charge; }
	public void setNilai_charge(double nilai_charge){ this.nilai_charge = nilai_charge; }

	public String getUser_create(){ return user_create; }
	public void setUser_create(String user_create){ this.user_create = user_create; }

	public Date getTgl_create(){ return tgl_create; }
	public void setTgl_create(Date tgl_create){ this.tgl_create = tgl_create; }


	public String getItemId() {return ""+divisi_kd+"/"+subdiv_kd+"/"+dept_kd+"/"+lok_kd+"/"+no_ppuc+"/"+tgl_ppuc+"/"+kd_biaya;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
