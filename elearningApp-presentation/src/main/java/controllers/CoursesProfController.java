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
@RequestMapping("/prof/cours")
@SessionAttributes({ "cours_choisis", "chapitre_choisi" })
public class CoursesProfController {

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

	Logger logger = Logger.getLogger(CoursesProfController.class);

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

	@RequestMapping(value = "/index")
	public String index(Model model, HttpServletRequest request) {

		System.out.println(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath());
		return "index";
	}

	@RequestMapping(value = "/AccesPageajoutCours")
	public String welcome(Model model, HttpServletRequest req) {

		MyModel myModel = new MyModel();

		Gson gson = new Gson();
		String emailsJSON = gson.toJson(emails);

		myModel.setEmailsJSON(emailsJSON);
		myModel.setAllCategoriesNames(allCategoriesNames);
		myModel.setAllmotsCles(motCleNames);

		model.addAttribute("myModel", myModel);
		return "prof/ajouterCours";
	}

	@RequestMapping(value = "/ajoutCours", method = RequestMethod.POST)
	public String ajoutCours(@ModelAttribute("myModel") @Valid MyModel myModel, BindingResult result, ModelMap model,
			HttpServletRequest req) throws Exception {
		if (result.hasErrors()) {

			myModel.setAllCategoriesNames(allCategoriesNames);
			myModel.setAllmotsCles(motCleNames);
			model.addAttribute("myModel", myModel);

			return "prof/ajouterCours";
		}

		else {
			User prof = (User) req.getSession().getAttribute("user");

			Cours cours = new Cours();
			cours.setProf(prof);

			CategorieCours cat;
			cat = categorieDAO.findByName(myModel.getCategorieName());
			cours.setCategorie(cat);
			cours.setDateAjout(new Date());
			cours.setStatus(StatusCours.PRIVE);
			cours.setName(myModel.getName());

			String descriptionOutput = myModel.getDescription().replace("\n", "<br />\n");
			cours.setDescription(descriptionOutput);

			ArrayList<String> motsClesNamesChoisis = myModel.getMotsClesChoisis();
			ArrayList<MotCle> motsClesChoisis = new ArrayList<MotCle>();
			Iterator<String> it3 = motsClesNamesChoisis.iterator();
			while (it3.hasNext()) {
				String motCleName = it3.next();
				MotCle motCle = motCleDAO.findByName(motCleName);
				motsClesChoisis.add(motCle);
			}
			cours.setMotsCles(motsClesChoisis);

			String prerequisOutput1 = myModel.getPrerequis().replace("\n", "<br />\n" + "* ");
			String prerequisOutput2 = "* " + prerequisOutput1;
			cours.setPrerequis(prerequisOutput2);

			String objectifsOutput1 = myModel.getObjectifs().replace("\n", "<br />\n" + "* ");
			String objectifsOutput2 = "* " + objectifsOutput1;
			cours.setObjectifs(objectifsOutput2);

			// TODO trouver tous les Etudiants à partir de leurs emails et faire
			// cours.etudiants = etudiants;
			String[] emailsList = myModel.getEmails().split(",");

			List<User> etudiants = new ArrayList<User>();
			for (int i = 0; i < emailsList.length - 1; i++) {
				User etudiant = userDAO.getUserByEmail(emailsList[i].trim());
				etudiants.add(etudiant);
			}

			// cours.setEtudiantsAutorises(etudiants);
			coursDAO.create(cours);
			tousLesCours.add(cours);

			for (int j = 0; j < etudiants.size(); j++) {
				User etudiant = etudiants.get(j);
				etudiant.getCoursAutorises().add(cours);
				userDAO.edit(etudiant);

			}

			model.addAttribute("cours", cours);
			return "prof/apresAjout";
		}
	}

	/** voir les cours ajoutés par le prof */
	@RequestMapping(value = "/voirTousLesCours")
	public String tousLesCours(Model model, HttpServletRequest req) {
		List<Cours> coursProf;
		coursProf = coursDAO.findByProf((User) req.getSession().getAttribute("user"));
		for (Cours cours : coursProf) {
			cours.setDescription(cours.getDescription().substring(0, 2).concat("..."));
		}

		model.addAttribute("tousLesCours", coursProf);
		AjoutChapitreModel ajoutChapModel = new AjoutChapitreModel();
		model.addAttribute("ajoutChapModel", ajoutChapModel);
		return "prof/allCoursesProf";
	}

