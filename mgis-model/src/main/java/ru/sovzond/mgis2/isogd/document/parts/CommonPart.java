package ru.sovzond.mgis2.isogd.document.parts;

import ru.sovzond.mgis2.isogd.document.Document;

import javax.persistence.*;

/**
 * Created by asd on 22/06/15.
 */
@Entity
@Table(name = "isogd_document_special_part")
public class CommonPart implements Cloneable {
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

	public CommonPart clone() {
		CommonPart commonPart = new CommonPart();
		commonPart.setId(id);
		Document document = new Document();
		document.setId(this.document.getId());
		commonPart.setDocument(document);
		return commonPart;
	}
}
