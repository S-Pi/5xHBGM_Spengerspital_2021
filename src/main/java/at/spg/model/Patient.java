package at.spg.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.List;

import javax.persistence.*;

@Entity
public class Patient extends DomainResource{

	
	public Patient() {
		super();
	}

	public Patient(boolean active, GenderCode gender, LocalDate birthDate, List<Identifier> identifier, List<HumanName> name, List<ContactPoint> telecom, boolean deceasedBoolean, List<Address> addresses, List<Attachment> photos) {
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

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "i_patient_fk", referencedColumnName = "id")     
	private List<Identifier> identifier;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "hn_patient_fk", referencedColumnName = "id")
	private List<HumanName> name;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "cp_patient_fk", referencedColumnName = "id")
	private List<ContactPoint> telecom;

	@Column(name="p_deceasedBoolean")
	private boolean deceasedBoolean;

	//@Column(name="p_deceasedDateTime")
	//private LocalDate deceaseDateTime;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "a_patient_fk", referencedColumnName = "id")
	private List<Address> addresses;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "at_patient_fk", referencedColumnName = "id")
	private List<Attachment> photos;

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

	public List<Identifier> getIdentifier() {
		return identifier;
	}

	public void setIdentifier(List<Identifier> identifier) {
		this.identifier = identifier;
	}

	public List<HumanName> getName() {
		return name;
	}

	public void setName(List<HumanName> name) {
		this.name = name;
	}

	public List<ContactPoint> getTelecom() {
		return telecom;
	}

	public void setTelecom(List<ContactPoint> telecom) {
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

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Attachment> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Attachment> photos) {
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
