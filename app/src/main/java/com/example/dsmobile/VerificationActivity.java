package com.example.dsmobile;

<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
=======
import android.os.Bundle;
>>>>>>> origin/main

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

<<<<<<< HEAD
/**
 * Activité 2 : le scanner de paradoxe.
 * Elle affiche le résumé du voyageur sous forme de billet / passeport
 * et permet d'approuver ou de refuser le saut temporel.
 */
public class VerificationActivity extends AppCompatActivity {

    // Données reçues de l'Activité 1
    private String nom;
    private int anneeNaissance;
    private int anneeDestination;
    private String motif;

=======
public class VerificationActivity extends AppCompatActivity {

>>>>>>> origin/main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_verification);
<<<<<<< HEAD

        // Ajout d'une marge pour ne pas passer sous la barre d'état / de navigation
=======
>>>>>>> origin/main
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
<<<<<<< HEAD

        // Liaison avec les éléments du layout (IDs avec les initiales "sh")
        TextView tvValeurNomSh = findViewById(R.id.tv_valeur_nom_sh);
        TextView tvValeurAgeSh = findViewById(R.id.tv_valeur_age_sh);
        TextView tvValeurAneesSh = findViewById(R.id.tv_valeur_annees_sh);
        TextView tvValeurMotifSh = findViewById(R.id.tv_valeur_motif_sh);
        TextView tvAlerteSh = findViewById(R.id.tv_alerte_sh);
        Button btnApprouverSh = findViewById(R.id.btn_approuver_sh);
        Button btnRefuserSh = findViewById(R.id.btn_refuser_sh);

        // Récupération des données avec les clés construites par la méthode statique
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

        // Âge temporel = âge que le voyageur aura à l'année de destination
        int ageTemporel = anneeDestination - anneeNaissance;

        // Affichage du résumé sur le billet
        tvValeurNomSh.setText(nom);
        tvValeurAgeSh.setText(getString(R.string.valeur_age_temporel, ageTemporel));
        tvValeurAneesSh.setText(getString(R.string.valeur_naissance_destination,
                anneeNaissance, anneeDestination));
        tvValeurMotifSh.setText(motif);

        // Règle : motif "Modification du passé" ET différence d'années première
        // => le bouton Approuver est désactivé dès le chargement de l'activité
        boolean modificationDuPasse = motif.equals(getString(R.string.motif_modification));
        boolean risqueParadoxe = modificationDuPasse && estPremier(Math.abs(ageTemporel));

        if (risqueParadoxe) {
            btnApprouverSh.setEnabled(false);
            tvAlerteSh.setVisibility(View.VISIBLE);
        }

        // Clic sur "Approuver" : verdict = true
        btnApprouverSh.setOnClickListener(v -> envoyerVerdict(true));

        // Clic sur "Refuser" : verdict = false
        btnRefuserSh.setOnClickListener(v -> envoyerVerdict(false));
    }

    /**
     * Lance l'Activité 3 avec le verdict (booléen) et le nom du voyageur.
     */
    private void envoyerVerdict(boolean verdict) {
        Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
        intent.putExtra(Cles.genererCle(Cles.MATRICULE, Cles.VERDICT), verdict);
        intent.putExtra(Cles.genererCle(Cles.MATRICULE, Cles.NOM_VOYAGEUR), nom);
        startActivity(intent);
    }

    /**
     * Teste si un nombre est premier (divisions jusqu'à la racine carrée).
     * 0 et 1 ne sont pas premiers.
     */
    private boolean estPremier(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; (long) i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
=======
    }
}
>>>>>>> origin/main
