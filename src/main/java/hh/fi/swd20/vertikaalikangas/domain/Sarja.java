package hh.fi.swd20.vertikaalikangas.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import net.minidev.json.annotate.JsonIgnore;


@Entity
@Table(name = "Sarja")
public class Sarja {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String sarjanNimi;
	
	
	//ManyToMany	 TOIMII (paitsi liikken lisäys sarjaan ja päin vastoin
	@JsonIgnore
	@ManyToMany(mappedBy = "luodutsarjat")
	private List<Liike> lisatytLiikkeet = new ArrayList<Liike>();
	
	
	public Sarja(String sarjanNimi) {
		super();
		this.sarjanNimi = sarjanNimi;
	}
	
	public Sarja() {
		super();
		this.sarjanNimi = null;
	}

	public void lisaaLiike(Liike liike) {
		lisatytLiikkeet.add(liike);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSarjanNimi() {
		return sarjanNimi;
	}

	public void setSarjanNimi(String sarjanNimi) {
		this.sarjanNimi = sarjanNimi;
	}

	public List<Liike> getLisatytLiikkeet() {
		return lisatytLiikkeet;
	}

	public void setLisatytLiikkeet(List<Liike> lisatytLiikkeet) {
		this.lisatytLiikkeet = lisatytLiikkeet;
	}

	@Override
	public String toString() {
		return "Sarja [id=" + id + ", sarjanNimi=" + sarjanNimi + ", lisatytLiikkeet=" + lisatytLiikkeet + "]";
	}
	

	//OneToMany TOIMII
	/*@JsonIgnoreProperties("sarja")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sarja")
	private List<Liike> liikkeet;
	
	
	@ManyToMany(mappedBy = "sarjat")
	private Set<Liike> liikkeet = new HashSet<Liike>();

	public Sarja(String sarjanNimi) {
		super();
		this.sarjanNimi = sarjanNimi;
	}

	public Sarja() {
		super();
		this.sarjanNimi = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSarjanNimi() {
		return sarjanNimi;
	}

	public void setSarjanNimi(String sarjanNimi) {
		this.sarjanNimi = sarjanNimi;
	}

	public List<Liike> getLiikkeet() {
		return liikkeet;
	}

	public void setLiikkeet(List<Liike> liikkeet) {
		this.liikkeet = liikkeet;
	}

	@Override
	public String toString() {
		return "Sarja [id=" + id + ", sarjanNimi=" + sarjanNimi + "]";
	} */
	
	

}
