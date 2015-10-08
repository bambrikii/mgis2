package ru.sovzond.mgis2.isogd.classifiers.documents;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author asd
 *         <p/>
 *         * 10. Классификатор документов, размещаемых в ИС ОГД. Код классификатора: 2.A
 *         <p/>
 *         Объект **
 */
@Entity
@Table(name = "isogd_cls_document_obj")
public class DocumentObject implements Cloneable {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "isogd_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * 1.01.01
	 */
	@Column(unique = true, nullable = false)
	private String code;

	@Column(nullable = false)
	private String name;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private DocumentClass documentClass;

	@OneToMany(mappedBy = "documentObject", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
	@OrderBy("code")
	private List<DocumentSubObject> documentSubObjects = new ArrayList<>();

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

	public DocumentClass getDocumentClass() {
		return documentClass;
	}

	public void setDocumentClass(DocumentClass documentClass) {
		this.documentClass = documentClass;
	}

	public List<DocumentSubObject> getDocumentSubObjects() {
		return documentSubObjects;
	}

	public void setDocumentSubObjects(List<DocumentSubObject> documentSubObjects) {
		this.documentSubObjects = documentSubObjects;
	}

	public DocumentObject clone() {
		DocumentObject documentObject = new DocumentObject();
		documentObject.setId(id);
		documentObject.setCode(code);
		documentObject.setName(name);
		DocumentClass documentClass = new DocumentClass();
		documentClass.setId(this.documentClass.getId());
		documentClass.setCode(this.documentClass.getCode());
		documentObject.setDocumentClass(documentClass);
		documentObject.setDocumentSubObjects(documentSubObjects.stream().map(documentSubObject -> documentSubObject.clone()).collect(Collectors.toList()));
		return documentObject;
	}

}
