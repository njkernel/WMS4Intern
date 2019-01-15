package com.connext.wms.util;

import java.util.List;

public class Page {

	//每页条数
	public static final int PAGE_SIZE = 3;
	
	//当前页
	private int currPage = 1;
	
	//总记录数
	private Long totalCount;
	
	//总页数
	private Long totalPage;
	
	//数据
	private List data;
	
	/**
	 * 计算总页数
	 */
	public void init() {
		if(this.totalCount % PAGE_SIZE == 0) {
			this.totalPage = this.totalCount/PAGE_SIZE;
		}else {
			this.totalPage = this.totalCount/PAGE_SIZE+1;
		}
	}

	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public static int getPageSize() {
		return PAGE_SIZE;
	}
	
	
}

