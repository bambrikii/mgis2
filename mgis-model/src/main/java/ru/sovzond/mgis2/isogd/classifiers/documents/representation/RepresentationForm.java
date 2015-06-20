package ru.sovzond.mgis2.isogd.classifiers.documents.representation;

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
 *         11. Классификатор формы представления документов системы. Код классификатора: 2.B
 */
@Entity
@Table(name = "isogd_cls_document_repr_form")
public class RepresentationForm {

	@Id
	@SequenceGenerator(name = "pk_sequence", sequenceName = "isogd_entity_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
	@Column
	private Long id;

	/**
	 * Код формы представления документа
	 */
	@Column(unique = true, nullable = false)
	private String code;

	@OneToMany(mappedBy = "representationForm", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
	private List<RepresentationFormat> representationFormat = new ArrayList<RepresentationFormat>();

}
