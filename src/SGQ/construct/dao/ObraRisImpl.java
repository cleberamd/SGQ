package SGQ.construct.dao;


import SGQ.construct.model.Obra;
import SGQ.construct.model.ObraRis;
import SGQ.construct.model.RIS;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class ObraRisImpl implements ObraRisDao {
	private Session session;
	
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(ObraRis obraris) {

		session.saveOrUpdate(obraris);
		
	}

	@Override
	public List<ObraRis> getObraRis(RIS ris) {
		Criteria criteria = session.createCriteria(ObraRis.class);
		criteria.add(Restrictions.eq("ris", ris));
		
		List<ObraRis> p = (List<ObraRis>) criteria.addOrder(Order.asc("id")).list();
		
		return p;
	}
	
	@Override
	public List<RIS> getListaRis(Obra obra) {
		Criteria criteria = session.createCriteria(ObraRis.class);
		criteria.add(Restrictions.eq("obra", obra));
		
		List<ObraRis> p = (List<ObraRis>) criteria.addOrder(Order.asc("id")).list();
		List<RIS> r =  new ArrayList();
		
		for (int i = 0; i < p.size(); i++) {
			if (p.get(i).getRis().isAtiva()) {
				 r.add(p.get(i).getRis());
			}
			
		}
		
		return r;
	}
	

	@Override
	public void deletar(List<ObraRis> ObraRis) {
		for (int i = 0; i < ObraRis.size(); i++) {
			
			session.delete(ObraRis.get(i));
		}
		
		
	}

}
