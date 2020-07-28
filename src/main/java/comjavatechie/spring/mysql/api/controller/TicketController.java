package comjavatechie.spring.mysql.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import comjavatechie.spring.mysql.api.model.Ticket;
import comjavatechie.spring.mysql.api.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping("ticket")
@RestController
@Api(tags = {"TICKETS"})
public class TicketController {

	//DAL CONTROLLER CHIAMO IL SERVICE 
	
//    private static final Logger log = LoggerFactory.getLogger(TicketController.class);

	
	@Autowired
	private TicketService ticketDataAccessService;
	
    @RequestMapping(value = "/insertTickets", method = RequestMethod.POST,consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Salva a DB i vari ticket", notes = "Ritorna un esito")
    public @ResponseBody String bookTicket(@RequestBody List<Ticket> tickets, HttpServletRequest request, HttpServletResponse response) {
    	return ticketDataAccessService.insertTicket(tickets);
	}
    
    @RequestMapping(value = "/getTickets", method = RequestMethod.GET, produces = {"application/json", "application/xml"}) 
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Ritorna lista di ticket a DB", notes = "")
	public @ResponseBody List<Ticket> getTickets(HttpServletRequest request, HttpServletResponse response) {
		return (List<Ticket>) ticketDataAccessService.getAllTicket();
	}

	@RequestMapping(value = "/deleteTickets", method = RequestMethod.DELETE, produces = {"application/json", "application/xml"}) 
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Rimuove ticket per ID", notes = "")
	public @ResponseBody String deleteTickets(@RequestParam (name = "id") Integer id, HttpServletRequest request, HttpServletResponse response) {	
		return ticketDataAccessService.deleteTicket(id);
	}
	
	@RequestMapping(value = "/getForCategory", method = RequestMethod.GET, produces = {"application/json", "application/xml"}) 
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Ritorna lista di ticket per categoria", notes = "")
	public @ResponseBody Ticket getTicketsForCategory(@RequestParam (name = "category") String category, HttpServletRequest request, HttpServletResponse response) {
		return ticketDataAccessService.cercaPerCategoria(category);
	}
	
	@RequestMapping(value = "/getBetweenAmount", method = RequestMethod.GET, produces = {"application/json", "application/xml"}) 
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Ritorna lista di ticket in un determinato range", notes = "")
	public @ResponseBody List<Ticket> getTicketsForCategory(@RequestParam (name = "amount1") double amount1, @RequestParam (name = "amount2") double amount2, HttpServletRequest request, HttpServletResponse response) {
		return ticketDataAccessService.cercaRangeAmount(amount1, amount2);
	}
	
	@RequestMapping(value = "/getLessAmount", method = RequestMethod.GET, produces = {"application/json", "application/xml"}) 
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Ritorna lista di ticket miorori dell amount indicato", notes = "")
	public @ResponseBody List<Ticket> getLessAmount(@RequestParam (name = "amount") double amount, HttpServletRequest request, HttpServletResponse response) {
		return ticketDataAccessService.cercaLessAmount(amount);
	}
	
	//ENTITY MANAGER
	@RequestMapping(value = "/getMaggioreDi", method = RequestMethod.GET, produces = {"application/json", "application/xml"}) 
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "", notes = "")
	public @ResponseBody List<Ticket> cercaTicketMaggioreDi(@RequestParam (name = "amount") Integer amount, HttpServletRequest request, HttpServletResponse response) {
		return ticketDataAccessService.cercaTicketMaggioreDi(amount);
	}
	
	@RequestMapping(value = "/getMaggioreDiProcedure", method = RequestMethod.GET, produces = {"application/json", "application/xml"}) 
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "", notes = "")
	public @ResponseBody List<Ticket> getMaggioreDiProcedure(@RequestParam (name = "amount") Integer amount, HttpServletRequest request, HttpServletResponse response) {
		return ticketDataAccessService.cercaTicketMaggioreDiProcedure(amount);
	}
	
	@RequestMapping(value = "/getTicketByCategory", method = RequestMethod.GET, produces = {"application/json", "application/xml"}) 
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "", notes = "")
	public @ResponseBody int getTicketByCategory(@RequestParam (name = "category") String category, HttpServletRequest request, HttpServletResponse response) {
		return ticketDataAccessService.cercaTicketByCategory(category);
	}
	
//	@RequestMapping(value = "/getTicketGreater", method = RequestMethod.GET, produces = {"application/json", "application/xml"}) 
//    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation(value = "", notes = "")
//	public @ResponseBody HashMap<String, Object> getTicketGreater(@RequestParam (name = "category") String category, HttpServletRequest request, HttpServletResponse response) {
//		return ticketDataAccessService.cercaTicketByCategoryHasMap(category);
//	}
//	
}
