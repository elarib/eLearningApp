package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
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

import dao.CategorieDAO;
import dao.ChapitreDAO;
import dao.CoursDAO;
import dao.LeconDAO;
import dao.MotCleDAO;
import dao.SingletonDAO;
import entities.CategorieCours;
import entities.Chapitre;
import entities.Cours;
import entities.Lecon;
import entities.MotCle;
import entities.StatusCours;
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

	private ArrayList<String> motCleNames;
	private List<String> allCategoriesNames;
	private List<Cours> tousLesCours;

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
		motCleNames = (ArrayList<String>) motCleDAO.findAllMotsClesNames();
		allCategoriesNames = categorieDAO.findAllCategoriesNames();
		tousLesCours = (ArrayList<Cours>) coursDAO.findAll();
	}

	@RequestMapping(value = "/index")
	public String index(Model model) {

		return "index";
	}

	@RequestMapping(value = "/AccesPageajoutCours")
	public String welcome(Model model) {

		MyModel myModel = new MyModel();

		myModel.setAllCategoriesNames(allCategoriesNames);
		myModel.setAllmotsCles(motCleNames);

		model.addAttribute("myModel", myModel);
		return "ajouterCours";
	}

	@RequestMapping(value = "/voirTousLesCours")
	public String tousLesCours(Model model) {

		model.addAttribute("tousLesCours", tousLesCours);
		AjoutChapitreModel ajoutChapModel = new AjoutChapitreModel();
		model.addAttribute("ajoutChapModel", ajoutChapModel);
		return "tousLesCours";
	}

	@RequestMapping(value = "/ajoutCours", method = RequestMethod.POST)
	public String ajoutCours(@ModelAttribute("myModel") @Valid MyModel myModel, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {

			myModel.setAllCategoriesNames(allCategoriesNames);
			myModel.setAllmotsCles(motCleNames);
			model.addAttribute("myModel", myModel);

			return "ajouterCours";
		}

		else {

			Cours cours = new Cours();
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

			coursDAO.create(cours);
			tousLesCours.add(cours);
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

	@RequestMapping(value = "/ajoutChapitre")
	public String dbAjoutChapitre(@RequestParam("cours") Long coursId,
			@ModelAttribute("ajoutChapModel") @Valid AjoutChapitreModel ajoutChapModel, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {

			model.addAttribute("messageAjoutChapError", "le chapitre n'a pas pu être ajouté");

			model.addAttribute("tousLesCours", tousLesCours);
			AjoutChapitreModel ajoutChapModell = new AjoutChapitreModel();
			model.addAttribute("ajoutChapModel", ajoutChapModell);

			List<FieldError> errors = result.getFieldErrors();
			model.addAttribute("errors", errors);

			return "tousLesCours";
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
			return "tousLesCours";
		}
	}

	@RequestMapping(value = "/contenuCours")
	public String demandeVoirContenuCours(@RequestParam("cours") Long coursId, Model model) {
		
		model.addAttribute("cours_choisis", coursId);
		
		Cours cours = coursDAO.findById(coursId);
		Collection<Chapitre> chapitresCours = cours.getChapitres();
		model.addAttribute("cours", cours);
		model.addAttribute("chapitresCours", chapitresCours);
		return "contenuCours";
	}

	// ajouterLeconForm
	@RequestMapping(value = "/ajouterLeconForm", method = RequestMethod.GET)
	public String demandeAjoutLecon(@RequestParam("chapitre") Long chapitreId, Model model) {

		model.addAttribute("chapitre_choisi", chapitreId);

		System.out.println("voilà  " + chapitreId);
		AjoutLeconModel ajoutLeconModel = new AjoutLeconModel();
		model.addAttribute("ajoutLeconModel", ajoutLeconModel);
		return "ajouterLeconForm";

	}

	@RequestMapping(value = "/dbAjoutLecon")
	public String dbAjoutLecon(@ModelAttribute("ajoutLeconModel") @Valid AjoutLeconModel ajoutLeconModel,
			BindingResult result, ModelMap model) {
		if (result.hasErrors()) {

			return "ajouterLeconForm";
		}

		else {

			long chpId = (Long) model.get("chapitre_choisi");
			Chapitre chapitre = chapitreDAO.findById(chpId);

			Lecon lecon = new Lecon(ajoutLeconModel.getName(), ajoutLeconModel.getLienVideo());
			lecon.setContent(ajoutLeconModel.getContent());
			lecon.setChapitre(chapitre);
			leconDAO.create(lecon);

			model.addAttribute("messageAjoutLecon", "Lecon ajoutée avec succès");

			return "ajouterLeconForm";
		}
	}

	@RequestMapping(value = "/contenuChapitre")
	public String demandeVoirContenuChapitre(@RequestParam("chapitre") Long chapId, Model model) {
		Chapitre chapitre = chapitreDAO.findById(chapId);
		Collection<Lecon> leconsDuChapitre = chapitre.getLecons();
		model.addAttribute("chapitre_choisi", chapId);
		model.addAttribute("chapitre", chapitre);
		model.addAttribute("leconsDuChapitre", leconsDuChapitre);
		return "contenuChapitre";
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
			//tousLesCours.remove(cours);
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
			//tousLesCours.add(cours);
			
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

	
	
	//modifierChapitre
	@RequestMapping(value = "/modifierChapitre")
	public String dbModifierChapitre(@ModelAttribute("myModel") @Valid ModifierChapitreModel modifierChapModel, BindingResult result,
			ModelMap model) {

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
	

}
