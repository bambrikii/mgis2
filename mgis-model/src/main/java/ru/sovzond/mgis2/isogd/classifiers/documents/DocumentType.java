package ru.sovzond.mgis2.isogd.classifiers.documents;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author asd
 *         <p/>
 *         12. Классификатор документов территориального планирования Российской федерации и субъектов Российской Федерации. Код классификатора: 2.C
 */
@Entity
@Table(name = "isogd_cls_document_type")
public class DocumentType {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "isogd_entity_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @Column
    private Long id;

    /**
     * Код схемы КС
     */
    @Column(unique = true, nullable = false)
    private String code;

    /**
     * Наименование схемы территориального планирования
     */
    @Column(unique = true, nullable = false)
    private String name;

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

    public DocumentType clone() {
        DocumentType documentType = new DocumentType();
        documentType.setId(id);
        documentType.setCode(code);
        documentType.setName(name);
        return documentType;
    }
}
