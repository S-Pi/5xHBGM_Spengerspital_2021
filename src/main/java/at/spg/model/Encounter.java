package at.spg.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Encounter extends DomainResource{

    public Encounter() {
    }

    public Encounter(Set<Identifier> identifier, statusEnum status, Set<StatusHistory> statusHistory, Set<CodeableConcept> type, Reference subject, Set<Reference> episodeOfCare, Set<Participant> participant, Set<Reference> appointment, Period period, Set<Reference> reasonReference, Set<Diagnosis> diagnoses, Reference partOf) {
        this.identifier = identifier;
        this.status = status;
        this.statusHistory = statusHistory;
        this.type = type;
        this.subject = subject;
        this.episodeOfCare = episodeOfCare;
        this.participant = participant;
        this.appointment = appointment;
        this.period = period;
        this.reasonReference = reasonReference;
        this.diagnoses = diagnoses;
        this.partOf = partOf;
    }

    public enum statusEnum{
        planned, arrived, triaged, in_progress, onleave, finished, cancelled
    }

    @OneToMany(cascade = CascadeType.ALL,targetEntity = Identifier.class)
    @JoinColumn(name = "i_encounter_fk", referencedColumnName = "id")
    private Set<Identifier> identifier;
    @Enumerated(EnumType.STRING)
    private statusEnum status;
    @OneToMany(cascade = CascadeType.ALL, targetEntity = StatusHistory.class)
    @JoinColumn(name = "en_statushistory_fk", referencedColumnName = "id")
    private Set<StatusHistory> statusHistory;
    @OneToMany(cascade = CascadeType.ALL, targetEntity = CodeableConcept.class)
    @JoinColumn(name="en_codeableconcept_fk", referencedColumnName="id")
    private Set<CodeableConcept> type;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="enc_subject_reference_fk", referencedColumnName="id")
    private Reference subject;
    @OneToMany(cascade = CascadeType.ALL,targetEntity = Reference.class)
    @JoinColumn(name = "enc_epOfCa_reference_fk", referencedColumnName = "id")
    @Column(name="episodeOfCare")
    private Set<Reference> episodeOfCare;
    @OneToMany(cascade = CascadeType.ALL,targetEntity = Participant.class)
    @JoinColumn(name = "part_encounter_fk", referencedColumnName = "id")
    @Column(name="participant")
    private Set<Participant> participant;
    @OneToMany(cascade = CascadeType.ALL,targetEntity = Reference.class)
    @JoinColumn(name = "enc_appointment_reference_fk", referencedColumnName = "id")
    @Column(name="appointment")
    private Set<Reference> appointment;
    @Column(name="period")
    private Period period;
    @OneToMany(cascade = CascadeType.ALL,targetEntity = Reference.class)
    @JoinColumn(name = "enc_reason_reference_fk", referencedColumnName = "id")
    @Column(name="reasonReference")
    private Set<Reference> reasonReference;
    @OneToMany(cascade = CascadeType.ALL,targetEntity = Diagnosis.class)
    @JoinColumn(name = "enc_diagnosis_fk", referencedColumnName = "id")
    @Column(name="reasonReference")
    private Set<Diagnosis> diagnoses;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="enc_partOf_reference_fk", referencedColumnName="id")
    private Reference partOf;

    public Set<Identifier> getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Set<Identifier> identifier) {
        this.identifier = identifier;
    }

    public statusEnum getStatus() {
        return status;
    }

    public void setStatus(statusEnum status) {
        this.status = status;
    }

    public Set<StatusHistory> getStatusHistory() {
        return statusHistory;
    }

    public void setStatusHistory(Set<StatusHistory> statusHistory) {
        this.statusHistory = statusHistory;
    }

    public Set<CodeableConcept> getType() {
        return type;
    }

    public void setType(Set<CodeableConcept> type) {
        this.type = type;
    }

    public Reference getSubject() {
        return subject;
    }

    public void setSubject(Reference subject) {
        this.subject = subject;
    }

    public Set<Reference> getEpisodeOfCare() {
        return episodeOfCare;
    }

    public void setEpisodeOfCare(Set<Reference> episodeOfCare) {
        this.episodeOfCare = episodeOfCare;
    }

    public Set<Participant> getParticipant() {
        return participant;
    }

    public void setParticipant(Set<Participant> participant) {
        this.participant = participant;
    }

    public Set<Reference> getAppointment() {
        return appointment;
    }

    public void setAppointment(Set<Reference> appointment) {
        this.appointment = appointment;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Set<Reference> getReasonReference() {
        return reasonReference;
    }

    public void setReasonReference(Set<Reference> reasonReference) {
        this.reasonReference = reasonReference;
    }

    public Set<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Diagnosis> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public Reference getPartOf() {
        return partOf;
    }

    public void setPartOf(Reference partOf) {
        this.partOf = partOf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Encounter encounter = (Encounter) o;
        return Objects.equals(identifier, encounter.identifier) &&
                status == encounter.status &&
                Objects.equals(statusHistory, encounter.statusHistory) &&
                Objects.equals(type, encounter.type) &&
                Objects.equals(subject, encounter.subject) &&
                Objects.equals(episodeOfCare, encounter.episodeOfCare) &&
                Objects.equals(participant, encounter.participant) &&
                Objects.equals(appointment, encounter.appointment) &&
                Objects.equals(period, encounter.period) &&
                Objects.equals(reasonReference, encounter.reasonReference) &&
                Objects.equals(diagnoses, encounter.diagnoses) &&
                Objects.equals(partOf, encounter.partOf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), identifier, status, statusHistory, type, subject, episodeOfCare, participant, appointment, period, reasonReference, diagnoses, partOf);
    }
}
