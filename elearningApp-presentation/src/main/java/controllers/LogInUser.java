package controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.SingletonDAO;
import dao.UserDAO;
import entities.Role;
import entities.User;
import exception.AuthenticationException;
import exception.EntityNotFoundException;
import services.SendMailService;
import validator.UserValidator;

@Controller
public class LogInUser {

	

	private static UserDAO userDao = SingletonDAO.getUserdao();

	
//	@RequestMapping(method = RequestMethod.GET)
	public String showNormalUserPage( HttpServletRequest req) {


		return "NormalUser";
	}
	
	


	@RequestMapping(value = "/login")
	public String Login(Model model, HttpServletRequest req) {

		
		if (req.getSession().getAttribute("user") != null)
			return "redirect:/profil";

		return "login";
	}
	
	@RequestMapping(value = "/login/{error}")
	public String Login(Model model, HttpServletRequest req,@PathVariable final String error) {

		model.addAttribute("error", error);
		return "login";
	}
	
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String Login(@RequestParam String username, @RequestParam String password, Model model,
//			HttpServletRequest request) throws AuthenticationException {
//
//		User user = userDao.authenticatePersonUsingUsername(username, password);
//		System.out.println(user);
//
//		if (user == null) {
//			return "error";
//		} else {
//
//			request.getSession().setAttribute("user", user);
//			return "redirect:/profil";
//
//		}
//
//	}

	@RequestMapping(value = "recupererMotdePasse", method = RequestMethod.GET)
	public String RecupererMotDePassePage() {
		return "recupererMotdePasse";
	}

	@RequestMapping(value = "recupererMotdePasse", method = RequestMethod.POST)
	public String RecupererMotDePasse(@RequestParam("email") String email,HttpServletRequest request) throws EntityNotFoundException {

		if (userDao.findEmail(email)) {
			new SendMailService().EnvoyerEmailRecuperationPassword(email,request);
			return "redirect:/recupererMotdePasse/succes";
		}

		return "error";

	}

	@RequestMapping(value = "recupererMotdePasse/succes")
	public String EmailEnvoyer(Model model) {

		model.addAttribute("source", this.getClass());
		model.addAttribute("traitement", "EmailEnvoyer de récuperation avec succés");

		return "reussi";

	}
	
	
	

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String getRegisterPage(@ModelAttribute("user") User user, Model model) {
		return "ApprenantRegisterView";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String getInfoUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model,
			HttpServletRequest request) {

		UserValidator userValidator = new UserValidator();
		userValidator.validate(user, result);

		String token = generateRandomToken();
		user.setToken(token);
		System.out.println("Token : " + user.getToken());
		request.setAttribute("user", user);

		if (result.hasErrors()) {
			System.out.println(result);
			// Si erreur, relancer la page de l'inscription
			return "ApprenantRegisterView";
		} else {
			/*
			 * Si n'est pas erreur, envoyer l'apprenant à la page de
			 * l'inscription réussite ( Dedans on envoie l'email de confirmation
			 * )
			 */

			System.out.println("Nice");
			return "forward:/signup/SendConfirmationEmailForNewApprenantController.do";
		}
	}

	private static String generateRandomToken() {
		char[] chars = "abcefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 35; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();

	}

	@RequestMapping(value = "/signup/SendConfirmationEmailForNewApprenantController.do", method = RequestMethod.POST)
	public String doSendEmail(HttpServletRequest request) throws Exception {

		// get les memes infos de l'utilisateur qui vient de s'inscrire
		User user = (User) request.getAttribute("user");

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date = new Date();

		user.setDateInscription(dateFormat.format(date));
		
		// Hasher le Mot de passe et Ajouter le Role d'apprenant
		System.out.println("Password : "+user.getMotDePasse()+" Hashed Password : "+new BCryptPasswordEncoder().encode(user.getMotDePasse()));
		user.setMotDePasse(new BCryptPasswordEncoder().encode(user.getMotDePasse()));
		Collection<Role> roles = new ArrayList<Role>();
		Role role =new Role("ROLE_APPRENANT");
		roles.add( role );
		user.setRoles(roles);
		SingletonDAO.getRoledao().create(role);
		System.out.println(user);
		try {
			userDao.create(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new AuthenticationException("Email ou Username est déjà inscrit ! "+e.getMessage());
		}
		// Send Confirmation Email
		sendConfirmationEmail(user,request);

		// forwards to the view named "Result"
		return "forward:/signup/inscription-reussite";
	}

	private boolean sendConfirmationEmail(User user,HttpServletRequest request) {

		System.out.println("From Method : " + user);

		new SendMailService().sendMailAction(user, request);

		System.out.println("success");

		return false;
	}
	
	
	
	@RequestMapping(value="/signup/inscription-reussite",method=RequestMethod.POST)
	public String sendConfirmationEmail(@Valid @ModelAttribute("user") User user, Model model,HttpServletRequest request)
	{
		// Send Confirmation email 
		
		boolean etat = sendConfirmationEmail(user,request);
		
		return "ApprenantInscriptionReussiteView";
		
	}

	
	
	@RequestMapping(value="/reenvoyerConfirmation",method=RequestMethod.GET)
	public String ResendConfirmationEmail()
	{
		
		
		return "resendConfirmation";
		
	}
	
	
	@RequestMapping(value="/reenvoyerConfirmation",method=RequestMethod.POST)
	public String ResendConfirmationEmail(@ModelAttribute("email") String email, Model model,HttpServletRequest request) throws EntityNotFoundException
	{
		User user = userDao.getUserByEmail(email);
		boolean etat = sendConfirmationEmail(user,request);

		model.addAttribute("source", this.getClass());
		model.addAttribute("traitement", "Email de confirmation réenvoyer avec succes");
		return "reussi";

		
	}
	
	
	
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String LogOut(Model model,HttpServletRequest req, HttpServletResponse response) 
	{
		
		   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    if (auth != null){    
		        new SecurityContextLogoutHandler().logout(req, response, auth);
		    }
		 
		model.addAttribute("logout", "Vous avez déconnecté avec succés !");
		return "login";
		
		
		
	}
	
	
	@RequestMapping(value="/403", method=RequestMethod.GET)
	public String error403(ModelMap model)
	{
		System.out.println("In the error 403 method");
		
		return "403";
	}
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String accueil()
	{	
		
		//model.addAttribute("user", arg1)
		
		return "accueil";
	}
	
	
	@RequestMapping(value="/error",method=RequestMethod.GET)
	public String errorPage()
	{	
		
		//model.addAttribute("user", arg1)
		
		return "error";
	}
	
	@RequestMapping(value = "/apropos")
	public String A (Model model, HttpServletRequest req) {

		
		

		return "apropos";
	}
	
	 private String getPrincipal(){
	        String userName = null;
	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 
	        if (principal instanceof UserDetails) {
	            userName = ((UserDetails)principal).getUsername();
	        } else {
	            userName = principal.toString();
	        }
	        return userName;
	    }
	
		
		

}
