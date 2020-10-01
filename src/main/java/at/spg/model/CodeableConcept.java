package at.spg.model;

import java.util.Objects;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class CodeableConcept extends Element{
	
	public CodeableConcept() {super();}
	
	public CodeableConcept(List<Coding> coding, String text) {
		super();
		this.coding = coding;
		this.text = text;
	}
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "c_codeableconcept_fk", referencedColumnName = "id")
	private List<Coding> coding;
	
	@Column(name="cc_text")
	private String text;
	
	public List<Coding> getCoding() {
		return coding;
	}
	public void setCoding(List<Coding> coding) {
		this.coding = coding;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}
