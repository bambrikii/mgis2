package ru.sovzond.mgis2.isogd.document;

import ru.sovzond.mgis2.isogd.classifiers.documents.representation.RepresentationFormat;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 28/06/15.
 */
@Entity
@Table(name = "isogd_document_content")
public class DocumentContent implements Cloneable {
	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "isogd_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@Column
	private String fileName;

	@Column
	private byte[] bytes;

	@ManyToOne(optional = false)
	private RepresentationFormat representationFormat;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public RepresentationFormat getRepresentationFormat() {
		return representationFormat;
	}

	public void setRepresentationFormat(RepresentationFormat representationFormat) {
		this.representationFormat = representationFormat;
	}

	public DocumentContent clone() {
		DocumentContent documentContent = new DocumentContent();
		documentContent.setId(id);
		documentContent.setFileName(fileName);
		documentContent.setBytes(bytes);
		documentContent.setRepresentationFormat(representationFormat != null ? representationFormat.clone() : null);
		return documentContent;
	}
}
