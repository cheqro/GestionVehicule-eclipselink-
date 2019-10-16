package jpa.vehiculeEclipseLink.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class VehiculeDaoJpaImpl implements VehiculeDao {

	private EntityManager em;

	public VehiculeDaoJpaImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	protected void finalize() throws Throwable {
		if ((this.em != null) && this.em.isOpen()) {
			try {
				this.em.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		super.finalize();
	}

	@Override
	public void insererVehicule(String _codeInterne, String _immatriculation, String _dateMiseEnCirculation) {

		EntityTransaction etx = null;
		try {
			// Initialiser une transaction JPA
			etx = this.em.getTransaction();
			etx.begin();
			// Construire l'objet Vehicule
			Vehicule vehicule = new Vehicule();
			vehicule.setCodeInterne(_codeInterne);
			vehicule.setImmatriculation(_immatriculation);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMdd");
			Date utildate = simpleDateFormat.parse(_dateMiseEnCirculation);
			vehicule.setDateMiseEnCirculation(utildate);
			// Insérer
			this.em.persist(vehicule);
			// Commiter la transaction JPA
			etx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (etx != null) {
				etx.rollback();
			}
		}

	}

	@Override
	public void insererVehicule(Vehicule _vehicule) {
		EntityTransaction etx = null;
		try {
			// Initialiser une transaction JPA
			etx = this.em.getTransaction();
			etx.begin();
			Vehicule vehicule = new Vehicule();
			// Construire l'objet Vehicule
			vehicule.setCodeInterne(_vehicule.getCodeInterne());
			vehicule.setImmatriculation(_vehicule.getImmatriculation());

			vehicule.setDateMiseEnCirculation(_vehicule.getDateMiseEnCirculation());
			// Insérer
			this.em.persist(vehicule);
			// Commiter la transaction JPA
			etx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (etx != null) {
				etx.rollback();
			}
		}

	}

	@Override
	public List<Vehicule> rechercherTousLesVehicules() {

		String queryString = "select * from Vehicule;";
		Query query = this.em.createNativeQuery(queryString, Vehicule.class);
		return query.getResultList();
	}

	@Override
	public Vehicule rechercherVehiculeParId(Long _id) {
		return this.em.find(Vehicule.class, _id);
	}

	@Override
	public List<Vehicule> rechercherVehiculeParImmatriculation(String _immatriculation) {

		String queryString = "select * from Vehicule where immatriculation=" + _immatriculation + ";";
		Query query = this.em.createNativeQuery(queryString, Vehicule.class);
		return query.getResultList();
	}

	@Override
	public void modifierVehiculeCodeInterneParId(Long _id, String _codeInterne) {

		Vehicule vehicule = this.em.find(Vehicule.class, _id);
		if (vehicule != null) {
			EntityTransaction etx = null;
			try {
				// Initialiser une transaction JPA
				etx = this.em.getTransaction();
				etx.begin();
				// Modifier l'entité
				// La modification ne nécessite pas d'instruction particulière
				vehicule.setCodeInterne(_codeInterne);
				// Commiter la transaction JPA
				etx.commit();
			} catch (Exception e) {
				e.printStackTrace();
				if (etx != null) {
					etx.rollback();
				}
			}
		}
	}

	@Override
	public void modifierVehiculeCodeInterneParId(Vehicule _vehicule) {
		EntityTransaction etx = null;
		try {
			// Initialiser une transaction JPA
			etx = this.em.getTransaction();
			etx.begin();
			// Pour modifier l'entité il faut la rattacher au contexte persisant
			this.em.merge(_vehicule);
			// Commiter la transaction JPA
			etx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (etx != null) {
				etx.rollback();
			}
		}
	}

	@Override
	public void supprimerVehiculeParId(Long _id) {

		EntityTransaction etx = null;
		try {
			// Initialiser une transaction JPA
			etx = this.em.getTransaction();
			etx.begin();
			// Pour modifier l'entité il faut la rattacher au contexte persisant
			Vehicule v = em.find(Vehicule.class, _id);
			em.remove(v);
			// Commiter la transaction JPA
			etx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (etx != null) {
				etx.rollback();
			}
		}
	}

	@Override
	public void supprimerVehiculeParId(Vehicule _vehicule) {
		EntityTransaction etx = null;
		try {
			// Initialiser une transaction JPA
			etx = this.em.getTransaction();
			etx.begin();
			// Pour modifier l'entité il faut la rattacher au contexte persisant

			Vehicule n_vehicule = em.find(Vehicule.class, _vehicule.getId());
			System.out.println("supprission du vehicule numéro :" + n_vehicule.getId());
			em.remove(n_vehicule);
			// Commiter la transaction JPA
			etx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (etx != null) {
				etx.rollback();
			}
		}
	}

}
