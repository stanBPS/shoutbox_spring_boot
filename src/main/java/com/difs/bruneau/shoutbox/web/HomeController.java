package com.difs.bruneau.shoutbox.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.difs.bruneau.shoutbox.model.Message;
import com.difs.bruneau.shoutbox.model.Report;
import com.difs.bruneau.shoutbox.model.User;
import com.difs.bruneau.shoutbox.repository.MessageRepository;
import com.difs.bruneau.shoutbox.repository.ReportRepository;
import com.difs.bruneau.shoutbox.repository.RoleRepository;
import com.difs.bruneau.shoutbox.repository.UserRepository;


@Controller
public class HomeController {

	
	@Autowired
	private UserRepository user_repository;
	
	@Autowired
	private MessageRepository message_repository;
	
	
	@Autowired
	private ReportRepository report_repository;
	
	@Autowired
	private RoleRepository role_repository;
	
	/**
	 * Affichage de la page login
	 * @return login
	 */
	@GetMapping(value = {"/","/login"})
	public String login() {
		return "login";
	}
	
	/**
	 * Affichage de la page chat avec la liste des messages des User 
	 * @param authentication
	 * @param model
	 * @return
	 */
	@GetMapping("/user/chat")
	public String chat(Authentication authentication,Model model) {
		model.addAttribute("username", authentication.getName());  // récuperation du nom du User connzcté
		model.addAttribute("user", user_repository.findAll());
		model.addAttribute("users1", user_repository.getUserByPseudo(authentication.getName()));// info de l'utilisateur connecté
		model.addAttribute("message", message_repository.findAll());
		model.addAttribute("report", report_repository.findAll());
		
		return "user/chat";
	}

	/**
	 * Création de l'objet User pour l'enregistrement 
	 * @param model
	 * @param authentication
	 * @return register
	 */
	@GetMapping(value = {"/register"})
	public String showRegisterUser(Model model,Authentication authentication) {		
	    User user = new User();
	    model.addAttribute("user", user); 
	    return "register";
	}
	
	/**
	 * Enregistrement du nouveau User en base 
	 * @param user
	 * @param model
	 * @return redirect:/login
	 */
	@PostMapping("/register")
    public String saveUser(@ModelAttribute("user") User user,Model model) {		
		user.setIdRole(role_repository.getIdByRole("utilisateur"));
		user_repository.save(user);
        return "redirect:/login";
    }
	
