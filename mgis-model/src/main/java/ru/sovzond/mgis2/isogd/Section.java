package ru.sovzond.mgis2.isogd;

import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentClass;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * @author Alexander Arakelyan
 */
@Entity
@Table(name = "isogd_section")
public class Section {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "isogd_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	private String name;

	@OneToMany(mappedBy = "section", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
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

}
