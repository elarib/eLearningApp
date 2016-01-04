package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.SingletonDAO;
import dao.UserDAO;
import exception.ConfirmationException;

@Controller
public class ConfirmerCompte {
	
	
	@RequestMapping(value="/confirm")
	public String ConfirmerCompte(@RequestParam(value="email",required=false) String email, @RequestParam(value="token",required=false) String token) throws ConfirmationException
	{
		
		UserDAO dao = SingletonDAO.getUserdao();
		boolean result = dao.confirmInscription(email, token);
		System.out.println("email : "+email+" token = "+token);
		if(result)
		{
			return "ApprenantInscriptionReussiteView";
		}
		else
		{
			return "error";
		}
		
		
	}

}
