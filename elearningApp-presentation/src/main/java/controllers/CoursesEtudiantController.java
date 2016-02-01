package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;

import dao.CategorieDAO;
import dao.ChapitreDAO;
import dao.CoursDAO;
import dao.LeconDAO;
import dao.MotCleDAO;
import dao.SingletonDAO;
import dao.UserDAO;
import dao.impl.UserDaoImpl;
import entities.CategorieCours;
import entities.Chapitre;
import entities.Cours;
import entities.Lecon;
import entities.MotCle;
import entities.StatusCours;
import entities.User;
import model.AjoutChapitreModel;
import model.AjoutLeconModel;
import model.ModifierChapitreModel;
import model.ModifierCoursModel;
import model.MyModel;

@Controller
@RequestMapping("/apprenant")
@SessionAttributes({ "cours_choisis", "chapitre_choisi" })
public class CoursesEtudiantController {

	private static CoursDAO coursDAO;
	private static ChapitreDAO chapitreDAO;
	private static LeconDAO leconDAO;
	private static CategorieDAO categorieDAO;
	private static MotCleDAO motCleDAO;
	private static UserDAO userDAO;

	private ArrayList<String> motCleNames;
	private List<String> allCategoriesNames;
	private List<Cours> tousLesCours;
	private List<Cours> tousLesCoursPubliques = new ArrayList<Cours>();
	private List<String> emails;

	Logger logger = Logger.getLogger(CoursesEtudiantController.class);

	@PostConstruct
	public void init() {

		method();
		PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("log4j.properties"));

	}

	public void method() {
		coursDAO = SingletonDAO.getCoursDAO();
		chapitreDAO = SingletonDAO.getChapitreDAO();
		leconDAO = SingletonDAO.getLeconDAO();
		categorieDAO = SingletonDAO.getCategoriedao();
		motCleDAO = SingletonDAO.getMotcledao();
		userDAO = SingletonDAO.getUserdao();
		motCleNames = (ArrayList<String>) motCleDAO.findAllMotsClesNames();
		allCategoriesNames = categorieDAO.findAllCategoriesNames();
		tousLesCours = (ArrayList<Cours>) coursDAO.findAll();
		for (Cours cours : tousLesCours) {
			if (cours.getStatus() == StatusCours.PUBLIQUE)
				tousLesCoursPubliques.add(cours);
		}

		emails = userDAO.findAllEmails();
	}

	@RequestMapping(value = "/voirCoursPubliques")
	public String tousLesCoursPubliques(Model model, HttpServletRequest req) {
		tousLesCours = (ArrayList<Cours>) coursDAO.findAll();
		List<Cours> publicCourses = new ArrayList<Cours>();
		for (Cours cours : tousLesCours) {
			if (cours.getStatus() == StatusCours.PUBLIQUE)
				cours.setDescription(cours.getDescription().substring(0, 100).concat("..."));
				publicCourses.add(cours);
		}

		model.addAttribute("tousLesCoursPubliques", publicCourses);
		return "apprenant/allPublicCourses";
	}

	@RequestMapping(value = "voirTousLesCoursAutorises")
	public String tousLesCoursAutorises(Model model, HttpServletRequest req) {
		List<Cours> coursAutorises;
		User apprenant = (User) req.getSession().getAttribute("user");

		coursAutorises = (List<Cours>) apprenant.getCoursAutorises();
		for (Cours cours : coursAutorises) {
			cours.setDescription(cours.getDescription().substring(0, 100).concat("..."));
		}

		model.addAttribute("tousLesCoursAutorises", coursAutorises);
		return "apprenant/allAutorizedCoursesApprenant";
	}

	@RequestMapping(value = "/apprenantContenuCours")
	public String demandeVoirContenuCoursApprenant(@RequestParam("cours") Long coursId, Model model) {

		model.addAttribute("cours_choisis", coursId);

		Cours cours = coursDAO.findById(coursId);
		Collection<Chapitre> chapitresCours = cours.getChapitres();
		model.addAttribute("cours", cours);
		model.addAttribute("chapitresCours", chapitresCours);
		return "apprenant/apprenantContenuCours";
	}

	@RequestMapping(value = "/contenuChapitre")
	public String demandeVoirContenuChapitre(@RequestParam("chapitre") Long chapId, ModelMap model) {
		Chapitre chapitre = chapitreDAO.findById(chapId);
		Collection<Lecon> leconsDuChapitre = chapitre.getLecons();
		model.addAttribute("chapitre_choisi", chapId);
		model.addAttribute("chapitre", chapitre);
		model.addAttribute("leconsDuChapitre", leconsDuChapitre);

		ModifierChapitreModel myModel = new ModifierChapitreModel();

		myModel.setOrdreDuChapitre(chapitre.getOrdreDuChapitre());
		myModel.setName(chapitre.getNom());
		myModel.setDescription(chapitre.getDescription());

		model.addAttribute("myModel", myModel);

		return "apprenant/apprenantContenuChapitre";
	}

	@RequestMapping(value = "/apprenantContenuLecon")
	public String demandeVoirContenuLecon(@RequestParam("lecon") Long leconId, Model model) {
		Lecon lecon = leconDAO.findById(leconId);
		model.addAttribute("lecon", lecon);
		return "apprenant/apprenantContenuLecon";
	}

}
