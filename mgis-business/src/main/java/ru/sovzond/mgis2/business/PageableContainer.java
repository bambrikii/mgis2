package ru.sovzond.mgis2.business;

import java.util.List;

public class PageableContainer<T> {
	private List<T> list;
	private Number totalNumberOfItems;
	private Number currentPosition;
	private Number itemsPerPage;

	public PageableContainer(List<T> list) {
		this.list = list;
		this.totalNumberOfItems = list.size();
	}

	public PageableContainer(List<T> list, Number totalNumberOfItems, Number currentPosition, Number itemsPerPage) {
		this.list = list;
		this.totalNumberOfItems = totalNumberOfItems;
		this.currentPosition = currentPosition;
		this.itemsPerPage = itemsPerPage;
	}

	public List<T> getList() {
		return list;
	}

	public Number getTotalNumberOfItems() {
		return totalNumberOfItems;
	}

	public Number getCurrentPosition() {
		return currentPosition;
	}

	public Number getItemsPerPage() {
		return itemsPerPage;
	}

}
