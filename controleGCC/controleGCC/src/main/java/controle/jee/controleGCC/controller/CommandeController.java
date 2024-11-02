package controle.jee.controleGCC.controller;

import controle.jee.controleGCC.model.*;
import controle.jee.controleGCC.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.slf4j.*;

@Controller
public class CommandeController {

    private static final Logger logger = LoggerFactory.getLogger(CommandeController.class);

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping("/client/{clientId}/commandes")
    public String afficherCommandesClient(@PathVariable int clientId, Model model) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) {
            logger.warn("Client with id {} not found", clientId);
            return "redirect:/clients"; // Rediriger si le client n'existe pas
        }
        List<Commande> commandes = commandeRepository.findByClient(client);
        model.addAttribute("client", client);
        model.addAttribute("commandes", commandes);
        return "commandes";
    }

    @GetMapping("/commande/{commandeId}/produits")
    public String afficherProduitsCommande(@PathVariable int commandeId, Model model) {
        Commande commande = commandeRepository.findById(commandeId).orElse(null);
        if (commande == null) {
            logger.warn("Commande with id {} not found", commandeId);
            return "redirect:/erreur";
        }
        model.addAttribute("commande", commande);
        model.addAttribute("produits", commande.getProduits());
        return "produitsClients";
    }

    @GetMapping("/client/{clientId}/commande/nouvelle")
    public String afficherFormulaireNouvelleCommande(@PathVariable int clientId, Model model) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) {
            logger.warn("Client with id {} not found for new command", clientId);
            return "redirect:/clients";
        }
        List<Produit> produits = produitRepository.findAll();
        model.addAttribute("client", client);
        model.addAttribute("produits", produits);
        return "nouvelleCommande";
    }

    @PostMapping("/client/{clientId}/commande/nouvelle")
    public String ajouterNouvelleCommande(@PathVariable int clientId, @RequestParam("produitIds") List<Integer> produitIds, Model model) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) {
            logger.warn("Attempt to add a new command for non-existing client with id {}", clientId);
            return "redirect:/clients";
        }
        if (produitIds.isEmpty()) {
            logger.warn("No products selected for new command by client {}", clientId);
            model.addAttribute("error", "You must select at least one product.");
            return "nouvelleCommande";
        }
        Commande nouvelleCommande = new Commande();
        nouvelleCommande.setClient(client);
        nouvelleCommande.setDate(new Date());
        List<Produit> produits = produitRepository.findAllById(produitIds);
        double montantTotal = produits.stream().mapToDouble(Produit::getPrix).sum();
        nouvelleCommande.setMontantTotal(montantTotal);
        nouvelleCommande.setProduits(produits);
        commandeRepository.save(nouvelleCommande);
        logger.info("New command added for client {}: {}", clientId, nouvelleCommande);
        return "redirect:/client/" + clientId + "/commandes";
    }
}