	@RequestMapping(value = "/contenuCours")
	public String demandeVoirContenuCours(@RequestParam("cours") Long coursId, Model model) {

		model.addAttribute("cours_choisis", coursId);

		Cours cours = coursDAO.findById(coursId);
		Collection<Chapitre> chapitresCours = cours.getChapitres();
		model.addAttribute("cours", cours);
		model.addAttribute("chapitresCours", chapitresCours);
		return "prof/profContenuCours";
	}

	// Modification du cours

	@RequestMapping(value = "/AccesPageModifierCours")
	public String demandeAccesPageModifierCours(ModelMap model) {
		long idCours = (Long) model.get("cours_choisis");
		Cours cours = coursDAO.findById(idCours);

		ModifierCoursModel myModel = new ModifierCoursModel();
		myModel.setName(cours.getName());
		myModel.setDescription(cours.getDescription());
		myModel.setPrerequis(cours.getObjectifs());
		myModel.setObjectifs(cours.getObjectifs());
		myModel.setAllCategoriesNames(allCategoriesNames);
		myModel.setAllmotsCles(motCleNames);

		model.addAttribute("myModel", myModel);

		return "prof/modifierCours";
	}

	@RequestMapping(value = "/modifierCours", method = RequestMethod.POST)
	public String modifierCours(@ModelAttribute("myModel") @Valid ModifierCoursModel myModel, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {

			myModel.setAllCategoriesNames(allCategoriesNames);
			myModel.setAllmotsCles(motCleNames);
			model.addAttribute("myModel", myModel);

			return "prof/modifierCours";
		}

		else {

			long id = (Long) model.get("cours_choisis");
			Cours cours = coursDAO.findById(id);
			// tousLesCours.remove(cours);
			CategorieCours cat;
			cat = categorieDAO.findByName(myModel.getCategorieName());
			cours.setCategorie(cat);
			cours.setStatus(StatusCours.PRIVE);
			cours.setName(myModel.getName());

			String descriptionOutput = myModel.getDescription().replace("\n", "<br />\n");
			cours.setDescription(descriptionOutput);

			ArrayList<String> motsClesNamesChoisis = myModel.getMotsClesChoisis();
			ArrayList<MotCle> motsClesChoisis = new ArrayList<MotCle>();
			Iterator<String> it3 = motsClesNamesChoisis.iterator();
			while (it3.hasNext()) {
				String motCleName = it3.next();
				MotCle motCle = motCleDAO.findByName(motCleName);
				motsClesChoisis.add(motCle);
			}
			cours.setMotsCles(motsClesChoisis);

			String prerequisOutput1 = myModel.getPrerequis().replace("\n", "<br />\n" + "* ");
			String prerequisOutput2 = "* " + prerequisOutput1;
			cours.setPrerequis(prerequisOutput2);

			String objectifsOutput1 = myModel.getObjectifs().replace("\n", "<br />\n" + "* ");
			String objectifsOutput2 = "* " + objectifsOutput1;
			cours.setObjectifs(objectifsOutput2);

			coursDAO.edit(cours);
			// tousLesCours.add(cours);

			tousLesCours = (ArrayList<Cours>) coursDAO.findAll();

			model.addAttribute("cours", cours);

			model.addAttribute("tousLesCours", tousLesCours);
			AjoutChapitreModel ajoutChapModel = new AjoutChapitreModel();
			model.addAttribute("ajoutChapModel", ajoutChapModel);
			return "prof/allCoursesProf";
		}
	}

	@RequestMapping(value = "/rendrePublique")
	public String rendrePublique(@RequestParam("cours") Long coursId, Model model) {

		model.addAttribute("cours_choisis", coursId);

		Cours cours = coursDAO.findById(coursId);
		cours.setStatus(StatusCours.PUBLIQUE);
		coursDAO.edit(cours);
		return "index";
	}

}
