package SGQ.construct.dao;








import org.hibernate.Session;




import SGQ.construct.model.PItem;




public class PItemImpl<E> implements PItemDao {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	
	@Override
	public void salvar(PItem PItem) {
		session.saveOrUpdate(PItem);
		
	}
	

/*	@Override
	public  PItem getPItemunq(int id) {
		Criteria criteria = session.createCriteria(PItem.class);
		criteria.add(Restrictions.eq("id", id));

		 PItem p = criteria.uniqueResult();

		return p;
	}*/





	

	

	

}
