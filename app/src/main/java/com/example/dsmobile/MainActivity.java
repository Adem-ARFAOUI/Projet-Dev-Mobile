package com.example.dsmobile;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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