package SGQ.construct.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


import SGQ.construct.model.Its;
import SGQ.construct.model.Obra;
import SGQ.construct.model.ObraRis;
import SGQ.construct.model.RIS;



public class ItsImpl<E> implements ItsDao {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Its its) {
		session.saveOrUpdate(its);
		
	}
	
	@Override
	public void deletar(Its its) {
		session.delete(its);
		
	}

	@Override
	public ArrayList<Its> getIts() {
		Criteria criteria = session.createCriteria(Its.class);
		try {
			ArrayList<Its> listaIts = (ArrayList<Its>) criteria.addOrder(Order.desc("id")).list();

			return listaIts;
		} catch (Exception e) {
			System.out.println("sem registros");
			return null;
		}
	}

	@Override
	public Its getIts(RIS ris) {
		Criteria criteria = session.createCriteria(Its.class);

		
		try {
			criteria.add(Restrictions.eq("ris",ris));
			Its it = (Its) criteria.addOrder(Order.desc("id")).list();
			return it;
		} catch (Exception e) {
			System.out.println("sem registros");
			return null;
		}

	}

	@Override
	public Its getItsunq(int id) {
		Criteria criteria = session.createCriteria(Its.class);
		criteria.add(Restrictions.eq("id", id));

		Its p = (Its) criteria.uniqueResult();

		return p;
	}

	

	

	

}
