package hh.fi.swd20.vertikaalikangas.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface LiikeRepository extends CrudRepository<Liike, Long> {

	List<Liike> findByNimi(String nimi);

}
