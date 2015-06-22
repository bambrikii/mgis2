package ru.sovzond.mgis2.isogd;

import ru.sovzond.mgis2.isogd.classifiers.documents.DocumentObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Arakelyan
 */
@Entity
@Table(name = "isogd_book")
public class Book {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "isogd_entity_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @Column
    private Long id;

    @Column
    private String name;

    @ManyToOne(optional = false)
    private Section section;


    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private List<Volume> volumes = new ArrayList<>();


    @ManyToOne
    private DocumentObject documentObject;

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

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public List<Volume> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<Volume> volumes) {
        this.volumes = volumes;
    }

}
