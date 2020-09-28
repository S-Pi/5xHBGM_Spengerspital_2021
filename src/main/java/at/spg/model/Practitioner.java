package at.spg.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
@Entity
public class Practitioner extends DomainResource {

    public Practitioner(Set<Identifier> identifier, boolean active, Set<HumanName> name, Set<ContactPoint> telecom, Set<Address> address, GenderCode gender, LocalDate birthDate, Set<Attachment> photo, Set<CodeableConcept> communication) {
        this.identifier = identifier;
        this.active = active;
        this.name = name;
        this.telecom = telecom;
        this.address = address;
        this.gender = gender;
        this.birthDate = birthDate;
        this.photo = photo;
        this.communication = communication;
    }

    public Practitioner() {
    }

    public enum GenderCode{
        male, female, other, unknown
    }

    @OneToMany(cascade = CascadeType.ALL,targetEntity = Identifier.class)
    @JoinColumn(name = "i_practitioner_fk", referencedColumnName = "id")
    private Set<Identifier> identifier;
    @Column(name="active")
    private boolean active;
    @OneToMany(cascade = CascadeType.ALL, targetEntity = HumanName.class)
    @JoinColumn(name = "hn_practitioner_fk", referencedColumnName = "id")
    private Set<HumanName> name;
    @OneToMany(cascade = CascadeType.ALL, targetEntity = ContactPoint.class)
    @JoinColumn(name = "cp_practitioner_fk", referencedColumnName = "id")
    private Set<ContactPoint> telecom;
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "a_practitioner_fk", referencedColumnName = "id")
    private Set<Address> address;
    @Enumerated(EnumType.STRING)
    @Column(name="gender")
    private GenderCode gender;
    @Column(name="birthdate")
    private LocalDate birthDate;
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Attachment.class)
    @JoinColumn(name = "at_practitioner_fk", referencedColumnName = "id")
    private Set<Attachment> photo;
    @OneToMany(cascade = CascadeType.ALL, targetEntity = CodeableConcept.class)
    @JoinColumn(name = "cc_practitioner_fk", referencedColumnName = "id")
    private Set<CodeableConcept> communication;
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Qualification.class)
    @JoinColumn(name = "q_practitioner_fk", referencedColumnName = "id")
    private Set<Qualification> qualifications;


    public Set<Identifier> getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Set<Identifier> identifier) {
        this.identifier = identifier;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<HumanName> getName() {
        return name;
    }

    public void setName(Set<HumanName> name) {
        this.name = name;
    }

    public Set<ContactPoint> getTelecom() {
        return telecom;
    }

    public void setTelecom(Set<ContactPoint> telecom) {
        this.telecom = telecom;
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }

    public GenderCode getGender() {
        return gender;
    }

    public void setGender(GenderCode gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Attachment> getPhoto() {
        return photo;
    }

    public void setPhoto(Set<Attachment> photo) {
        this.photo = photo;
    }

    public Set<CodeableConcept> getCommunication() {
        return communication;
    }

    public void setCommunication(Set<CodeableConcept> communication) {
        this.communication = communication;
    }

    public Set<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(Set<Qualification> qualifications) {
        this.qualifications = qualifications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Practitioner that = (Practitioner) o;
        return active == that.active &&
                Objects.equals(identifier, that.identifier) &&
                Objects.equals(name, that.name) &&
                Objects.equals(telecom, that.telecom) &&
                Objects.equals(address, that.address) &&
                gender == that.gender &&
                Objects.equals(birthDate, that.birthDate) &&
                Objects.equals(photo, that.photo) &&
                Objects.equals(communication, that.communication) &&
                Objects.equals(qualifications, that.qualifications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), identifier, active, name, telecom, address, gender, birthDate, photo, communication, qualifications);
    }
}
