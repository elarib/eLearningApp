package controllers;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.SingletonDAO;
import dao.UserDAO;
import entities.Role;
import entities.User;
import exception.EntityNotFoundException;

@Controller
@RequestMapping(value = "/profil")
public class Profil {

	@RequestMapping
	public String getProfil(HttpServletRequest request) throws EntityNotFoundException {
		// model.addAttribute("user", arg1)
		User user = SingletonDAO.getUserdao().getUserByUsername(request.getUserPrincipal().getName());
		request.getSession().setAttribute("user", user);
		Collection<Role> roles = user.getRoles();
		
		for(Role role : roles)
		{
			request.getSession().setAttribute("role", role.getRoleName().split("_")[1]);
			
		}


		return "Profil";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String afficherPageModifierInfos() {

		// model.addAttribute("user", arg1)

		return "EditProfil";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String modifierInfos(HttpServletRequest req,Model model) {

		// model.addAttribute("user", arg1)
		UserDAO dao = SingletonDAO.getUserdao();

		User user = (User) req.getSession().getAttribute("user");
		user.setNom(req.getParameter("nom"));
		user.setPrenom(req.getParameter("prenom"));
		if (req.getParameter("motdepasse") != null) {
			user.setMotDePasse(req.getParameter("motdepasse"));
		}
		// user.setDateNaissance(new Date(req.getParameter("nom")));

		dao.edit(user);
		model.addAttribute("good", "Good");
		return "EditProfil";
	}

}
