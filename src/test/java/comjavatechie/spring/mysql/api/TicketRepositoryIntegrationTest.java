package comjavatechie.spring.mysql.api;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import comjavatechie.spring.mysql.api.model.Ticket;
import comjavatechie.spring.mysql.api.service.TicketService;

//@Sql(scripts = {"/ticket.sql"}) //Con questa annotation carico una file sql per poter fare dei test al nostro DB (questo file deve essere messo dentro la cartella 
								// resources di test.)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketRepositoryIntegrationTest {

    private static final Logger log = LoggerFactory.getLogger(TicketRepositoryIntegrationTest.class);
	
    //Inietttimao il nostro DAO
//	@Autowired
//	private TicketDao ticketDao;
	
	@Autowired
	private TicketService ticketService;
	
//	@Before //con SETUP () La prima cosa che deve fare la classe test è creare degli oggetti ticket per poi salvarli a DB
//	public void setup() {
//		Ticket ticket1 = new Ticket(11, "PROVA1");
//		Ticket ticket3 = new Ticket(12, "PROVA2");
//		Ticket ticket2 = new Ticket(13, "PROVA3");
//		
//		//arrays.AsList ritornar una arraylist con dentro bli oggetti appena creati, che poi salveremo a DB PER I TEST
//		ticketDao.saveAll(Arrays.asList(ticket1, ticket2, ticket3 ));
//	}
	
//	@After//ALLA fine dei test svuotiamo tutta la tabella ticket
//	public void clear() {
//		ticketDao.deleteAll();
//	}
	
	//Qui sotto ci sono i test unit dei nostri microservizi
	@Test
	public void testInsertTicket() {
		
		Ticket ticketTest = new Ticket();
		ticketTest.setAmount(110);
		ticketTest.setCategory("PROVA110");
		
		Ticket ticketTest2 = new Ticket();
		ticketTest.setAmount(111);
		ticketTest.setCategory("PROVA111");
		
		String response= ticketService.insertTicket(Arrays.asList(ticketTest, ticketTest2));
		
		if (response != null) {
			log.info("***** Response TestInsertTicket --> "+ response);
		}
		assertTrue(response != null);
	}
	
	@Test
	public void testGetAllTicket() {
		
		List<Ticket> response= ticketService.getAllTicket();
		if (response != null) {
			for (Ticket ticket : response) {
				log.info("***** TICKET -->"+ ticket.getCategory() + " " + ticket.getAmount());
			}
		}
		assertTrue(response != null);
	}
	
	@Test
	public void testDeleteTicket() {
		
		String response= ticketService.deleteTicket(11);
		
		if (response != null) {
			log.info("RESPONSE DELETE " + response);
		}
		assertTrue(response != null);
	}
	
	@Test
	public void testCercaPerCategoria() {
		
		Ticket response= ticketService.cercaPerCategoria("PROVA1");
		if (response != null) {
			log.info("***** TICKET -->"+ response.getCategory() + " " + response.getAmount());
		}
		assertTrue(response != null);
	}
	
	@Test
	public void testCercaRangeAmount() {
		
		List<Ticket>response= ticketService.cercaRangeAmount(123, 126);
		if (response != null && !response.isEmpty()) {
			
			for (Ticket ticket : response) {
				log.info("***** TICKET -->"+ ticket.getCategory() + " " + ticket.getAmount());
			}
		}
		assertTrue(response.size()>0);
	}
	
	@Test
	public void testCercaLessAmount() {
		
		List<Ticket>response= ticketService.cercaLessAmount(129);
		if (response != null && !response.isEmpty()) {
			
			for (Ticket ticket : response) {
				log.info("***** TICKET -->"+ ticket.getCategory() + " " + ticket.getAmount());
			}
			
		}
//		assertEquals(3, response.size());
		assertTrue(response.size()>0);
	}
	
	//Qui si usa l'entity Manager
	@Test
	public void testCercaTicketMaggioreDi() {
		List<Ticket>response= ticketService.cercaTicketMaggioreDi(124);
		assertTrue(response.size()>0);
	}
	
	@Test
	public void testCercaTicketMaggioreDiProcedure() {
		List<Ticket>response= ticketService.cercaTicketMaggioreDiProcedure(124);
		assertTrue(response.size()>0);
	}
	
	@Test
	public void testGetMaggioreDiProcedure() {
		
		int response= ticketService.cercaTicketByCategory("PROVA1");
		assertTrue(response>0);
	}
	
}
