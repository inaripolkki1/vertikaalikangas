package hh.fi.swd20.vertikaalikangas.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.fi.swd20.vertikaalikangas.domain.Liike;
import hh.fi.swd20.vertikaalikangas.domain.LiikeRepository;
import hh.fi.swd20.vertikaalikangas.domain.SarjaRepository;

@Controller
public class LiikeController {

	@Autowired
	private LiikeRepository liikerepository;

	@Autowired
	private SarjaRepository sarjarepository;

	// näytä liikelista
	@RequestMapping("/liikelista")
	public String liikelista(Model model) {
		model.addAttribute("liikkeet", liikerepository.findAll());
		model.addAttribute("sarjat", sarjarepository.findAll());
		return "liikelista";

	}

	// lisää uusi liike
	@RequestMapping("/lisaaliike")
	public String lisaaLiike(Model model) {
		model.addAttribute("liike", new Liike());
		model.addAttribute("sarjat", sarjarepository.findAll());
		return "lisaaLiike";
	}

	// tallenna uusi liike
	@RequestMapping(value = "/tallenna", method = RequestMethod.POST)
	public String save(Liike liike) {
		liikerepository.save(liike);
		return "redirect:liikelista";
	}

	// muokkaa liikettä
	@RequestMapping(value = "/muokkaaliiketta/{id}", method = RequestMethod.GET)
	public String muokkaaLiiketta(@PathVariable(value = "id") Long id, Model model) {
		model.addAttribute("liike", liikerepository.findById(id));
		model.addAttribute("sarja", sarjarepository.findById(id));
		return "muokkaaLiiketta";
	}

	// poista liike
	@RequestMapping(value = "/poistaliike/{id}", method = RequestMethod.GET)
	public String poistaLiike(@PathVariable("id") Long id, Model model) {
		liikerepository.deleteById(id);
		return "redirect:../liikelista";
	}

}
