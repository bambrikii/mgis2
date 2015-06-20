package ru.sovzond.mgis2.isogd.classifiers.documents;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author asd
 *
 *         * 10. Классификатор документов, размещаемых в ИС ОГД. Код классификатора: 2.A
 * 
 *         Объект **
 */
@Entity
@Table(name = "isogd_cls_document_obj")
public class DocumentObject {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "isogd_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private DocumentClass documentClass;

	/**
	 * 1.01.01
	 */
	@Column(unique = true, nullable = false)
	private String code;

	@OneToMany(mappedBy = "documentObject", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
	private List<DocumentSubObject> documentSubObjects = new ArrayList<DocumentSubObject>();
}
