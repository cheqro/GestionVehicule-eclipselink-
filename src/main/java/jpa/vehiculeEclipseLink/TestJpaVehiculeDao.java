package jpa.vehiculeEclipseLink;

import java.util.List;

import jpa.vehiculeEclipseLink.dao.Vehicule;
import jpa.vehiculeEclipseLink.dao.VehiculeDao;
import jpa.vehiculeEclipseLink.dao.VehiculeDaoFactory;

public class TestJpaVehiculeDao {

	public static void main(String[] args) {
		TestJpaVehiculeDao.testerVehicule(VehiculeDaoFactory.JPA_DAO);
	}

	private static void testerVehicule(String typeDao) {
		try {
			VehiculeDao vehiculeDao = VehiculeDaoFactory.getVehiculeDao(typeDao);

			// Test des méthodes
			/************* inserer Vehicule ****/

			// vehiculeDao.insererVehicule("A2017", "12-H-38779", "2018-10-24");
			// vehiculeDao.insererVehicule("A2018", "10-A-66759", "2019-06-25");
			// vehiculeDao.insererVehicule("A2019", "1-H-91226", "2018-12-26");

			/******** methode inserer vehicule *****/

			/*
			 * 
			 * Vehicule vv = new Vehicule(); vv.setCodeInterne("25");
			 * vv.setImmatriculation("555"); vv.setDateMiseEnCirculation(new Date());
			 * vehiculeDao.insererVehicule(vv);
			 */
			/*********** Q11 afficher tous les vehicules *********/

			List<Vehicule> listeTousLesVehicules = vehiculeDao.rechercherTousLesVehicules(); // Afficher les véhicule
																								// recherchés
			for (Vehicule vehicule : listeTousLesVehicules) {
				System.out.println("\n\t\t\t\t\t\t# - id : " + vehicule.getId().longValue() + "; codeInterne:"
						+ vehicule.getCodeInterne() + " ; immat:" + vehicule.getImmatriculation() + " ; dateMEC:"
						+ (vehicule.getDateMiseEnCirculation() + "."));
			}

			/********* Q12 Rechercher un véhicule par son id ************/
			/*
			 * Vehicule vehiculeParId = vehiculeDao.rechercherVehiculeParId(11L); // //
			 * Afficher le véhicule recherché (s'il n'existe pas il génère une exception)
			 * System.out.println("#- Vehicule recherché: id:" +
			 * vehiculeParId.getId().longValue() + "\n,codeInterne:" +
			 * vehiculeParId.getCodeInterne() + "\n, immat:" +
			 * vehiculeParId.getImmatriculation() + "\n, dateMEC:" +
			 * (vehiculeParId.getDateMiseEnCirculation()));
			 */

			/********* Q 13 recherche V par immatriculation **********/
			/*
			 * List<Vehicule> vehiculeParId =
			 * vehiculeDao.rechercherVehiculeParImmatriculation("10"); // // Afficher le //
			 * véhicule recherché (s'il n'existe pas il génère une exception)
			 * 
			 * System.out.println("\nil y a " + vehiculeParId.size() +
			 * " vehicule de cette immatriculation"); for (int i = 0; i <
			 * vehiculeParId.size(); i++) {
			 * 
			 * System.out.println("\n#- Vehicule recherché: id:" +
			 * vehiculeParId.get(i).getId() + "\n,codeInterne:" +
			 * vehiculeParId.get(i).getCodeInterne() + "\n, immat:" +
			 * vehiculeParId.get(i).getImmatriculation() + "\n, dateMEC:" +
			 * vehiculeParId.get(i).getDateMiseEnCirculation() + ""); }
			 */

			/***************** Q14 modifier *************/

			/* vehiculeDao.modifierVehiculeCodeInterneParId(14L, "kk25ss17"); */

			/************* Q15 modifier par objet vehicule *************/

			/*
			 * Vehicule vv = new Vehicule(); vv.setId(14L); vv.setCodeInterne("25");
			 * vv.setImmatriculation("B2988"); String date = "2020-01-02"; java.sql.Date
			 * sqlDate = java.sql.Date.valueOf(date); vv.setDateMiseEnCirculation(sqlDate);
			 * vehiculeDao.modifierVehiculeCodeInterneParId(vv);
			 */

			/**************** Q16 (A) supprimer un vehicule par id ***************/

			/* vehiculeDao.supprimerVehiculeParId(16L); */

			/****************** Q16 (B) *********************/

			/*
			 * Vehicule vv = new Vehicule(); vv.setId(4L); vv.setCodeInterne("10");
			 * vv.setImmatriculation("10");
			 */
			/*
			 * String date = "2020-01-02"; java.sql.Date SqlDate =
			 * java.sql.Date.valueOf(date);
			 * 
			 * vehiculeDao.supprimerVehiculeParId(new Vehicule(12L, "25", "B2988",
			 * SqlDate));
			 */

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}
}