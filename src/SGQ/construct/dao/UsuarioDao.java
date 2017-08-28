package SGQ.construct.dao;

import java.util.ArrayList;

import SGQ.construct.model.Obra;
import SGQ.construct.model.Usuario;



public interface UsuarioDao {
	public void save(Usuario usuario);
	public Usuario login(String nome, String senha);
	public ArrayList<Usuario> getUser();
	public ArrayList<Obra> getListObra(String[] id);
	public Usuario getUserunq(int id);
	void deletar(Usuario usuario);
}