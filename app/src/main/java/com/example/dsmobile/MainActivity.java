package com.example.dsmobile;

<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
=======
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
>>>>>>> origin/main
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
<<<<<<< HEAD

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

/**
 * Activité 1 : formulaire d'enregistrement du voyageur.
 * Elle récupère les données saisies puis lance VerificationActivity
 * avec un Intent explicite.
 */
public class MainActivity extends AppCompatActivity {

    // Éléments de l'interface (les IDs contiennent les initiales "sh")
    private EditText etNomSh, etAnneeNaissanceSh, etAnneeDestinationSh;
    private RadioGroup rgMotifSh;
    private Button btnInspecterSh;
=======
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Déclaration des variables
    private EditText etNom, etAnneeNaissance, etAnneeDestination;
    private RadioGroup rgMotif;
    private Button btnInspecter;

    // Méthode statique exigée pour les clés de l'Intent
    public static String genererCle(String matricule, String cleBase) {
        return matricule + "_" + cleBase;
    }
>>>>>>> origin/main

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        // Liaison entre les variables Java et les éléments du layout
        etNomSh = findViewById(R.id.et_nom_sh);
        etAnneeNaissanceSh = findViewById(R.id.et_annee_naissance_sh);
        etAnneeDestinationSh = findViewById(R.id.et_annee_destination_sh);
        rgMotifSh = findViewById(R.id.rg_motif_sh);
        btnInspecterSh = findViewById(R.id.btn_inspecter_sh);

        // Clic sur le bouton "Inspecter le profil"
        btnInspecterSh.setOnClickListener(v -> inspecterProfil());
    }

    /**
     * Valide le formulaire puis envoie les données à l'Activité 2.
     */
    private void inspecterProfil() {
        String nom = etNomSh.getText().toString().trim();
        String naissanceTxt = etAnneeNaissanceSh.getText().toString().trim();
        String destinationTxt = etAnneeDestinationSh.getText().toString().trim();

        // Réinitialisation des anciens messages d'erreur
        etNomSh.setError(null);
        etAnneeNaissanceSh.setError(null);
        etAnneeDestinationSh.setError(null);

        // Vérification : le nom ne doit pas être vide
        if (nom.isEmpty()) {
            etNomSh.setError(getString(R.string.erreur_champ_vide));
            etNomSh.requestFocus();
            return;
        }

        // Vérification : l'année de naissance doit contenir 4 chiffres
        if (naissanceTxt.length() != 4) {
            etAnneeNaissanceSh.setError(getString(R.string.erreur_annee_invalide));
            etAnneeNaissanceSh.requestFocus();
            return;
        }

        // Vérification : l'année de destination doit contenir 4 chiffres
        if (destinationTxt.length() != 4) {
            etAnneeDestinationSh.setError(getString(R.string.erreur_annee_invalide));
            etAnneeDestinationSh.requestFocus();
            return;
        }

        // Conversion en entiers (sans risque : on a vérifié 4 chiffres, inputType=number)
        int anneeNaissance = Integer.parseInt(naissanceTxt);
        int anneeDestination = Integer.parseInt(destinationTxt);

        // Un voyageur ne peut pas être né dans le futur
        int anneeCourante = Calendar.getInstance().get(Calendar.YEAR);
        if (anneeNaissance > anneeCourante) {
            etAnneeNaissanceSh.setError(getString(R.string.erreur_naissance_futur));
            etAnneeNaissanceSh.requestFocus();
            return;
        }

        // Récupération du motif choisi (un bouton radio est coché par défaut)
        RadioButton rbChoisi = findViewById(rgMotifSh.getCheckedRadioButtonId());
        String motif = rbChoisi.getText().toString();

        // Intent explicite vers l'Activité 2
        Intent intent = new Intent(MainActivity.this, VerificationActivity.class);

        // Les clés sont construites avec la méthode statique (pas de clé écrite en dur)
        intent.putExtra(Cles.genererCle(Cles.MATRICULE, Cles.NOM_VOYAGEUR), nom);
        intent.putExtra(Cles.genererCle(Cles.MATRICULE, Cles.ANNEE_NAISSANCE), anneeNaissance);
        intent.putExtra(Cles.genererCle(Cles.MATRICULE, Cles.ANNEE_DESTINATION), anneeDestination);
        intent.putExtra(Cles.genererCle(Cles.MATRICULE, Cles.MOTIF), motif);

        startActivity(intent);
    }
}
=======
        // Liaison avec les éléments graphiques en utilisant les IDs avec "_sh"
        etNom = findViewById(R.id.et_nom_sh);
        etAnneeNaissance = findViewById(R.id.et_annee_naissance_sh);
        etAnneeDestination = findViewById(R.id.et_annee_destination_sh);
        rgMotif = findViewById(R.id.rg_motif_sh);
        btnInspecter = findViewById(R.id.btn_inspecter_sh);

        // Action lors du clic sur le bouton
        btnInspecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupération des textes saisis
                String nom = etNom.getText().toString();
                String anneeNaissanceStr = etAnneeNaissance.getText().toString();
                String anneeDestinationStr = etAnneeDestination.getText().toString();

                // Vérification que les champs ne sont pas vides
                if (nom.isEmpty() || anneeNaissanceStr.isEmpty() || anneeDestinationStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Récupération du motif sélectionné
                int selectedId = rgMotif.getCheckedRadioButtonId();
                RadioButton rbSelected = findViewById(selectedId);
                String motif = rbSelected.getText().toString();

                // Création de l'Intent explicite vers VerificationActivity
                Intent intent = new Intent(MainActivity.this, VerificationActivity.class);

                String matricule = "1234";

                // Envoi des données avec les clés générées dynamiquement
                intent.putExtra(genererCle(matricule, "NOM"), nom);
                intent.putExtra(genererCle(matricule, "ANNEE_NAISSANCE"), Integer.parseInt(anneeNaissanceStr));
                intent.putExtra(genererCle(matricule, "ANNEE_DESTINATION"), Integer.parseInt(anneeDestinationStr));
                intent.putExtra(genererCle(matricule, "MOTIF"), motif);

                // Lancement de l'Activité 2
                startActivity(intent);
            }
        });
    }
}
>>>>>>> origin/main
