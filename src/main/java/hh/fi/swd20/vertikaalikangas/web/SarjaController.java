package hh.fi.swd20.vertikaalikangas.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.fi.swd20.vertikaalikangas.domain.LiikeRepository;
import hh.fi.swd20.vertikaalikangas.domain.Sarja;
import hh.fi.swd20.vertikaalikangas.domain.SarjaRepository;

@Controller
public class SarjaController {
	
	@Autowired
	private SarjaRepository sarjarepository;
	
	@Autowired
	private LiikeRepository liikerepository;
	
	
	//listaa sarjat
	@RequestMapping("/sarjalista")
	public String sarjalista(Model model) {
		model.addAttribute("sarjat", sarjarepository.findAll());
		model.addAttribute("liikkeet", liikerepository.findAll());
		return "sarjalista";
	}
	
	//lisää uusi sarja
	@RequestMapping(value = "/lisaasarja")
	public String lisaaSarja(Model model) {
			model.addAttribute("sarja", new Sarja());
			model.addAttribute("liikkeet", liikerepository.findAll());
	return "lisaaSarja";
	}
	
	//tallenna uusi sarja
	@RequestMapping(value = "/tallennasarja", method = RequestMethod.POST)
	public String save(Sarja sarja) {
		sarjarepository.save(sarja);
		return "redirect:sarjalista";
	}
	
	//muokkaa sarjaa
	@RequestMapping(value = "/muokkaasarjaa/{id}", method = RequestMethod.GET)
	public String muokkaaSarjaa(@PathVariable(value = "id") Long id, Model model) {
		model.addAttribute("sarja", sarjarepository.findById(id));
		//model.addAttribute("liike", liikerepository.findById(id));
		model.addAttribute("liikkeet", liikerepository.findAll());
		return "muokkaaSarjaa";
	}
	
	//poista sarja
	@RequestMapping(value = "/poistasarja/{id}", method = RequestMethod.GET) 
	public String poistaSarja(@PathVariable("id") Long id, Model model) {
		sarjarepository.deleteById(id);
		return "redirect:../sarjalista";
	}
	

}