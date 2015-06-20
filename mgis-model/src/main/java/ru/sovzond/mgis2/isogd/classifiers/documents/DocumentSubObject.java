package ru.sovzond.mgis2.isogd.classifiers.documents;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author asd
 *
 *         10. Классификатор документов, размещаемых в ИС ОГД. Код классификатора: 2.A
 * 
 *         Подобъект
 */
@Entity
@Table(name = "isogd_cls_document_sub_obj")
public class DocumentSubObject {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "isogd_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private DocumentObject documentObject;

	/**
	 * Наименование документа
	 */
	@Column(unique = true, nullable = false)
	private String name;
}
