package com.example.dsmobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
