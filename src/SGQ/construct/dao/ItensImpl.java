package SGQ.construct.dao;

import java.util.ArrayList;



import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import SGQ.construct.model.Itens;
import SGQ.construct.model.RIS;



public class ItensImpl<E> implements ItensDao {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Itens itens) {
		
			session.saveOrUpdate(itens);
	
	}
	
	@Override
	public void delete(Itens itens) {
		
			session.delete(itens);
	
	}

	@Override
	public ArrayList<Itens> getItens() {
		Criteria criteria = session.createCriteria(Itens.class);
		try {
			ArrayList<Itens> listaItens = (ArrayList<Itens>) criteria.addOrder(Order.desc("id")).list();

			return listaItens;
		} catch (Exception e) {
			System.out.println("sem registros");
			return null;
		}
	}

	@Override
	public ArrayList<Itens> getItens(RIS ris) {
		Criteria criteria = session.createCriteria(Itens.class);

		int i = ris.getId();
		try {
			criteria.add(Restrictions.eq("usuario.id", i));
			ArrayList<Itens> lista = (ArrayList<Itens>) criteria.addOrder(Order.desc("id")).list();
			return lista;
		} catch (Exception e) {
			System.out.println("sem registros");
			return null;
		}

	}

	@Override
	public Itens getItensunq(int id) {
		Criteria criteria = session.createCriteria(Itens.class);
		criteria.add(Restrictions.eq("id", id));

		Itens p = (Itens) criteria.uniqueResult();

		return p;
	}

	

	

}
