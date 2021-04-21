package hh.fi.swd20.vertikaalikangas.domain;

import java.util.List;


import org.springframework.data.repository.CrudRepository;



public interface SarjaRepository extends CrudRepository<Sarja, Long> {

	List<Sarja> findBySarjanNimi(String sarjanNimi);

}
