package ru.sovzond.mgis2.business;

import java.util.List;

public class PageableContainer<T> {
	private List<T> list;
	private Number count;

	public PageableContainer(List<T> list, Number count) {
		this.list = list;
		this.count = count;
	}

	public List<T> getList() {
		return list;
	}

	public Number getCount() {
		return count;
	}

}
