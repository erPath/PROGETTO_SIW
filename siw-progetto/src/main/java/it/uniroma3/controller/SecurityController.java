/*package it.uniroma3.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class SecurityController {
	
	//Da pagina qualsiasi -> Pagina Login
	@RequestMapping("/login")
	public String login(Model model, Authentication auth) {
		//Se voglio andare alla pagina di login, ma sono già loggato come responsabile 
		if(hasRole("RESPONSABILE")) {
			model.addAttribute("log", "Benvenuto, "+ auth.getName());
			return "/Admin/adminPanel";
		}
		else
		{
			//Se voglio andare alla pagina di login, ma sono già loggato come direttore
			if(hasRole("DIRETTORE")) {
				model.addAttribute("log", "Benvenuto, "+ auth.getName());
				return "/SuperAdmin/superadminPanel";
			}
			else
			{

		    	//Se voglio andare alla pagina di login, e non sono loggato
				return "login";
			}
		}
	}

	//Da pagina login -> pannelli amm 
	@RequestMapping("/panel")//(Entra qui solo se User/Pwd sono ok , vedi secConf)
	public String panel(Model model, Authentication auth)
	{
		model.addAttribute("log", "Benvenuto, "+auth.getName());
		if(hasRole("RESPONSABILE")) 
			return "/Admin/adminPanel";
		else
			return "/SuperAdmin/superAdminPanel";
	}
	@RequestMapping("/logout") //Da pannelli amm -> pagina login
	public String logout(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		if(authentication!= null)
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		
		return "redirect:/login";
	}
	
	//Metodo per verificare che si disponga di un certo ruolo "role"
	
	protected boolean hasRole(String role) {
		SecurityContext context= SecurityContextHolder.getContext();
		if(context== null)
		return false;
		
		Authentication authentication= context.getAuthentication();
		if(authentication== null)
			return false;
		
		for(GrantedAuthority auth: authentication.getAuthorities()) {
			if(role.equals(auth.getAuthority()))
				return true;
		}
		return false;
	}
}
*/