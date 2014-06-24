package com.melawai.ppuc.model;

import java.io.Serializable;

/**
 * GENERATE BY BraisSpringMVCHelp
 * @since		: Thu Jun 19 23:42:25 ICT 2014
 * @Description: Model Base Object
 * @Revision	:
 */

public class BaseObject implements Serializable {

	//search helper
	public Integer rowcount;
	public Integer page;
	public String sort;
	public String search;

	//****************** GETTER SETTER START HERE ******************/
	public Integer getRowcount() { return rowcount; }
	public void setRowcount(Integer rowcount) { this.rowcount = rowcount; }

	public Integer getPage() { return page; }
	public void setPage(Integer page) { this.page = page; }

	public String getSort() { return sort; }
	public void setSort(String sort) { this.sort = sort; }

	public String getSearch() { return search; }
	public void setSearch(String search) { this.search = search; }

	//****************** GETTER SETTER END HERE ******************/

}
