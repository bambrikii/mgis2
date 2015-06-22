package ru.sovzond.mgis2.common.classifiers.oktmo;

import javax.persistence.*;

/**
 * Created by Alexander Arakelyan on 22.06.15.
 */
@Entity
@Table(name = "isogd_cls_document_type")
public class Territory implements Cloneable {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "common_classifiers_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    @Column
    private Long id;

    @Column
    private String code;

    @Column
    private String name;

    @ManyToOne
    private Territory parent;

    @Column
    private String comment;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Territory getParent() {
        return parent;
    }

    public void setParent(Territory parent) {
        this.parent = parent;
    }

    public Territory clone() {
        Territory territory = new Territory();
        territory.setId(id);
        territory.setCode(code);
        territory.setName(name);
        territory.setComment(comment);
        territory.setParent(parent.clone());
        return territory;
    }
}
