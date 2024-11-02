package controle.jee.controleGCC.repository;

import controle.jee.controleGCC.model.Client;
import controle.jee.controleGCC.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    List<Commande> findByClient(Client client);

}

