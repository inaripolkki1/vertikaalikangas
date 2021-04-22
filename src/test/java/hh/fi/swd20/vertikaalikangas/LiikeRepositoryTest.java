package hh.fi.swd20.vertikaalikangas;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import hh.fi.swd20.vertikaalikangas.domain.Liike;
import hh.fi.swd20.vertikaalikangas.domain.LiikeRepository;

//@RunWith(SpringRunner.class)
@DataJpaTest
public class LiikeRepositoryTest {
	
	@Autowired
	private LiikeRepository liikerepository;
	
	@Test
	public void findByLiikkeenNimiShouldReturnLiike() {
		List<Liike> liikkeet = liikerepository.findByNimi("sammakko");
		
		assertThat(liikkeet).hasSize(1);
		assertThat(liikkeet.get(0).getAlkuasento()).isEqualTo("haarataitto");
	}
	
	@Test
	public void luoUusiLiike() {
		Liike liike = new Liike("tuplis", "sammakko", 3, 5.0, "sammakko ilmassa + dippi");
		liikerepository.save(liike);
		assertThat(liike.getId()).isNotNull();
	}

}
