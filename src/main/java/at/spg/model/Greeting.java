package at.spg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "greeting")
public class Greeting {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String content;

	  public Greeting(long id, String content) {
	    this.id = id;
	    this.content = content;
	  }
	  
	  public Greeting() {}

	  public long getId() {
	    return id;
	  }

	  public String getContent() {
	    return content;
	  }
}
