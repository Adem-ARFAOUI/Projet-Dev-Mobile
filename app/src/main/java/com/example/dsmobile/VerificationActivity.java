package com.example.dsmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
/**
 Activité 2 : le scanner de paradoxe.
 Twika bch nafichiw le résumé du voyageur sous forme de billet wala passeport (Mr brsmy 7awelt njibha kima passeport ama ala8lb adheka li tl3li et merci :D )
 w mb3ed l voyageur ya5tar enou yapprouvi wala yrefusi le saut temporel.
 */
public class VerificationActivity extends AppCompatActivity {

    // Données reçues de l'Activité 1
    private String nom;
    private int anneeNaissance;
    private int anneeDestination;
    private String motif;
    // nfs remarque fi activity_main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // Twika bch norbtou binet layout xml w bch n7otou fehom les infos elli jeyin mn activité1
        // IDs avec les initiales "ia" pour dire Imen Abed
        // 5ter hia 5demt activité 2 .
        TextView tvValeurNomIa = findViewById(R.id.tv_valeur_nom_ia);
        TextView tvValeurAgeIa = findViewById(R.id.tv_valeur_age_ia);
        TextView tvValeurAneesIa = findViewById(R.id.tv_valeur_annees_ia);
        TextView tvValeurMotifIa = findViewById(R.id.tv_valeur_motif_ia);
        TextView tvAlerteIa = findViewById(R.id.tv_alerte_ia);
        Button btnApprouverIa = findViewById(R.id.btn_approuver_ia);
        Button btnRefuserIa = findViewById(R.id.btn_refuser_ia);

        // Récupération des données avec les clés construites par la méthode statique elli fi class cles.
        Intent intent = getIntent();
        nom = intent.getStringExtra(Cles.genererCle(Cles.MATRICULE, Cles.NOM_VOYAGEUR));
        anneeNaissance = intent.getIntExtra(Cles.genererCle(Cles.MATRICULE, Cles.ANNEE_NAISSANCE), 0);
        anneeDestination = intent.getIntExtra(Cles.genererCle(Cles.MATRICULE, Cles.ANNEE_DESTINATION), 0);
        motif = intent.getStringExtra(Cles.genererCle(Cles.MATRICULE, Cles.MOTIF));

        // Valeurs par défaut si une donnée est absente
        if (nom == null) {
            nom = getString(R.string.voyageur_inconnu);
        }
        if (motif == null) {
            motif = "";
        }

        // Âge temporel = âge que le voyageur aura - l'année de destination
        int ageTemporel = anneeDestination - anneeNaissance;

        // Affichage du résumé sur le billet
        tvValeurNomIa.setText(nom);
        tvValeurAgeIa.setText(getString(R.string.valeur_age_temporel, ageTemporel));
        tvValeurAneesIa.setText(getString(R.string.valeur_naissance_destination, anneeNaissance, anneeDestination));
        tvValeurMotifIa.setText(motif);


        // twika bch nchoufou ken la différence bin age  w années destination est un nb premier :
        // w9tha le bouton Approuver est désactivé dès le chargement de l'activité
        boolean modificationDuPasse = motif.equals(getString(R.string.motif_modification));
        boolean risqueParadoxe = modificationDuPasse && estPremier(Math.abs(ageTemporel));

        if (risqueParadoxe) {
            btnApprouverIa.setEnabled(false);
            tvAlerteIa.setVisibility(View.VISIBLE);
        }

        // ki yeclici 3ala bouton "approuvé" : verdict = true
        btnApprouverIa.setOnClickListener(v -> envoyerVerdict(true));

        // ki yeclici 3ala bouton "refusé" : verdict = false
        btnRefuserIa.setOnClickListener(v -> envoyerVerdict(false));
    }

    /**
     kif ma 3amlna fel activité 1 twika bch nb3thou l verdict w nom de voyageur lel activité 3
     à travers un Intent explicite.
     */
    private void envoyerVerdict(boolean verdict) {
        Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
        intent.putExtra(Cles.genererCle(Cles.MATRICULE, Cles.VERDICT), verdict);
        intent.putExtra(Cles.genererCle(Cles.MATRICULE, Cles.NOM_VOYAGEUR), nom);
        startActivity(intent);
    }

    // hadhy l fonction elli etasti si un nombre et premier ou pas
    // 0 w 1 ne sont mayet7sbouch par définition
    private boolean estPremier(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
