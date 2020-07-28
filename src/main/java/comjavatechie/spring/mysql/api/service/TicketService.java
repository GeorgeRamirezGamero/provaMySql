package comjavatechie.spring.mysql.api.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import comjavatechie.spring.mysql.api.dao.TicketDao;
import comjavatechie.spring.mysql.api.model.Ticket;

@Service
public class TicketService {

    private static final Logger log = LoggerFactory.getLogger(TicketService.class);

	
	@Autowired
	private TicketDao ticketDao;
	
	@Autowired
	private EntityManager em;


	public TicketService() {
	}

	public String insertTicket(List<Ticket> tickets) {

		String responseMethod = "";
		List<Ticket> returnDb = (List<Ticket>) ticketDao.findAll();

		if (returnDb.isEmpty()) {
			ticketDao.saveAll(tickets);
		} else {

			for (Ticket ticket : tickets) {
				Ticket ticketDb = ticketDao.findByCategory(ticket.getCategory());

				if (ticketDb == null) {
					ticketDao.save(ticket);
				} else {
					responseMethod = responseMethod + ticket.getCategory() + " ";
				}

			}
		}

		if (responseMethod.length() == 0) {
			return "ALL TICKET SAVED";
		} else {

			return "OBJECT " + responseMethod + " NOT SAVED BECAUSE ALREADY EXIST";
		}

	}

	public List<Ticket> getAllTicket() {
		return (List<Ticket>) ticketDao.findAll();
	}

	public String deleteTicket(Integer id) {
		String response = "";
		try {
			ticketDao.deleteById(id);
			response = "Delete Success";
			return response;
		} catch (Exception e) {
			// TODO: handle exception
			response = e.getMessage();
			return response;
		}
	}

	public Ticket cercaPerCategoria(String category) {
		return ticketDao.findByCategory(category);
	}

	public List<Ticket> cercaRangeAmount(double amount1, double amount2) {
		return ticketDao.findByAmountBetween(amount1, amount2);
	}
	
	public List<Ticket> cercaLessAmount(double amount) {
		return ticketDao.findByAmountLessThan(amount);
	}

	//Qui si usa l'entity Manager
	@SuppressWarnings("unchecked")
	public List<Ticket> cercaTicketMaggioreDi(Integer amount) {		
		List<Ticket> appo = em.createNamedStoredProcedureQuery("Ticket.TicketGreater").setParameter("amount_in", amount).getResultList();
		return appo;
	}
	
	public List<Ticket> cercaTicketMaggioreDiProcedure(Integer ammount) {
		
		try {
//			HashMap<String, Object> mappa = ticketDao.getTicketGreater2(amount);
//			printMap(mappa);
//			log.info("MAPPA ----> " + JSONHelper.toJSONwithPrettyPrint(mappa));
//			return JSONHelper.convertValue(mappa, List.class);
			
//			if (mappa != null) {
//				log.info("SONO DIVERSO DA NULL");
//				return mappa.size();
//			}
//			else {
//				log.info("SONO NULL");
//				return null;
//			}
			
			List<Ticket> listTicketDb = ticketDao.getTicketGreaterQuery(ammount); 
			if (listTicketDb != null) {
				for (Ticket ticket : listTicketDb) {
					log.info("Ticket Id: " + ticket.getId() + " | Ticket Amount: " + ticket.getAmount() + " | Ticket category: " + ticket.getCategory());
				}
				return listTicketDb;
			}else {
				log.info("Non ci sono ticket con l'amount inserito");
				return null;
			}

		}catch(Exception ex){
			log.info(ex.getMessage());
			log.info(ex.getLocalizedMessage());
			return null;
		}
	}
	
	public int cercaTicketByCategory(String category) {
		return ticketDao.getTicketByCategoryDirectProcedure(category);		
	}
	
//	public HashMap<String, Object> cercaTicketByCategoryHasMap(String category) {
//		return ticketDao.getTicketGreaterHashMap(category);		
//	}
//	
//	 public static void printMap(Map mp) {
//	        Iterator it = mp.entrySet().iterator();
//	        while (it.hasNext()) {
//	            Map.Entry pair = (Map.Entry)it.next();
//	            System.out.println(pair.getKey() + " = " + pair.getValue());
//	            it.remove(); // avoids a ConcurrentModificationException
//	        }
//	    }
	
}
