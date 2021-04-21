package hh.fi.swd20.vertikaalikangas.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@PreAuthorize("hasRole('admin')")
	public String poistaLiike(@PathVariable("id") Long id, Model model) {
		liikerepository.deleteById(id);
		return "redirect:../liikelista";
	}
	
	@RequestMapping(value = "/liikes", method = RequestMethod.GET)
	public @ResponseBody List<Liike> liikeListRest() {
		return (List<Liike>) liikerepository.findAll();
	}
	
	@RequestMapping(value = "/liike/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Liike> findLiikeRest(@PathVariable("id") Long id) {
		return liikerepository.findById(id);
	}

}
