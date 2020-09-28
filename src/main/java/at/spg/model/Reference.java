package at.spg.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Reference extends Element{

    public Reference(String reference, String type, Set<Identifier> identifier, String display) {
        this.reference = reference;
        this.type = type;
        this.identifier = identifier;
        this.display = display;
    }

    public Reference() {
    }

    @Column(name="reference")
    private String reference;
    @Column(name="type")
    private String type;
    @OneToMany(cascade = CascadeType.ALL,targetEntity = Identifier.class)
    @JoinColumn(name = "i_ref_fk", referencedColumnName = "id")
    private Set<Identifier> identifier;
    @Column(name="display")
    private String display;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Identifier> getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Set<Identifier> identifier) {
        this.identifier = identifier;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
}
