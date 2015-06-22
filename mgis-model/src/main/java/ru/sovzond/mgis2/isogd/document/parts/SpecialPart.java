package ru.sovzond.mgis2.isogd.document.parts;

import ru.sovzond.mgis2.isogd.document.Document;

import javax.persistence.*;

@Entity
@Table(name = "isogd_document_special_part")
public class SpecialPart implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "isogd_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	private Document document;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public SpecialPart clone() {
		SpecialPart specialPart = new SpecialPart();
		specialPart.setId(id);
		Document document = new Document();
		document.setId(this.document.getId());
		specialPart.setDocument(document);
		return specialPart;
	}

}
