package at.spg.model;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Diagnosis extends BackboneElement{
    public Diagnosis(Set<Reference> condition, CodeableConcept use, int rank) {
        this.condition = condition;
        this.use = use;
        this.rank = rank;
    }

    public Diagnosis() {
    }

    @OneToMany(cascade = CascadeType.ALL,targetEntity = Reference.class)
    @JoinColumn(name = "diag_reference_fk", referencedColumnName = "id")
    @Column(name="condition")
    private Set<Reference> condition;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="diag_codeableconcept_fk", referencedColumnName="id")
    private CodeableConcept use;
    @Column(name="rank")
    private int rank;

    public Set<Reference> getCondition() {
        return condition;
    }

    public void setCondition(Set<Reference> condition) {
        this.condition = condition;
    }

    public CodeableConcept getUse() {
        return use;
    }

    public void setUse(CodeableConcept use) {
        this.use = use;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
