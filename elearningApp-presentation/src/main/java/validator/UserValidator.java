package validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import entities.User;

public class UserValidator implements Validator {

	public boolean supports(Class<?> clazz) {

		return User.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {

		User user = (User) target;

		// Vérifier la confirmation du password !

		if (!(user.getMotDePasse()).equals(user.getMotDePasseConfirm())) {
			errors.rejectValue("motDePasseConfirm", "valid.motDePasseConfirm");
		}

		// Vérifier l'âge de l'utilisateur

		
		try {
			if (user.getDateNaissance() ==null) {
				errors.rejectValue("dateNaissance", "valid.dateNaissance");

			}
			long fifteenYearsToDays = 15 * 365;
			long diff = getDateDiffFromNow(user.getDateNaissance());

			if (diff < fifteenYearsToDays) {
				errors.rejectValue("dateNaissance", "valid.dateNaissance");

			}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			errors.rejectValue("dateNaissance", "valid.dateNaissance");
		}

	}

	/**
	 * Get a diff between two dates
	 * 
	 * @param userDateNaissance
	 *            the oldest date
	 * @param date2
	 *            the newest date
	 * @param timeUnit
	 *            the unit in which you want the diff
	 * @return the diff value, in the provided unit
	 */
	public static long getDateDiffFromNow(Date userDateNaissance) throws NullPointerException{

		
			// convertir le String userDateNaisse à une Date

			Date dateDeNaissance = (userDateNaissance);

			// Get current Date
			Calendar now = Calendar.getInstance();
			Date nowDate = now.getTime();

			// Calculer la différence

			long diff = nowDate.getTime() - dateDeNaissance.getTime();
			// return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			return diff;
		

	}

}
