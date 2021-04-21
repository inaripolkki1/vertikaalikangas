package hh.fi.swd20.vertikaalikangas.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Liike")
public class Liike {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nimi;
	private String alkuasento;
	private int vaikeustaso;
	private double vaadittuKorkeus;
	private String erityisvaatimus;
	
	
	
	//ManyToMany 	TOIMII
	@JsonIgnoreProperties("liikkeet")
	@ManyToMany
	
	@JoinTable(
			name = "luodutsarjat",
			joinColumns = @JoinColumn(name = "liike_id"),
			inverseJoinColumns = @JoinColumn(name = "sarja_id")
			)
	private List<Sarja> luodutsarjat = new ArrayList<Sarja>();

	public Liike(String nimi, String alkuasento, int vaikeustaso, double vaadittuKorkeus, String erityisvaatimus) {
		super();
		this.nimi = nimi;
		this.alkuasento = alkuasento;
		this.vaikeustaso = vaikeustaso;
		this.vaadittuKorkeus = vaadittuKorkeus;
		this.erityisvaatimus = erityisvaatimus;
	}
	
	public Liike() {
		super();
		this.nimi = null;
		this.alkuasento = null;
		this.vaikeustaso = 0;
		this.vaadittuKorkeus = 0;
		this.erityisvaatimus = null;
	}
	
	public void luoSarja(Sarja sarja) {
		luodutsarjat.add(sarja);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public String getAlkuasento() {
		return alkuasento;
	}

	public void setAlkuasento(String alkuasento) {
		this.alkuasento = alkuasento;
	}

	public int getVaikeustaso() {
		return vaikeustaso;
	}

	public void setVaikeustaso(int vaikeustaso) {
		this.vaikeustaso = vaikeustaso;
	}

	public double getVaadittuKorkeus() {
		return vaadittuKorkeus;
	}

	public void setVaadittuKorkeus(double vaadittuKorkeus) {
		this.vaadittuKorkeus = vaadittuKorkeus;
	}

	public String getErityisvaatimus() {
		return erityisvaatimus;
	}

	public void setErityisvaatimus(String erityisvaatimus) {
		this.erityisvaatimus = erityisvaatimus;
	}

	public List<Sarja> getLuodutsarjat() {
		return luodutsarjat;
	}

	public void setLuodutsarjat(List<Sarja> luodutsarjat) {
		this.luodutsarjat = luodutsarjat;
	}

	@Override
	public String toString() {
		return "Liike [id=" + id + ", nimi=" + nimi + ", alkuasento=" + alkuasento + ", vaikeustaso=" + vaikeustaso
				+ ", vaadittuKorkeus=" + vaadittuKorkeus + ", erityisvaatimus=" + erityisvaatimus + ", luodutsarjat="
				+ luodutsarjat + "]";
	}
	
	

	//ManyToOne TOIMII
	/*
	@JsonIgnoreProperties("liikkeet")
	@ManyToOne
	@JoinColumn(name = "sarja_id")
	private Sarja sarja;

	public Long getId() {
		return id;
	}
	
	public Liike(String nimi, String alkuasento, int vaikeustaso, double vaadittuKorkeus, String erityisvaatimus,
			Sarja sarja) {
		super();
		this.nimi = nimi;
		this.alkuasento = alkuasento;
		this.vaikeustaso = vaikeustaso;
		this.vaadittuKorkeus = vaadittuKorkeus;
		this.erityisvaatimus = erityisvaatimus;
		this.sarja = sarja;
	}
	
	public Liike() {
		super();
		this.nimi = null;
		this.alkuasento = null;
		this.vaikeustaso = 0;
		this.vaadittuKorkeus = 0;
		this.erityisvaatimus = null;
		this.sarja = null;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public String getAlkuasento() {
		return alkuasento;
	}

	public void setAlkuasento(String alkuasento) {
		this.alkuasento = alkuasento;
	}

	public int getVaikeustaso() {
		return vaikeustaso;
	}

	public void setVaikeustaso(int vaikeustaso) {
		this.vaikeustaso = vaikeustaso;
	}

	public double getVaadittuKorkeus() {
		return vaadittuKorkeus;
	}

	public void setVaadittuKorkeus(double vaadittuKorkeus) {
		this.vaadittuKorkeus = vaadittuKorkeus;
	}

	public String getErityisvaatimus() {
		return erityisvaatimus;
	}

	public void setErityisvaatimus(String erityisvaatimus) {
		this.erityisvaatimus = erityisvaatimus;
	}

	public Sarja getSarja() {
		return sarja;
	}

	public void setSarja(Sarja sarja) {
		this.sarja = sarja;
	}

	@Override
	public String toString() {
		return "Liike [id=" + id + ", nimi=" + nimi + ", alkuasento=" + alkuasento + ", vaikeustaso=" + vaikeustaso
				+ ", vaadittuKorkeus=" + vaadittuKorkeus + ", erityisvaatimus=" + erityisvaatimus + ", sarja=" + sarja
				+ "]";
	}
 */
	
}

