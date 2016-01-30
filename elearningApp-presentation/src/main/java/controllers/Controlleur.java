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
@SessionAttributes({ "cours_choisis", "chapitre_choisi" })
public class Controlleur {

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

	Logger logger = Logger.getLogger(Controlleur.class);

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

	@RequestMapping(value = "/prof/AccesPageajoutCours")
	public String welcome(Model model, HttpServletRequest req) {

		MyModel myModel = new MyModel();
		Gson gson = new Gson();
		String emailsJSON = gson.toJson(emails);
		myModel.setEmailsJSON(emailsJSON);
		System.out.println("voilàà "+ emailsJSON );

		myModel.setAllCategoriesNames(allCategoriesNames);
		myModel.setAllmotsCles(motCleNames);

		
		model.addAttribute("myModel", myModel);
		return "ajouterCours";
	}

	@RequestMapping(value = "/prof/voirTousLesCours")
	public String tousLesCours(Model model, HttpServletRequest req) {
		List<Cours> coursProf;
		coursProf = coursDAO.findByProf((User) req.getSession().getAttribute("user"));

		model.addAttribute("tousLesCours", coursProf);
		AjoutChapitreModel ajoutChapModel = new AjoutChapitreModel();
		model.addAttribute("ajoutChapModel", ajoutChapModel);
		return "allCoursesProf";
	}

	@RequestMapping(value = "/apprenant/voirCoursPubliques")
	public String tousLesCoursPubliques(Model model, HttpServletRequest req) {

		model.addAttribute("tousLesCours", tousLesCoursPubliques);
		AjoutChapitreModel ajoutChapModel = new AjoutChapitreModel();
		model.addAttribute("ajoutChapModel", ajoutChapModel);
		return "allCoursesProf";
	}

	@RequestMapping(value = "/prof/ajoutCours", method = RequestMethod.POST)
	public String ajoutCours(@ModelAttribute("myModel") @Valid MyModel myModel, BindingResult result, ModelMap model,
			HttpServletRequest req) throws Exception {
		if (result.hasErrors()) {

			myModel.setAllCategoriesNames(allCategoriesNames);
			myModel.setAllmotsCles(motCleNames);
			model.addAttribute("myModel", myModel);

			return "ajouterCours";
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
			
			//TODO trouver tous les Etudiants à partir de leurs emails et faire cours.etudiants = etudiants;
			String[] emailsList = myModel.getEmails().split(",");
			
			List<User> etudiants = new ArrayList<User>();
			for(int i =0; i<emailsList.length-1; i++){
				User etudiant  = userDAO.getUserByEmail(emailsList[i].trim());
				etudiants.add(etudiant);
			}
			
			//cours.setEtudiantsAutorises(etudiants);
			coursDAO.create(cours);
			tousLesCours.add(cours);
			
			
			for(int j = 0; j<etudiants.size(); j++){
				User etudiant = etudiants.get(j);
				etudiant.getCoursAutorises().add(cours);
				userDAO.edit(etudiant);
				
			}
			
			model.addAttribute("cours", cours);
			return "apresAjout";
		}
	}

	// ajouterChapitre
	@RequestMapping(value = "/ajouterChapitreForm", method = RequestMethod.GET)
	public String demandeAjoutChapitre(@RequestParam("cours") Long coursId, Model model) {

		model.addAttribute("cours_choisis", coursId);

		System.out.println("voilà  " + coursId);
		AjoutChapitreModel ajoutChapModel = new AjoutChapitreModel();
		model.addAttribute("ajoutChapModel", ajoutChapModel);
		return "ajouterChapitreForm";

	}

	@RequestMapping(value = "/prof/ajoutChapitre")
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

