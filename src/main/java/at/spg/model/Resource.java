package at.spg.model;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@MappedSuperclass
public class Resource {
	
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
		Resource resource = (Resource) o;
		return Objects.equals(id, resource.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
