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
 * @Description: Model for table sys_counter
 * @Revision	:
 */

public class Counter extends BaseObject implements Serializable  {

	//****************** COLOMN FROM TABLE START HERE ******************/
	@NotNull
	public Long id;

	@Size(max=50)
	public String nm_counter;

	public Long counter;

	public Long max;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style="M-")
	public Date last_periode;

	@Size(max=45)
	public String keterangan;

	//****************** COLOMN FROM TABLE END HERE ******************/


	//****************** OTHERS START HERE ******************/

	public String itemId;
	//****************** OTHERS END HERE ******************/


	//****************** CONSTRUCTOR START HERE ******************/
	public Counter(){
		//TODO: standard constructor free to change
	}

	//****************** CONSTRUCTOR END HERE ******************/


	//****************** GETTER SETTER START HERE ******************/
	public Long getId(){ return id; }
	public void setId(Long id){ this.id = id; }

	public String getNm_counter(){ return nm_counter; }
	public void setNm_counter(String nm_counter){ this.nm_counter = nm_counter; }

	public Long getCounter(){ return counter; }
	public void setCounter(Long counter){ this.counter = counter; }

	public Long getMax(){ return max; }
	public void setMax(Long max){ this.max = max; }

	public Date getLast_periode(){ return last_periode; }
	public void setLast_periode(Date last_periode){ this.last_periode = last_periode; }

	public String getKeterangan(){ return keterangan; }
	public void setKeterangan(String keterangan){ this.keterangan = keterangan; }


	public String getItemId() {return ""+id;	}
	public void setItemId(String itemId) {this.itemId = itemId;}

	//****************** GETTER SETTER END HERE ******************/

}
