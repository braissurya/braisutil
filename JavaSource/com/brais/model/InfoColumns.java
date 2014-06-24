package com.brais.model;

import java.io.Serializable;



public class InfoColumns implements Serializable {
	
	
	private static final long serialVersionUID = 8070884692824244186L;
	
	public String namakolom;
	public String dataTypekolom;
	public String dataTypekolomJava;
	public String dataTypekolomJDBC;
	public String is_nullable;
	public Integer numericscale;
	public Integer charactermaximumlength;
	public String namakey;
	public String extra;
	public String tableName;
	public String modelName;
	public String viewName;
	public String viewKolom;
	public String modelKolom;
	public Boolean sysTable;
	public Boolean syskolom;
	
	public String getNamakolom() {
		return namakolom;
	}
	public void setNamakolom(String namakolom) {
		this.namakolom = namakolom;
	}
	public String getDataTypekolom() {
		return dataTypekolom;
	}
	public void setDataTypekolom(String dataTypekolom) {
		this.dataTypekolom = dataTypekolom;
	}
	public String getIs_nullable() {
		return is_nullable;
	}
	public void setIs_nullable(String is_nullable) {
		this.is_nullable = is_nullable;
	}
	public Integer getNumericscale() {
		return numericscale;
	}
	public String getNamakey() {
		return namakey;
	}
	public void setNamakey(String namakey) {
		this.namakey = namakey;
	}
	public void setNumericscale(Integer numericscale) {
		this.numericscale = numericscale;
	}
	public Integer getCharactermaximumlength() {
		return charactermaximumlength;
	}
	public void setCharactermaximumlength(Integer charactermaximumlength) {
		this.charactermaximumlength = charactermaximumlength;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public String getDataTypekolomJava() {
		return dataTypekolomJava;
	}
	public void setDataTypekolomJava(String dataTypekolomJava) {
		this.dataTypekolomJava = dataTypekolomJava;
	}
	public String getDataTypekolomJDBC() {
		return dataTypekolomJDBC;
	}
	public void setDataTypekolomJDBC(String dataTypekolomJDBC) {
		this.dataTypekolomJDBC = dataTypekolomJDBC;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getViewName() {
		return viewName;
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	public String getViewKolom() {
		return viewKolom;
	}
	public void setViewKolom(String viewKolom) {
		this.viewKolom = viewKolom;
	}
	public String getModelKolom() {
		return modelKolom;
	}
	public void setModelKolom(String modelKolom) {
		this.modelKolom = modelKolom;
	}
	public Boolean getSysTable() {
		return sysTable;
	}
	public void setSysTable(Boolean sysTable) {
		this.sysTable = sysTable;
	}
	public Boolean getSyskolom() {
		return syskolom;
	}
	public void setSyskolom(Boolean syskolom) {
		this.syskolom = syskolom;
	}
	
	
	
	
}
