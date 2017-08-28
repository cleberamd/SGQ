package SGQ.construct.dao;

import java.util.ArrayList;

import SGQ.construct.model.Itens;
import SGQ.construct.model.RIS;






public interface ItensDao {

	public void salvar(Itens itens);
	
	ArrayList<Itens> getItens(RIS ris);
	Itens getItensunq(int id);
	ArrayList<Itens> getItens();

	void delete(Itens itens);
	
}