	/**
	 * Deconnexion de la session du User
	 * @param request
	 * @param response
	 * @return redirect:/login?logout
	 */
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout"; 
	}
	
	/**
	 * Création de l'objet message pour enregistrement
	 * @param model
	 * @param authentication
	 * @return user/chat
	 */
	@GetMapping(value = {"/user/chat/add"})
	public String showAddMessage(Model model,Authentication authentication) {		
	    Message message = new Message();
	    model.addAttribute("message", message); 
	    return "user/chat";
	}
	
	/**
	 * Enregistrement du message en base
	 * @param message
	 * @param model
	 * @return redirect:/user/chat
	 */
	@PostMapping("/user/chat/add")
    public String saveMessage(@ModelAttribute("message") Message message,Model model) {			
		message_repository.save(message);
        return "redirect:/user/chat";
    }
	
	/**
	 * Affichage de la page gestion des messages
	 * @param model
	 * @param authentication
	 * @return admin/gestion_message
	 */
	@GetMapping(value = {"/admin/gestion_message"})
	public String gestionMessage(Model model,Authentication authentication) {	
		model.addAttribute("username", authentication.getName());  
		model.addAttribute("message", message_repository.findAll());
	    return "admin/gestion_message";
	}
	
	/**
	 * Affichage de la page gestion des reports
	 * @param model
	 * @param authentication
	 * @return admin/gestion_report
	 */
	@GetMapping(value = {"/admin/gestion_report"})
	public String gestionReport(Model model,Authentication authentication) {	
		model.addAttribute("username", authentication.getName());  
		model.addAttribute("users1", user_repository.getUserByPseudo(authentication.getName()));
		model.addAttribute("user", user_repository.findAll());
		model.addAttribute("report", report_repository.findAll());
	    return "admin/gestion_report";
	}
	
	
	/**
	 * Affichage de la page pour ajouter un User depuis la page admin
	 * @param model
	 * @param authentication
	 * @return admin/add_user
	 */
	@GetMapping(value = {"/admin/add_user"})
	public String addUser(Model model,Authentication authentication) {	
		model.addAttribute("username", authentication.getName()); 
		 model.addAttribute("role", role_repository.findAll());
	    return "admin/add_user";
	}
	
	/**
	 * Affichage de page avec la liste des User
	 * @param model
	 * @param authentication
	 * @return admin/user_list
	 */ 
	@GetMapping(value = {"/admin/user_list"})
	public String user_list(Model model,Authentication authentication) {	
		model.addAttribute("username", authentication.getName()); 
		model.addAttribute("user", user_repository.findAll());
	    return "admin/user_list";
	}
	
	/**
	 * Suppression d'un message en fonction de son id
	 * @param idMessage
	 * @param model
	 * @return redirect:/admin/gestion_message
	 */
	@GetMapping("/admin/gestion_message/delete/{idMessage}")
    public String deleteMessage(@PathVariable("idMessage") int idMessage,Model model) {		
		Message message = message_repository.findById(idMessage);
		message_repository.delete(message);
        return "redirect:/admin/gestion_message";
    }
	
	/**
	 * Supression d'un utilisateur en fonction de son id
	 * @param idPseudo
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/user_list/delete/{idPseudo}")
    public String deleteUser(@PathVariable("idPseudo") int idPseudo,Model model) {		
		User user = user_repository.findById(idPseudo);
		user_repository.delete(user);
        return "redirect:/admin/user_list";
    }
	
	/**
	 * Creation de l'objet report 
	 * @param model
	 * @param authentication
	 * @return user/chat
	 */
	@GetMapping(value = {"user/chat/report"})
	public String reportMessage(Model model,Authentication authentication) {		
		Report report_send = new Report();
	    model.addAttribute("report_send", report_send); 
	    return "user/chat";
	}
	
	/**
	 * Enregistrement de l'objet report en base
	 * @param report_send
	 * @param model
	 * @return redirect:/user/chat
	 */
	@PostMapping("user/chat/report")
    public String saveReportMessage(@ModelAttribute("report_send") Report report_send,Model model) {			
		report_repository.save(report_send);
        return "redirect:/user/chat";
    }
	
	/**
	 * Edition et sauvegarge d'un message en base par un admin
	 * @param message
	 * @param model
	 * @return redirect:/admin/gestion_message
	 */
	@PostMapping("/admin/gestion_message/edit")
    public String updateMessage(@ModelAttribute("message") Message message,Model model) {	
		message_repository.save(message);
        return "redirect:/admin/gestion_message";
    }
	
	/**
	 * Affichage d'un message d'erreur sur la page login si connexion echoue
	 * @param model
	 * @return login
	 */
	@RequestMapping("/login-error")  
    public String loginError(Model model) {  
        model.addAttribute("loginError", true);  
        return "login";  
    }  
	
	/**
	 * Suppression d'un report et du message si report valider
	 * @param idGestion
	 * @param model
	 * @return redirect:/admin/gestion_report
	 */
	@GetMapping("/admin/gestion_report/delete/{idGestion}")
    public String deleteReportAndMessage(@PathVariable("idGestion") int idGestion,Model model) {		
		Report report = report_repository.findById(idGestion);
		Message m = message_repository.findById(report.getMessage().getIdMessage());
		message_repository.delete(m);
        return "redirect:/admin/gestion_report";
    }
	
	/**
	 * Supression du report mais pas du message car report refuser
	 * pour le message changement de l'attribut can_report a false
	 * (Un message ne peut erte repoter qu'une fois)
	 * @param idGestion
	 * @param model
	 * @return redirect:/admin/gestion_report
	 */
	@GetMapping("/admin/gestion_report/keep/{idGestion}")
    public String deleteReport(@PathVariable("idGestion") int idGestion,Model model) {		
		Report report = report_repository.findById(idGestion);
		Message m = message_repository.findById(report.getMessage().getIdMessage());
		m.setCanReport(false);
		message_repository.save(m);
		report_repository.deleteReport(idGestion);
        return "redirect:/admin/gestion_report";
    }

	/**
	 * Création de l'objet User pour ajout en base depuis la page admin
	 * @param model
	 * @return admin/user_list
	 */
	@GetMapping(value = {"/admin/add_user/add"})
	public String showAdminAddUser(Model model) {		
	    User user = new User();
	    model.addAttribute("user", user); 
	    return "admin/user_list";
	}
	
	/**
	 * Enregistrement d'un User depuis la page admin
	 * @param user
	 * @param model
	 * @return redirect:/admin/user_list
	 */
	@PostMapping("/admin/add_user/add")
    public String adminAddUser(@ModelAttribute("user") User user,Model model) {			
		user_repository.save(user);
        return "redirect:/admin/user_list";
    }
	
}
