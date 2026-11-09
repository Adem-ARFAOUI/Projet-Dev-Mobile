package com.example.dsmobile;

/**
 * Classe utilitaire partagée par les trois activités.
 * Elle centralise la construction des clés utilisées dans les putExtra / getExtra.
 * Les clés ne sont jamais écrites en dur : elles sont toujours construites
 * à partir du matricule (4 derniers chiffres de la carte d'étudiant).
 */
public final class Cles {

    // 4 derniers chiffres de la carte d'étudiant d'un membre du groupe
    // TODO : remplacer "1234" par le vrai matricule avant le dépôt
    public static final String MATRICULE = "1234";

    // Parties variables des clés (le préfixe matricule est ajouté par genererCle)
    public static final String NOM_VOYAGEUR = "NOM_VOYAGEUR";
    public static final String ANNEE_NAISSANCE = "ANNEE_NAISSANCE";
    public static final String ANNEE_DESTINATION = "ANNEE_DESTINATION";
    public static final String MOTIF = "MOTIF";
    public static final String VERDICT = "VERDICT";

    // Constructeur privé : classe non instanciable
    private Cles() {
    }

    /**
     * Méthode statique exigée par l'énoncé.
     * Exemple : genererCle("1234", "NOM_VOYAGEUR") renvoie "1234_NOM_VOYAGEUR".
     */
    public static String genererCle(String matricule, String cleBase) {
        return matricule + "_" + cleBase;
    }
}
