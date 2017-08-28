package SGQ.construct.dao;

import java.util.ArrayList;

import SGQ.construct.model.Its;
import SGQ.construct.model.RIS;






public interface ItsDao {

	public void salvar(Its its);
	
	Its getIts(RIS ris);
	
	Its getItsunq(int id);
	ArrayList<Its> getIts();

	void deletar(Its its);
	
}
