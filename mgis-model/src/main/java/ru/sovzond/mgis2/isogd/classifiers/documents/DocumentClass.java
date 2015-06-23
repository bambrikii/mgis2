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

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "documentClass", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private List<DocumentObject> documentObjects = new ArrayList<>();

    @Column
    private boolean hasCommonPart;

    @Column
    private boolean hasSpecialPart;


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

    public List<DocumentObject> getDocumentObjects() {
        return documentObjects;
    }

    public void setDocumentObjects(List<DocumentObject> documentObjects) {
        this.documentObjects = documentObjects;
    }

    public boolean isHasCommonPart() {
        return hasCommonPart;
    }

    public void setHasCommonPart(boolean hasCommonPart) {
        this.hasCommonPart = hasCommonPart;
    }

    public boolean isHasSpecialPart() {
        return hasSpecialPart;
    }

    public void setHasSpecialPart(boolean hasSpecialPart) {
        this.hasSpecialPart = hasSpecialPart;
    }

    public DocumentClass clone() {
        DocumentClass documentClass = new DocumentClass();
        documentClass.setId(id);
        documentClass.setCode(code);
        documentClass.setName(name);
        documentClass.setHasCommonPart(hasCommonPart);
        documentClass.setHasSpecialPart(hasSpecialPart);
        documentClass.setDocumentObjects(documentObjects.stream().map(documentObject -> documentObject.clone()).collect(Collectors.toList()));
        return documentClass;
    }

}
