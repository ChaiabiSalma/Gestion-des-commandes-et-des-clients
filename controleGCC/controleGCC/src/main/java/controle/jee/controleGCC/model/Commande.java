package controle.jee.controleGCC.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name="commande")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    private Double montantTotal;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "commande_produit",//Nom de la table d'association
            joinColumns = @JoinColumn(name = "commande_id"),//Colonne dans la table d'association (commande_produit) qui fait référence à la clé primaire de l'entité Commande.
            inverseJoinColumns = @JoinColumn(name = "produit_id")//Colonne dans la table d'association (commande_produit) qui fait référence à la clé primaire de l'entité Produit.
    )
    private List<Produit> produits;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(Double montantTotal) {
        this.montantTotal = montantTotal;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public Commande() {
        this.produits = new ArrayList<>();
    }

}

