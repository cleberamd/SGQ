package SGQ.construct.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import SGQ.construct.model.Obra;
import SGQ.construct.model.RIS;




public class ObraImpl<E> implements ObraDao {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Obra Obra) {
		session.saveOrUpdate(Obra);
	}
	
	@Override
	public void deletar(Obra Obra) {
		session.delete(Obra);
	}
	
	@Override
	public ArrayList<Obra> getObra() {
		Criteria criteria = session.createCriteria(Obra.class);
		try {
			ArrayList<Obra> listaObra = (ArrayList<Obra>) criteria.addOrder(Order.desc("id")).list();

			return listaObra;
		} catch (Exception e) {
			System.out.println("sem registros");
			return null;
		}
	}
	
	@Override
	public List<RIS> getListRIS(String[] numero) {
		Criteria criteria = session.createCriteria(RIS.class).
		add( Restrictions.in( "numero", numero ) );
	
		try {
			List<RIS> listaris = criteria.list();
			return (List<RIS>) listaris;
			
		} catch (Exception e) {
			System.out.println("sem registros");
			return null;
		}
	}

	@Override
	public Obra getObraunq(int id) {
		Criteria criteria = session.createCriteria(Obra.class);
		criteria.add(Restrictions.eq("id", id));

		Obra p = (Obra) criteria.uniqueResult();

		return p;
	}
	
	@Override
	public Obra getObraunq(RIS ris) {
		Criteria criteria = session.createCriteria(Obra.class);
		criteria.add(Restrictions.eq("RisList", ris.getId()));

		Obra p = (Obra) criteria.uniqueResult();

		return p;
	}

	

	

}
