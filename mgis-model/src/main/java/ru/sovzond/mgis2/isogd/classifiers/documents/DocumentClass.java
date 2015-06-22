package ru.sovzond.mgis2.isogd.classifiers.documents;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author asd
 *         <p/>
 *         10. Классификатор документов, размещаемых в ИС ОГД. Код классификатора: 2.A
 *         <p/>
 *         Класс *
 */
@Entity
@Table(name = "isogd_cls_document_class")
public class DocumentClass implements Cloneable {

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

    @OneToMany(mappedBy = "documentClass", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private List<DocumentObject> documentObjects = new ArrayList<DocumentObject>();

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

    public List<DocumentObject> getDocumentObjects() {
        return documentObjects;
    }

    public void setDocumentObjects(List<DocumentObject> documentObjects) {
        this.documentObjects = documentObjects;
    }

    public DocumentClass clone() {
        DocumentClass documentClass = new DocumentClass();
        documentClass.setId(id);
        documentClass.setCode(code);
        documentClass.setDocumentObjects(documentObjects.stream().map(documentObject -> documentObject.clone()).collect(Collectors.toList()));
        return documentClass;
    }
}
