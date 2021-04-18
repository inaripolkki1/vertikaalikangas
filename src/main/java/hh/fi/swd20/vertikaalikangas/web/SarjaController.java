package hh.fi.swd20.vertikaalikangas.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.fi.swd20.vertikaalikangas.domain.Sarja;
import hh.fi.swd20.vertikaalikangas.domain.SarjaRepository;

@Controller
public class SarjaController {
	
	@Autowired
	private SarjaRepository sarjarepository;
	
	@RequestMapping("/sarjalista")
	public String sarjalista(Model model) {
		model.addAttribute("sarjat", sarjarepository.findAll());
		return "sarjalista";
	}
	
	//lisää uusi sarja
	@RequestMapping(value = "/lisaasarja")
	public String lisaaSarja(Model model) {
			model.addAttribute("sarja", new Sarja());
	return "lisaaSarja";
	}
	
	//tallenna uusi sarja
	@RequestMapping(value = "/tallennasarja", method = RequestMethod.POST)
	public String save(Sarja sarja) {
		sarjarepository.save(sarja);
		return "redirect:sarjalista";
	}
	
	//muokkaa sarjaa
	@RequestMapping(value = "/muokkaasarjaa/{sarja_id}", method = RequestMethod.GET)
	public String muokkaaSarjaa(@PathVariable(value = "sarja_id") Long sarja_id, Model model) {
		model.addAttribute("sarja", sarjarepository.findById(sarja_id));
		return "muokkaaSarjaa";
	}
	
	//poista sarja
	@RequestMapping(value = "/poistasarja/{id}", method = RequestMethod.GET) 
	public String poistaSarja(@PathVariable("id") Long sarja_id, Model model) {
		sarjarepository.deleteById(sarja_id);
		return "redirect:../sarjalista";
	}
	

}