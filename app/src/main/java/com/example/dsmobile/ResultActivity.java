package com.example.dsmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 Activité 3 : le verdict.
 Le fond de l'écran yetbaddel selon le verdict reçu (vert = approuvé, rouge = refusé)
 w bch yaffichi message personnalisé avec le nom du voyageur.
 */
public class ResultActivity extends AppCompatActivity {
    // nfs l remarque mara o5ra.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // declaration mt3 variable racine elli mnou houa bch nbdel el background mt3 layout
        View racine = findViewById(R.id.main_result_ad);

        // kif kif bch njib les ids mt3 les élements xml bch n5dem behom (IDs avec les initiales "ad" pour Adem Arfaoui )
        TextView tvSymboleAd = findViewById(R.id.tv_symbole_ad);
        TextView tvTitreVerdictAd = findViewById(R.id.tv_titre_verdict_ad);
        TextView tvMessageAd = findViewById(R.id.tv_message_ad);
        Button btnNouveauAd = findViewById(R.id.btn_nouveau_ad);

        // Récupération des données mn activité 2 bel les clés elli fi methode statique genrerCle.
        Intent intent = getIntent();
        boolean approuve = intent.getBooleanExtra(Cles.genererCle(Cles.MATRICULE, Cles.VERDICT), false);
        String nom = intent.getStringExtra(Cles.genererCle(Cles.MATRICULE, Cles.NOM_VOYAGEUR));

        // Valeur par défaut si le nom est absent
        if (nom == null) {
            nom = getString(R.string.voyageur_inconnu);
        }

        // bch nbdel : Fond, symbole, titre et message selon le verdict
        if (approuve) {
            racine.setBackgroundColor(ContextCompat.getColor(this, R.color.verdict_approuve));
            tvSymboleAd.setText("✔");
            tvTitreVerdictAd.setText(R.string.titre_verdict_approuve);
            tvMessageAd.setText(getString(R.string.message_approuve, nom));
        } else {
            racine.setBackgroundColor(ContextCompat.getColor(this, R.color.verdict_refuse));
            tvSymboleAd.setText("!");
            tvTitreVerdictAd.setText(R.string.titre_verdict_refuse);
            tvMessageAd.setText(getString(R.string.message_refuse, nom));
        }

        //ki yclici sur "Nouveau voyageur" iraj3ou lel MainActivity
        btnNouveauAd.setOnClickListener(v -> nouveauVoyageur());
    }

    /**
     twika juste ki yarja3 lel l'Activité 1 isker toutes les activités précédentes.
     CLEAR_TASK tvidi la pile d'activités, NEW_TASK trelaci MainActivity
     */
    private void nouveauVoyageur() {
        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
