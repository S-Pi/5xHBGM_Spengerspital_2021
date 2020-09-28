package at.spg.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Patient extends DomainResource{

	
	public Patient() {
		super();
	}

	public Patient(boolean active, GenderCode gender, LocalDate birthDate, Set<Identifier> identifier, Set<HumanName> name, Set<ContactPoint> telecom, boolean deceasedBoolean, Set<Address> addresses, Set<Attachment> photos) {
		this.active = active;
		this.gender = gender;
		this.birthDate = birthDate;
		this.identifier = identifier;
		this.name = name;
		this.telecom = telecom;
		this.deceasedBoolean = deceasedBoolean;
		this.addresses = addresses;
		this.photos = photos;
	}

	public enum GenderCode{
		male, female, other, unknown
	}
	
	@Column(name="p_active")
	private boolean active;
	
	@Enumerated(EnumType.STRING)
	@Column(name="p_gender")
	private GenderCode gender;
	
	@Column(name="p_birthdate")
	private LocalDate birthDate;

	@OneToMany(cascade = CascadeType.ALL,targetEntity = Identifier.class)
	@JoinColumn(name = "i_patient_fk", referencedColumnName = "id")     
	private Set<Identifier> identifier;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = HumanName.class)
	@JoinColumn(name = "hn_patient_fk", referencedColumnName = "id")
	private Set<HumanName> name;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = ContactPoint.class)
	@JoinColumn(name = "cp_patient_fk", referencedColumnName = "id")
	private Set<ContactPoint> telecom;

	@Column(name="p_deceasedBoolean")
	private boolean deceasedBoolean;

	//@Column(name="p_deceasedDateTime")
	//private LocalDate deceaseDateTime;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Address.class)
	@JoinColumn(name = "a_patient_fk", referencedColumnName = "id")
	private Set<Address> addresses;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Attachment.class)
	@JoinColumn(name = "at_patient_fk", referencedColumnName = "id")
	private Set<Attachment> photos;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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

	public Set<Identifier> getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Set<Identifier> identifier) {
		this.identifier = identifier;
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

	public boolean isDeceasedBoolean() {
		return deceasedBoolean;
	}

	public void setDeceasedBoolean(boolean deceasedBoolean) {
		this.deceasedBoolean = deceasedBoolean;
	}

	//public LocalDate getDeceaseDateTime() {
		//return deceaseDateTime;
	//}

	//public void setDeceaseDateTime(LocalDate deceaseDateTime) {
	//	this.deceaseDateTime = deceaseDateTime;
	//}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<Attachment> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<Attachment> photos) {
		this.photos = photos;
	}



	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Patient patient = (Patient) o;
		return active == patient.active &&
				deceasedBoolean == patient.deceasedBoolean &&
				gender == patient.gender &&
				Objects.equals(birthDate, patient.birthDate) &&
				Objects.equals(identifier, patient.identifier) &&
				Objects.equals(name, patient.name) &&
				Objects.equals(telecom, patient.telecom) &&
				Objects.equals(addresses, patient.addresses) &&
				Objects.equals(photos, patient.photos);
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, gender, birthDate, identifier, name, telecom, deceasedBoolean, addresses, photos);
	}
}
