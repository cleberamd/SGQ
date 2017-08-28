package SGQ.construct.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import SGQ.construct.model.Itens;

import SGQ.construct.model.RIS;
import SGQ.construct.model.Usuario;



public class RisImpl<E> implements RisDao {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(RIS RIS) {
		session.saveOrUpdate(RIS);
		
	}
	
	@Override
	public void deletar(RIS ris) {
		
		session.delete(ris);
		
		
	}

	@Override
	public ArrayList<RIS> getRIS() {
		Criteria criteria = session.createCriteria(RIS.class);
		criteria.add(Restrictions.eq("Ativa",true));
		try {
			ArrayList<RIS> listaRIS = (ArrayList<RIS>) criteria.addOrder(Order.desc("id")).list();

			return listaRIS;
		} catch (Exception e) {
			System.out.println("sem registros");
			return null;
		}
	}

	@Override
	public List<RIS> getRIS(String[] numero) {
		Criteria criteria = session.createCriteria(RIS.class).add( Restrictions.in( "numero", numero ) );
		
		try {
			List<RIS> listaris = criteria.list();
			return  listaris;
		} catch (Exception e) {
			System.out.println("sem registros");
			return null;
		}

	}

	
	
	@Override
	public RIS getRISunq(int id) {
		Criteria criteria = session.createCriteria(RIS.class);
		criteria.add(Restrictions.eq("id", id));

		RIS p = (RIS) criteria.uniqueResult();

		return p;
	}
	
	@Override
	public Itens getItenuniq(int id) {
		Criteria criteria = session.createCriteria(Itens.class);
		criteria.add(Restrictions.eq("id", id));

		Itens p = (Itens) criteria.uniqueResult();

		return p;
	}



	

	

}
