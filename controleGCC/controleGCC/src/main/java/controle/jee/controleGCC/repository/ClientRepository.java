package controle.jee.controleGCC.repository;

import controle.jee.controleGCC.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// permettant ainsi de gérer les opérations CRUD (Create, Read, Update, Delete) automatiquement, sans écrire de SQL explicite

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}

