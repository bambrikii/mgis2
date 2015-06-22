package ru.sovzond.mgis2.isogd.classifiers.documents.representation;

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
 *         <p/>
 *         11. Классификатор формы представления документов системы. Код классификатора: 2.B
 */
@Entity
@Table(name = "isogd_cls_document_repr_format")
public class RepresentationFormat implements Cloneable {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "isogd_entity_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @Column
    private Long id;

    /**
     * Код формата представления документа
     */
    @Column(unique = true, nullable = false)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private RepresentationForm representationForm;

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

    public RepresentationForm getRepresentationForm() {
        return representationForm;
    }

    public void setRepresentationForm(RepresentationForm representationForm) {
        this.representationForm = representationForm;
    }

    public RepresentationFormat clone() {
        RepresentationFormat representationFormat = new RepresentationFormat();
        representationFormat.setId(id);
        representationFormat.setCode(code);
        RepresentationForm representationForm = new RepresentationForm();
        representationForm.setId(representationForm.getId());
        representationFormat.setRepresentationForm(representationForm);
        return representationFormat;
    }
}