			return "allCoursesProf";
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
			return "allCoursesProf";
		}
	}

	@RequestMapping(value = "/prof/contenuCours")
	public String demandeVoirContenuCours(@RequestParam("cours") Long coursId, Model model) {

		model.addAttribute("cours_choisis", coursId);

		Cours cours = coursDAO.findById(coursId);
		Collection<Chapitre> chapitresCours = cours.getChapitres();
		model.addAttribute("cours", cours);
		model.addAttribute("chapitresCours", chapitresCours);
		return "contenuCours2";
	}
	
	@RequestMapping(value = "/apprenant/contenuCours")
	public String demandeVoirContenuCoursApprenant(@RequestParam("cours") Long coursId, Model model) {

		model.addAttribute("cours_choisis", coursId);

		Cours cours = coursDAO.findById(coursId);
		Collection<Chapitre> chapitresCours = cours.getChapitres();
		model.addAttribute("cours", cours);
		model.addAttribute("chapitresCours", chapitresCours);
		return "contenuCours2";
	}

	// ajouterLeconForm
	@RequestMapping(value = "/prof/ajouterLeconForm", method = RequestMethod.GET)
	public String demandeAjoutLecon(@RequestParam("chapitre") Long chapitreId, Model model) {

		model.addAttribute("chapitre_choisi", chapitreId);

		System.out.println("voilà  " + chapitreId);
		AjoutLeconModel ajoutLeconModel = new AjoutLeconModel();
		model.addAttribute("ajoutLeconModel", ajoutLeconModel);
		return "ajouterLeconForm";

	}

	@RequestMapping(value = "/prof/dbAjoutLecon")
	public String dbAjoutLecon(@ModelAttribute("ajoutLeconModel") @Valid AjoutLeconModel ajoutLeconModel,
			BindingResult result, ModelMap model) throws Exception {
		if (result.hasErrors()) {

			return "ajouterLeconForm";
		}

		else {

			long chpId = (Long) model.get("chapitre_choisi");
			Chapitre chapitre = chapitreDAO.findById(chpId);

			Lecon lecon = new Lecon(ajoutLeconModel.getName(), ajoutLeconModel.getLienVideo());

			// lecon.setContent(ajoutLeconModel.getContent());
			lecon.setChapitre(chapitre);
			leconDAO.create(lecon);

			model.addAttribute("messageAjoutLecon", "Lecon ajoutée avec succès");

			return "ajouterLeconForm";
		}
	}

	@RequestMapping(value = "/prof/contenuChapitre")
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

		return "contenuChapitre2";
	}

	@RequestMapping(value = "/contenuLecon")
	public String demandeVoirContenuLecon(@RequestParam("lecon") Long leconId, Model model) {
		Lecon lecon = leconDAO.findById(leconId);
		model.addAttribute("lecon", lecon);
		return "contenuLecon";
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

		return "modifierCours";
	}

	@RequestMapping(value = "/modifierCours", method = RequestMethod.POST)
	public String modifierCours(@ModelAttribute("myModel") @Valid ModifierCoursModel myModel, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {

			myModel.setAllCategoriesNames(allCategoriesNames);
			myModel.setAllmotsCles(motCleNames);
			model.addAttribute("myModel", myModel);

			return "modifierCours";
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
			return "tousLesCours";
		}
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

		return "modifierChapitre";
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

			return "tousLesCours";
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
			return "tousLesCours";
		}
	}

	@RequestMapping(value = "/prof/rendrePublique")
	public String rendrePublique(@RequestParam("cours") Long coursId, Model model) {

		model.addAttribute("cours_choisis", coursId);

		Cours cours = coursDAO.findById(coursId);
		cours.setStatus(StatusCours.PUBLIQUE);
		coursDAO.edit(cours);
		System.out.println("yahooooooooooooooooooooo");
		return "index";
	}
	
	
	@RequestMapping(value = "apprenant/voirTousLesCoursAutorises")
	public String tousLesCoursAutorises(Model model, HttpServletRequest req) {
		List<Cours> coursAutorises;
		User apprenant = (User) req.getSession().getAttribute("user");
		
		coursAutorises = (List<Cours>) apprenant.getCoursAutorises();

		model.addAttribute("tousLesCoursAutorises", coursAutorises);
		return "allAutorizedCoursesApprenant";
	}
	
	
	
	
	
	
	

}
