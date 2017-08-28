package SGQ.construct.dao.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import SGQ.construct.dao.utils.HibernateUtil;
import SGQ.construct.model.Obra;
import SGQ.construct.dao.ItensDao;
import SGQ.construct.dao.ItensImpl;
import SGQ.construct.dao.ItsDao;
import SGQ.construct.dao.ItsImpl;
import SGQ.construct.dao.ObraDao;
import SGQ.construct.dao.ObraImpl;
import SGQ.construct.dao.ObraRisDao;
import SGQ.construct.dao.ObraRisImpl;
import SGQ.construct.dao.PItemDao;
import SGQ.construct.dao.PItemImpl;
import SGQ.construct.dao.RisDao;
import SGQ.construct.dao.RisImpl;
import SGQ.construct.dao.UsuarioDao;
import SGQ.construct.dao.UsuarioImpl;

public class DAOFactory {
	public static UsuarioDao createUsuario() {
		UsuarioImpl usuarioImpl = new UsuarioImpl();
		usuarioImpl.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioImpl;
	}
	
	public static RisDao criarRis(){
		RisImpl impl = new RisImpl();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.getCurrentSession();
		impl.setSession(session);
		return impl;
	}
	
	public static ItensDao criarItens(){
		ItensImpl impl = new ItensImpl();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.getCurrentSession();
		impl.setSession(session);
		return impl;
	}

	public static PItemDao criarPItem(){
		PItemImpl impl = new PItemImpl();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.getCurrentSession();
		impl.setSession(session);
		return (PItemDao) impl;
	}
	
	public static ObraDao criarObra(){
		ObraImpl impl = new ObraImpl();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.getCurrentSession();
		impl.setSession(session);
		return impl;
	}
	
	public static ObraRisDao criarObraRis(){
		ObraRisImpl impl = new ObraRisImpl();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.getCurrentSession();
		impl.setSession(session);
		return impl;
	}
	
	public static ItsDao criarIts(){
		ItsImpl impl = new ItsImpl();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.getCurrentSession();
		impl.setSession(session);
		return (ItsDao) impl;
	}
}
