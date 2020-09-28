package at.spg.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
public class Identifier extends Element{
	
	public Identifier() {super();}
	
	public Identifier(UseCode code, CodeableConcept type, String system, String value, Period period) {
		super();
		this.code = code;
		this.type = type;
		this.system = system;
		this.value = value;
		this.period = period;
	}

	public enum UseCode{
		usual , official , temp , secondary , old;
	}
	@Enumerated(EnumType.STRING)
	@Column(name="i_code")
	private UseCode code;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="i_codeableconcept_fk", referencedColumnName="id")
	private CodeableConcept type;
	
	@Column(name="i_system")
	private String system;
	
	@Column(name="i_value")
	private String value;
	
	@Embedded
	private Period period;

	public UseCode getCode() {
		return code;
	}

	public void setCode(UseCode code) {
		this.code = code;
	}

	public CodeableConcept getType() {
		return type;
	}

	public void setType(CodeableConcept type) {
		this.type = type;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Identifier that = (Identifier) o;
		return code == that.code &&
				Objects.equals(type, that.type) &&
				Objects.equals(system, that.system) &&
				Objects.equals(value, that.value) &&
				Objects.equals(period, that.period);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), code, type, system, value, period);
	}
}
