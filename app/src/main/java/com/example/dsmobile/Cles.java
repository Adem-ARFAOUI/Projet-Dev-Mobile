package com.example.dsmobile;

/**
 el classe hadhy bech tkoun partagé m3a les 3 activités.
 menha hia bch nasn3ou les clés elli bech nest3mlohom fi les putExtra w getExtra.
 */
public final class Cles {
    public static final String MATRICULE = "9281"; // maricule mt3 e5er 4 chiffres de carte étudiant mt3 Adem ARFAOUI :)
    // hadhouma clés variables (mouch fixe kima clé matricule elli yetzed bel methode genererCle)
    public static final String NOM_VOYAGEUR = "NOM_VOYAGEUR";
    public static final String ANNEE_NAISSANCE = "ANNEE_NAISSANCE";
    public static final String ANNEE_DESTINATION = "ANNEE_DESTINATION";
    public static final String MOTIF = "MOTIF";
    public static final String VERDICT = "VERDICT";

    // Hadheya l constructeur mt3 class cles , lzmou ikoun private 5ter maylzmch t3ml mnou instance depuis l'extérieur
    // Hadheka 3lh nest3ml fi méthode statique genererCle.
    private Cles() {
    }

    // création de methode genererCle()
    public static String genererCle(String matricule, String cleBase) {
        return matricule + "_" + cleBase;
    }
}
