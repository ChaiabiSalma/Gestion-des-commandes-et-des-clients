package controle.jee.controleGCC.controller;

import controle.jee.controleGCC.model.Client;
import controle.jee.controleGCC.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//Les contrôleurs sont les classes qui gèrent les requêtes HTTP. Ils relient les vues (HTML/Thymeleaf) aux données de la base en passant par les entités et répertoires
@Controller
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CommandeRepository commandeRepository;

    @GetMapping("/clients")
    public String listClients(Model model) {
        try {
            logger.info("Tentative de récupération des clients");
            model.addAttribute("clients", clientRepository.findAll());
            logger.info("Clients récupérés avec succès");
            return "clients";
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des clients: ", e);
            model.addAttribute("errorMessage", "Erreur lors de la récupération des clients : " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/inscription")
    public String afficherFormulaireInscription() {
        return "inscription";
    }

    @PostMapping("/inscription")
    public String inscrireClient(@RequestParam String nom, @RequestParam String adresse, @RequestParam String email, Model model) {
        try {
            Client client = new Client();
            client.setNom(nom);
            client.setAdresse(adresse);
            client.setEmail(email);
            clientRepository.save(client);
            return "redirect:/clients";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Erreur lors de l'inscription du client : " + e.getMessage());
            return "inscription";
        }
    }

}

