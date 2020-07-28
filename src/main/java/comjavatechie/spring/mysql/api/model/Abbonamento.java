package comjavatechie.spring.mysql.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "abbonamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Abbonamento {

	//Column indica la colonna dentro la tabella
	
	@Id
	@GeneratedValue()
	@Column
	private Integer id;
	@Column
	private String tipoAbbonamento;
	@Column
	private String annoScadenza;
	
	
}
