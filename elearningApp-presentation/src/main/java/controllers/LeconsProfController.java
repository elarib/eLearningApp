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
@RequestMapping("/prof/cours/chapitres/lecons")
@SessionAttributes({ "cours_choisis", "chapitre_choisi" })
public class LeconsProfController {

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

	Logger logger = Logger.getLogger(LeconsProfController.class);

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


	@RequestMapping(value = "/ajouterLeconForm", method = RequestMethod.GET)
	public String demandeAjoutLecon(@RequestParam("chapitre") Long chapitreId, Model model) {

		model.addAttribute("chapitre_choisi", chapitreId);

		System.out.println("voilà  " + chapitreId);
		AjoutLeconModel ajoutLeconModel = new AjoutLeconModel();
		model.addAttribute("ajoutLeconModel", ajoutLeconModel);
		return "prof/ajouterLeconForm";

	}

	@RequestMapping(value = "/dbAjoutLecon")
	public String dbAjoutLecon(@ModelAttribute("ajoutLeconModel") @Valid AjoutLeconModel ajoutLeconModel,
			BindingResult result, ModelMap model) throws Exception {
		if (result.hasErrors()) {

			return "prof/ajouterLeconForm";
		}

		else {

			long chpId = (Long) model.get("chapitre_choisi");
			Chapitre chapitre = chapitreDAO.findById(chpId);

			Lecon lecon = new Lecon(ajoutLeconModel.getName(), ajoutLeconModel.getLienVideo(), ajoutLeconModel.getContent());

			// lecon.setContent(ajoutLeconModel.getContent());
			lecon.setChapitre(chapitre);
			leconDAO.create(lecon);

			model.addAttribute("messageAjoutLecon", "Lecon ajoutée avec succès");

			return "prof/ajouterLeconForm";
		}
	}

	@RequestMapping(value = "/contenuLecon")
	public String demandeVoirContenuLecon(@RequestParam("lecon") Long leconId, Model model) {
		Lecon lecon = leconDAO.findById(leconId);
		model.addAttribute("lecon", lecon);
		return "prof/contenuLecon";
	}

}
