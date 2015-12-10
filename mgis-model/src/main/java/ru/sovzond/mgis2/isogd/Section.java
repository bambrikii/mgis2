package ru.sovzond.mgis2.isogd;

import ru.sovzond.mgis2.Sortable;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentClass;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexander Arakelyan
 */
@Entity
@Table(name = "isogd_section", indexes = {@Index(name = "isogd_section_sortorder_ix", columnList = "sort_order")})
public class Section implements Cloneable, Sortable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "isogd_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	private String name;

	@Column(name = "sort_order")
	private Long sortOrder;

	@OneToMany(mappedBy = "section", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	private List<Book> books = new ArrayList<>();

	@ManyToOne
	private DocumentClass documentClass;

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

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public DocumentClass getDocumentClass() {
		return documentClass;
	}

	public void setDocumentClass(DocumentClass documentClass) {
		this.documentClass = documentClass;
	}

	public Long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Section clone() {
		Section section = new Section();
		section.setId(id);
		section.setName(name);
		section.setDocumentClass(documentClass == null ? null : documentClass.clone());
		section.setSortOrder(sortOrder);
		section.setBooks(books.stream().map(book -> {
			Book book2 = new Book();
			book2.setId(book.getId());
			book2.setName(book.getName());
			return book2;
		}).collect(Collectors.toList()));
		return section;
	}

}
