package SGQ.construct.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import SGQ.construct.model.Obra;
import SGQ.construct.model.Usuario;



public class UsuarioImpl implements UsuarioDao {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void save(Usuario usuario) {
	
		session.saveOrUpdate(usuario);
	}
	
	@Override
	public void deletar(Usuario usuario) {
		session.delete(usuario);
		
	}
	
	
	

	@Override
	public Usuario login(String usuario, String senha) {
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("status", 1));
        criteria.add(Restrictions.eq("usuario", usuario));
        criteria.add(Restrictions.eq("senha", senha));
        return (Usuario) criteria.uniqueResult();
	}
	
	@Override
	public ArrayList<Usuario> getUser() {
		Criteria criteria = session.createCriteria(Usuario.class);
		try {

			ArrayList<Usuario> listaUsuario = (ArrayList<Usuario>) criteria.addOrder(Order.asc("id")).list();
			
			return listaUsuario;
		} catch (Exception e) {
			System.out.println("sem registros");
			return null;
		}
	}
	
	@Override
	public Usuario getUserunq(int id) {
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("id", id));

		Usuario p = (Usuario) criteria.uniqueResult();

		return p;
	}

	@Override
	public ArrayList<Obra> getListObra(String[] numero) {
		
		Criteria criteria = session.createCriteria(Obra.class).add( Restrictions.in( "numero", numero ) );
		
		try {
			List<Obra> listaobra = criteria.list();
			
			
			return (ArrayList<Obra>) listaobra;
			
		} catch (Exception e) {
			System.out.println("sem registros");
			return null;
		}
	}
	
}