package at.spg.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Participant extends BackboneElement{

    public Participant(Set<CodeableConcept> type, Period period, Reference individual) {
        this.type = type;
        this.period = period;
        this.individual = individual;
    }

    public Participant() {
    }

    @OneToMany(cascade = CascadeType.ALL,targetEntity = CodeableConcept.class)
    @JoinColumn(name = "cc_participant_fk", referencedColumnName = "id")
    private Set<CodeableConcept> type;
    @Column(name="period")
    private Period period;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="part_reference_fk", referencedColumnName="id")
    private Reference individual;


    public Set<CodeableConcept> getType() {
        return type;
    }

    public void setType(Set<CodeableConcept> type) {
        this.type = type;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
