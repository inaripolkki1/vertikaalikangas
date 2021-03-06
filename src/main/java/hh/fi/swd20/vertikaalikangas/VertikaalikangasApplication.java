package hh.fi.swd20.vertikaalikangas;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import hh.fi.swd20.vertikaalikangas.domain.Liike;
import hh.fi.swd20.vertikaalikangas.domain.LiikeRepository;
import hh.fi.swd20.vertikaalikangas.domain.Sarja;
import hh.fi.swd20.vertikaalikangas.domain.SarjaRepository;
import hh.fi.swd20.vertikaalikangas.domain.User;
import hh.fi.swd20.vertikaalikangas.domain.UserRepository;

@SpringBootApplication
@RestController
public class VertikaalikangasApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication
				.run(VertikaalikangasApplication.class, args);

		// manytomany kokeilua
		SarjaRepository sarjarepository = configurableApplicationContext.getBean(SarjaRepository.class);
		LiikeRepository liikerepository = configurableApplicationContext.getBean(LiikeRepository.class);

		Liike sammakko = new Liike("sammakko", "haarataitto", 1, 1.0, "pääsee ylösalaisin");
		Liike sakset = new Liike("sakset", "kankaan vieressä", 2, 1.0, null);
		Liike nilkkasolmu = new Liike("nilkkasolmu", "ranskalainen kiipeys", 1, 1.0, null);

		List<Liike> liikkeet = Arrays.asList(sammakko, sakset, nilkkasolmu);

		Sarja alkeisSarja = new Sarja("Alkeistason sarja");
		Sarja jatkoSarja = new Sarja("Jatkotason sarja");
		Sarja edistynytSarja = new Sarja("Edistyneiden sarja");

		List<Sarja> sarjat = Arrays.asList(alkeisSarja, jatkoSarja, edistynytSarja);

		sarjarepository.saveAll(sarjat);

		sammakko.luoSarja(alkeisSarja);
		sakset.luoSarja(alkeisSarja);
		nilkkasolmu.luoSarja(alkeisSarja);
		sammakko.luoSarja(jatkoSarja);
		sakset.luoSarja(edistynytSarja);

		liikerepository.saveAll(liikkeet);
	}

	private static final Logger log = LoggerFactory.getLogger(VertikaalikangasApplication.class);

	@Bean
	public CommandLineRunner demo(UserRepository userrepository) {
		return (args) -> {

			// Luo käyttäjät: admin/admini user/useri
			User user1 = new User("user", "$2a$10$D6XoktdshEWeNzPgqBXobuvoWp9nhQzN8DnW/Kp064fUbSLEtxnQ2", "USER");
			User user2 = new User("admin", "$2a$10$FLMjZo.xsWYbWIX/WKRcvumZ6ITgebKjpIqH5b7ro/OT8NijkLCca", "ADMIN");
			userrepository.save(user1);
			userrepository.save(user2);
			
			log.info("Hae käyttäjät");
			for (User user : userrepository.findAll()) {
			   log.info(user.toString());
			}

		};
	}

	/*
	 * MANY TO ONE TOIMIVA COMMAND LINE RUNNER private static final Logger log =
	 * LoggerFactory.getLogger(VertikaalikangasApplication.class);
	 * 
	 * @Bean public CommandLineRunner demo(LiikeRepository liikerepository,
	 * SarjaRepository sarjarepository) { return (args) -> {
	 * 
	 * Sarja alkeis = new Sarja("alkeis"); Sarja jatko = new Sarja("jatko"); Sarja
	 * edistynyt = new Sarja("edistynyt");
	 * 
	 * sarjarepository.save(alkeis); sarjarepository.save(jatko);
	 * sarjarepository.save(edistynyt);
	 * 
	 * //luo liikkeitä Liike sammakko = new Liike("sammakko", "haarataitto", 1, 1.0,
	 * "pääsee ylösalaisin", sarjarepository.findBySarjanNimi("alkeis").get(0));
	 * Liike sakset = new Liike("sakset", "kankaan vieressä", 2, 1.0, null,
	 * sarjarepository.findBySarjanNimi("alkeis").get(0)); Liike nilkkasolmu = new
	 * Liike("nilkkasolmu", "ranskalainen kiipeys", 1, 1.0, null,
	 * sarjarepository.findBySarjanNimi("alkeis").get(0));
	 * 
	 * liikerepository.save(sammakko); liikerepository.save(sakset);
	 * liikerepository.save(nilkkasolmu);
	 * 
	 * 
	 * 
	 * log.info("Nouda kaikki liikkeet"); for (Liike liike :
	 * liikerepository.findAll()) { log.info(liike.toString()); }
	 * 
	 * log.info("Nouda kaikki sarjat"); for (Sarja sarja :
	 * sarjarepository.findAll()) { log.info(sarja.toString()); } }; }
	 */
}
