package comjavatechie.spring.mysql.api.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Citta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Citta {

	//Column indica la colonna dentro la tabella
	
	@Id
	@GeneratedValue()
	@Column
	private Integer id;
	@Column
	private String citta;
	
	@OneToMany
	private Set<Direzione> direzioni;
}
