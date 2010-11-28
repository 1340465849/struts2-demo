package com.bulain.common.page;

import java.io.Serializable;


public class Page implements Serializable{
	private static final long serialVersionUID = -7707816532423931718L;

	private static final int PAGE_SIZE = 10;

	private long low;
	private long high;
	private long count;

	private int pageSize = PAGE_SIZE;
	private int page;
	private int totalPage;
	
	public void setCount(final long cnt) {
		count = cnt<0 ? 0: cnt;
		totalPage=(int)(((count-1)/pageSize)+1);
		page = page<1?1:(page>totalPage?totalPage:page);
		low=(page-1)*pageSize;
		high=page*pageSize;
	}

	public long getCount() {
		return count;
	}
	public long getHigh() {
		return high;
	}
	public void setHigh(final long high) {
		this.high = high;
	}
	public long getLow() {
		return low;
	}
	public void setLow(final long low) {
		this.low = low;
	}
	public int getPage() {
		return page;
	}
	public void setPage(final int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(final int totalPage) {
		this.totalPage = totalPage;
	}
}
