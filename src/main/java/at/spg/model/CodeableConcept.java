package at.spg.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class CodeableConcept extends Element{
	
	public CodeableConcept() {super();}
	
	public CodeableConcept(Set<Coding> coding, String text) {
		super();
		this.coding = coding;
		this.text = text;
	}
	
	@OneToMany(cascade = CascadeType.ALL,targetEntity = Coding.class,fetch = FetchType.EAGER)
	@JoinColumn(name = "c_codeableconcept_fk", referencedColumnName = "id")
	private Set<Coding> coding;
	@Column(name="cc_text")
	private String text;
	
	public Set<Coding> getCoding() {
		return coding;
	}
	public void setCoding(Set<Coding> coding) {
		this.coding = coding;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		CodeableConcept that = (CodeableConcept) o;
		return Objects.equals(coding, that.coding) &&
				Objects.equals(text, that.text);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), coding, text);
	}
}
