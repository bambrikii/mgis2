package ru.sovzond.mgis2.isogd.document;

import ru.sovzond.mgis2.common.classifiers.oktmo.Territory;
import ru.sovzond.mgis2.isogd.Volume;
import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentSubObject;
import ru.sovzond.mgis2.isogd.classifiers.documents.representation.RepresentationFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Alexander Arakelyan
 */
@Entity
@Table(name = "isogd_document")
public class Document {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "isogd_entity_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @Column
    private Long id;

    @Column
    private String docNumber;

    @Column
    private Date docDate;

    @Column
    private String name;

    @ManyToOne(optional = false)
    private Volume volume;

    @OneToOne(mappedBy = "document")
    private SpecialPart specialPart;

    @ManyToOne
    private DocumentSubObject documentSubObject;

    @ManyToOne
    private RepresentationFormat representationFormat;

    @ManyToOne
    private Territory oktmo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public RepresentationFormat getRepresentationFormat() {
        return representationFormat;
    }

    public void setRepresentationFormat(RepresentationFormat representationFormat) {
        this.representationFormat = representationFormat;
    }

    public Territory getOktmo() {
        return oktmo;
    }

    public void setOktmo(Territory oktmo) {
        this.oktmo = oktmo;
    }

    public DocumentSubObject getDocumentSubObject() {
        return documentSubObject;
    }

    public void setDocumentSubObject(DocumentSubObject documentSubObject) {
        this.documentSubObject = documentSubObject;
    }
}
