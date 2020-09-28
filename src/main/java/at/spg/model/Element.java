package at.spg.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@MappedSuperclass
public class Element {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Element element = (Element) o;
		return Objects.equals(id, element.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
