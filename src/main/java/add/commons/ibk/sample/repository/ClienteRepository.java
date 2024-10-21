package add.commons.ibk.sample.repository;

import add.commons.ibk.sample.model.Cliente;
import add.commons.ibk.sample.dto.ClienteDTO;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends CosmosRepository<Cliente, String> {
    List<Cliente> findByNombre(String nombre);
    List<Cliente> findAllByOrderByApellidoAsc();

    @Query(value = "SELECT c.id, CONCAT(c.nombre,\" \",c.apellido, \" \", IS_DEFINED(c.apellidoMaterno) ? c.apellidoMaterno:'' ) as nombreCompleto\n" +
            "     from c order by c.nombreCompleto")
    List<ClienteDTO> getAllClientesSortedByNombre();



}
