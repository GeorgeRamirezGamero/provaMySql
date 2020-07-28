package comjavatechie.spring.mysql.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Table(schema = "PIPPO", name = "Ticket")
@Table(name = "ticket")
@Getter
@Setter
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
//@XmlAccessorType(XmlAccessType.FIELD)

//We can also use the @NamedStoredProcedureQuery annotation to define a stored procedure in the entity class:

@NamedStoredProcedureQueries({ 	
	@NamedStoredProcedureQuery(name = "Ticket.TicketGreater", 
							   procedureName = "FIND_TICKET_GREATER", 
							   parameters = {@StoredProcedureParameter(mode = ParameterMode.IN, name = "amount_in", type = Integer.class)}),
																																	
	@NamedStoredProcedureQuery(name = "ticket.TicketCount", 
							   procedureName = "GET_TOTAL_TICKET_BY_CATEGORY", 
							   parameters = {@StoredProcedureParameter(mode = ParameterMode.IN, name = "category_in", type = String.class)
	})
	})

public class Ticket {

	//Column indica la colonna dentro la tabella
	
	@Id
	@GeneratedValue()
	@Column
	private Integer id;
	@Column
	private Integer amount;
	@Column
	private String category;
	
	@OneToOne
	private Persona persona;
	
}
