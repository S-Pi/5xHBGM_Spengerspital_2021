package at.spg.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Qualification extends BackboneElement {
    public Qualification(Set<Identifier> identifier, CodeableConcept code, Period period) {
        this.identifier = identifier;
        this.code = code;
        this.period = period;
    }

    public Qualification() {
    }

    @OneToMany(cascade = CascadeType.ALL,targetEntity = Identifier.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "i_qualification_fk", referencedColumnName = "id")
    private Set<Identifier> identifier;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="q_codeableconcept_fk", referencedColumnName="id")
    private CodeableConcept code;
    @Embedded
    private Period period;

    public Set<Identifier> getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Set<Identifier> identifier) {
        this.identifier = identifier;
    }

    public CodeableConcept getCode() {
        return code;
    }

    public void setCode(CodeableConcept code) {
        this.code = code;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
