package controle.jee.controleGCC.controller;

import controle.jee.controleGCC.model.Produit;
import controle.jee.controleGCC.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class ProduitController {

    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping("/produits")
    public String afficherProduits(Model model) {
        List<Produit> produits = produitRepository.findAll();
        model.addAttribute("produits", produits);
        return "produits"; // Assurez-vous que le modèle "produits" existe
    }

    @PostMapping("/produit/nouveau")
    public String ajouterProduit(@ModelAttribute Produit produit) {
        produitRepository.save(produit);
        return "redirect:/produits";
    }

    @GetMapping("/produit/nouveau")
    public String afficherFormulaireNouveauProduit() {
        return "nouveauProduit";
    }
}
