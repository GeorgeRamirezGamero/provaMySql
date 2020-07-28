package comjavatechie.spring.mysql.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Persona")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Persona {

	//Column indica la colonna dentro la tabella
	
	@Id
	@GeneratedValue()
	@Column
	private Integer id;
	@Column
	private String name;
	
	@OneToOne
	private Direzione direzione;
	
	@OneToMany
	private List<Carta> carteDiCredito;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		    name = "persona_abbonamenti",
		    joinColumns = @JoinColumn(name = "abbonamenti_id"),
		    inverseJoinColumns = @JoinColumn(name = "persone_id"))
		
	private List<Abbonamento> abbonamenti;

	
}
