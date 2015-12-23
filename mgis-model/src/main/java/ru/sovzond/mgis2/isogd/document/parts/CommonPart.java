package ru.sovzond.mgis2.isogd.document.parts;

import ru.sovzond.mgis2.isogd.document.Document;
import ru.sovzond.mgis2.isogd.document.DocumentContent;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by asd on 22/06/15.
 */
@Entity
@Table(name = "isogd_document_common_part")
public class CommonPart implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "isogd_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	private Document document;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<DocumentContent> documentContents = new ArrayList<>();

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

	public List<DocumentContent> getDocumentContents() {
		return documentContents;
	}

	public void setDocumentContents(List<DocumentContent> documentContents) {
		this.documentContents = documentContents;
	}

	public CommonPart clone() {
		CommonPart part = new CommonPart();
		part.setId(id);
		Document document = new Document();
		document.setId(this.document.getId());
		part.setDocument(document);
		part.setDocumentContents(documentContents.stream().map(documentContent -> {
			DocumentContent documentContent1 = new DocumentContent();
			documentContent1.setId(documentContent.getId());
			documentContent1.setFileName(documentContent.getFileName());
			//documentContent1.setBytes(documentContent.getBytes());
			documentContent1.setRepresentationFormat(documentContent.getRepresentationFormat() != null ? documentContent.getRepresentationFormat().clone() : null);
			return documentContent1;
		}).collect(Collectors.toList()));
		return part;
	}

}
