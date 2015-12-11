package ru.sovzond.mgis2.reports;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexander Arakelyan on 11/12/15.
 */
public class Report implements Cloneable {
	private Long id;
	private String code;
	private String name;
	private byte[] bytes;
	private Set<String> filters = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public Set<String> getFilters() {
		return filters;
	}

	public void setFilters(Set<String> filters) {
		this.filters = filters;
	}

	public Report clone() {
		Report report = new Report();
		report.setId(id);
		report.setCode(code);
		report.setName(name);
		report.setBytes(bytes);
		report.setFilters(filters);
		return report;
	}
}
