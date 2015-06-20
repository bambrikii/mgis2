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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author asd
 * 
 *         10. Классификатор документов, размещаемых в ИС ОГД. Код классификатора: 2.A
 * 
 *         Класс *
 */
@Entity
@Table(name = "isogd_cls_document_class")
public class DocumentClass {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "isogd_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Код объекта
	 */
	@Column(unique = true, nullable = false)
	private String code;

	@OneToMany(mappedBy = "documentClass", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
	private List<DocumentObject> documentObjects = new ArrayList<DocumentObject>();
}
