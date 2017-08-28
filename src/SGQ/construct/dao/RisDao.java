package SGQ.construct.dao;

import java.util.ArrayList;
import java.util.List;

import SGQ.construct.model.Itens;
import SGQ.construct.model.Its;
import SGQ.construct.model.RIS;
import SGQ.construct.model.Usuario;





public interface RisDao {

	public void salvar(RIS RIS);
	
	List<RIS> getRIS(String[] id);
	RIS getRISunq(int id);
	ArrayList<RIS> getRIS();

	Itens getItenuniq(int id);

	void deletar(RIS RIS);

	
	
	
}
