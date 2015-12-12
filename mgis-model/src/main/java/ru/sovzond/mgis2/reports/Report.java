package ru.sovzond.mgis2.reports;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexander Arakelyan on 11/12/15.
 */
@Entity
@Table(name = "mgis2_reports_report")
public class Report implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "mgis2_report_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column(unique = true, nullable = false)
	private String code;

	@Column
	private String name;

	@Column
	private String sortOrder;

	@Column
	private byte[] bytes;

	@ElementCollection
	@CollectionTable(name = "mgis2_reports_report_filters")
	@Column(name = "filter")
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

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
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
		report.setSortOrder(sortOrder);
		report.setFilters(filters);
		return report;
	}
}
