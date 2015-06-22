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
 *         <p/>
 *         10. Классификатор документов, размещаемых в ИС ОГД. Код классификатора: 2.A
 *         <p/>
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

    @Column(unique = true)
    private String code;

    /**
     * Наименование документа
     */
    @Column(unique = true, nullable = false)
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DocumentObject documentObject;

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

    public DocumentObject getDocumentObject() {
        return documentObject;
    }

    public void setDocumentObject(DocumentObject documentObject) {
        this.documentObject = documentObject;
    }

    public DocumentSubObject clone() {
        DocumentSubObject documentSubObject = new DocumentSubObject();
        documentSubObject.setId(id);
        documentSubObject.setCode(code);
        documentSubObject.setName(name);
        DocumentObject documentObject = new DocumentObject();
        documentObject.setId(this.documentObject.getId());
        documentObject.setCode(this.getDocumentObject().getCode());
        documentSubObject.setDocumentObject(documentObject);
        return documentSubObject;
    }

}
