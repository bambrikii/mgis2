package ru.sovzond.mgis2.isogd;

import ru.sovzond.mgis2.Sortable;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexander Arakelyan
 */
@Entity
@Table(name = "isogd_book", indexes = {@Index(name = "isogd_book_sortorder_ix", columnList = "section_id, sort_order")})
public class Book implements Cloneable, Sortable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "isogd_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	private String name;

	@ManyToOne(optional = false)
	@JoinColumn(name = "section_id")
	private Section section;

	@Column(name = "sort_order")
	private Long sortOrder;

	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	private List<Volume> volumes = new ArrayList<>();


	@ManyToOne
	private DocumentObject documentObject;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public Long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public List<Volume> getVolumes() {
		return volumes;
	}

	public void setVolumes(List<Volume> volumes) {
		this.volumes = volumes;
	}

	public DocumentObject getDocumentObject() {
		return documentObject;
	}

	public void setDocumentObject(DocumentObject documentObject) {
		this.documentObject = documentObject;
	}

	public Book clone() {
		Book book2 = new Book();
		book2.setId(id);
		book2.setName(name);
		book2.setSection(section.clone());
		book2.setSortOrder(sortOrder);
		if (documentObject != null) {
			book2.setDocumentObject(documentObject.clone());
		}
		book2.setVolumes(volumes.stream().map(volume -> {
			Volume volume2 = new Volume();
			volume2.setId(volume.getId());
			volume2.setName(volume.getName());
			return volume2;
		}).collect(Collectors.toList()));
		return book2;
	}

}
