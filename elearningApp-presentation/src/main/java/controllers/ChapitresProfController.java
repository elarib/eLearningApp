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
@RequestMapping("/prof/cours/chapitres")
@SessionAttributes({ "cours_choisis", "chapitre_choisi" })
public class ChapitresProfController {

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

	Logger logger = Logger.getLogger(ChapitresProfController.class);

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


	// ajouterChapitre
	@RequestMapping(value = "/ajouterChapitreForm", method = RequestMethod.GET)
	public String demandeAjoutChapitre(@RequestParam("cours") Long coursId, Model model) {

		model.addAttribute("cours_choisis", coursId);

		System.out.println("voilà  " + coursId);
		AjoutChapitreModel ajoutChapModel = new AjoutChapitreModel();
		model.addAttribute("ajoutChapModel", ajoutChapModel);
		return "prof/ajouterChapitreForm";

	}

	@RequestMapping(value = "/ajoutChapitre")
	public String dbAjoutChapitre(@RequestParam("cours") Long coursId,
			@ModelAttribute("ajoutChapModel") @Valid AjoutChapitreModel ajoutChapModel, BindingResult result,
			ModelMap model) throws Exception {

		if (result.hasErrors()) {

			model.addAttribute("messageAjoutChapError", "le chapitre n'a pas pu être ajouté");

			model.addAttribute("tousLesCours", tousLesCours);
			AjoutChapitreModel ajoutChapModell = new AjoutChapitreModel();
			model.addAttribute("ajoutChapModel", ajoutChapModell);

			List<FieldError> errors = result.getFieldErrors();
			model.addAttribute("errors", errors);

			return "prof/allCoursesProf";
		}

		else {

			model.addAttribute("cours_choisis", coursId);

			long id = (Long) model.get("cours_choisis");

			Cours coursChoisis = coursDAO.findById(id);
			Chapitre chapitre = new Chapitre(ajoutChapModel.getOrdreDuChapitre(), ajoutChapModel.getName(),
					ajoutChapModel.getDescription());
			chapitre.setCours(coursChoisis);
			chapitreDAO.create(chapitre);

			model.addAttribute("messageAjoutChap", "chapitre ajouté avec succès");

			model.addAttribute("tousLesCours", tousLesCours);
			AjoutChapitreModel ajoutChapModell = new AjoutChapitreModel();
			model.addAttribute("ajoutChapModel", ajoutChapModell);
			return "prof/allCoursesProf";
		}
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

		return "prof/profContenuChapitre";
	}

	@RequestMapping(value = "/AccesPageModifierChapitre")
	public String demandeAccesPageModifierChapitre(ModelMap model) {
		long idChap = (Long) model.get("chapitre_choisi");
		Chapitre chapitre = chapitreDAO.findById(idChap);

		ModifierChapitreModel myModel = new ModifierChapitreModel();

		myModel.setOrdreDuChapitre(chapitre.getOrdreDuChapitre());
		myModel.setName(chapitre.getNom());
		myModel.setDescription(chapitre.getDescription());

		model.addAttribute("myModel", myModel);

		return "prof/modifierChapitre";
	}

	// modifierChapitre
	@RequestMapping(value = "/modifierChapitre")
	public String dbModifierChapitre(@ModelAttribute("myModel") @Valid ModifierChapitreModel modifierChapModel,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {

			model.addAttribute("messageAjoutChapError", "le chapitre n'a pas pu être ajouté");

			AjoutChapitreModel ajoutChapModell = new AjoutChapitreModel();
			model.addAttribute("ajoutChapModel", ajoutChapModell);
			model.addAttribute("tousLesCours", tousLesCours);

			List<FieldError> errors = result.getFieldErrors();
			model.addAttribute("errors", errors);

			return "prof/allCoursesProf";
		}

		else {

			long idChap = (Long) model.get("chapitre_choisi");
			Chapitre chapitre = chapitreDAO.findById(idChap);

			chapitre.setOrdreDuChapitre(modifierChapModel.getOrdreDuChapitre());
			chapitre.setNom(modifierChapModel.getName());
			chapitre.setDescription(modifierChapModel.getDescription());

			chapitreDAO.edit(chapitre);

			model.addAttribute("messageAjoutChap", "chapitre modifié avec succès");

			model.addAttribute("tousLesCours", tousLesCours);
			AjoutChapitreModel ajoutChapModell = new AjoutChapitreModel();
			model.addAttribute("ajoutChapModel", ajoutChapModell);
			return "prof/allCoursesProf";
		}
	}

}
