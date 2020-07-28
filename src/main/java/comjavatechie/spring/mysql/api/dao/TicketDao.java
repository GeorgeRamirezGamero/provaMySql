package comjavatechie.spring.mysql.api.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import comjavatechie.spring.mysql.api.model.Ticket;

//CrudRepository
//PagingAndSortingRepository
//jpaRepository


@Repository
//@Transactional
public interface TicketDao extends JpaRepository<Ticket, Integer> {

	//QUESTO COSA é UNA FIGATA!!!!  SQL INSERITO NEL NOME DEI METODI
	
	//ESMEPIO: findByCategory (selezione nella tabella ticket tutti i ticker con categoryu uguale a quello passato come parametro)
	//ESMEPIO: findByCategoryAndAmount (selezione nella tabella ticket tutti i ticker con category AND amount uguale a quello passati come parametro)
	
	//Qui springboot lavora con findBy senza che andiamo a implementare la interfaccia
	//documentazione importantissima: https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html
	//documentazione importantissima: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.details
	
	Ticket findByCategory (String category);
	List<Ticket> findByAmountBetween (double amount1, double amount2);
//	Ticket findByCategoryAndAmount (String category, double amount);
	List<Ticket> findByAmountLessThan (double amount);	


	//********************* PROCEDURE CHE FUNZIONANO *********************
	
//	@Procedure
//	int GET_TOTAL_TICKET_BY_CATEGORY(String category);
//	
	@Procedure ("GET_TOTAL_TICKET_BY_CATEGORY")
	int getTicketByCategoryDirectProcedure (String category);
//	
//	//LE PROCEDURE
//	//5.3. Reference a Stored Procedure with @Query Annotation -- FUNZIONA!
	@Query(value = "CALL FIND_TICKET_GREATER(:amount_in);", nativeQuery = true) //call storeProcedure, with parameters
	List<Ticket> getTicketGreaterQuery(@Param("amount_in") Integer amount);

	//********************* PROCEDURE CHE FUNZIONANO *********************	
	

	
	
	
	//****************************PROCEDURE CHE NON FUNZIONANO********************************************************************

//	@Procedure(name = "Ticket.TicketCount")
//	HashMap<String, Object> getTicketGreaterHashMap(@Param("category_in") String category);
	
	//???? Then, we can reference this definition in the repository: Fa riferimento alle annotation che sono in ticket.java 24 -27 
//	@Procedure(name = "Ticket.TicketGreater")
//	HashMap<String, Object> getTicketGreaterHashMap(@Param("amount_in") Integer amount);

	//???? There are four equivalent ways to do that. For example, we can use the stored procedure name directly as the method name:
//	@Procedure()
//	Object[] FIND_ALL_TICKET ();	
	
	//???? If we want to define a different method name, we can put the stored procedure name as the element of @Procedure annotation:
//	@Procedure ("FIND_TICKET_GREATER")
//	int getTicketGreater2 ();
	
	//???? We can also use the procedureName attribute to map the stored procedure name:
//	@Procedure (procedureName = "FIND_TICKET_GREATER")
//	HashMap<String, Object> getTicketGreater3 (Integer amount);

	//???? Similarly, we can use the value attribute to map the stored procedure name:
//	@Procedure (value = "FIND_TICKET_GREATER")
//	HashMap<String, Object> getTicketGreater4 ();
	
	//****************************PROCEDURE CHE NON FUNZIONANO********************************************************************

}
