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
 Activité 1 : Bech nbdew nsn3ou el formulaire d'enregistrement mt3 voyageur.
 bch ta5ou les données saisies. w tab3thom lel ficher VerificationActivity elli hia activité 2
 en utilisant putExtra et un intent explicite.
 lenna 3mlt extends AppCompatActivity 5ter mnha hia bch njib les methodes : setContentView,findViewById,startActivity,getIntent w getString.
 w mn8irha , l classe MainActivity bch tbda keynha simple classe java manjem le nafichi layout le n3ml lpassage bin les interfaces bel Intent.
 */
public class MainActivity extends AppCompatActivity {

    // Kima 9olt fel énoncé Mr , el tasmiyet mt3 chaque élément d’interface (TextView, EditText, Button) lzm ikoun fehom les initiales mt3 we7ed mn lgrp.
    // Donc lenna 7atit "sh" comme quoi Syrine Hafsia :D.
    private EditText etNomSh, etAnneeNaissanceSh, etAnneeDestinationSh;
    private RadioGroup rgMotifSh;
    private Button btnInspecterSh;

    // twika bch n3ml creation mt3 interface bel methode onCreate elli jebtha men AppCompatActivity.
    // est3mlna @override bch n3abed ndéfini l methode 7asb l contenu mt3 l enoncé , w zeda st3mlt super.onCreate bch njib l version origine mb3ed nbdel feh kima n7ab.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Liaison bin les variables Java w les éléments du layout
        etNomSh = findViewById(R.id.et_nom_sh);
        etAnneeNaissanceSh = findViewById(R.id.et_annee_naissance_sh);
        etAnneeDestinationSh = findViewById(R.id.et_annee_destination_sh);
        rgMotifSh = findViewById(R.id.rg_motif_sh);
        btnInspecterSh = findViewById(R.id.btn_inspecter_sh);

        // ki y3ml Clic 3ala le bouton "Inspecter le profil" , bech yet3adda lel methode inspercterProfil() bch iverifi les informations
        //elli d5lhom l utilisateur.
        btnInspecterSh.setOnClickListener(v -> inspecterProfil());
    }

    private void inspecterProfil() {
        String nom = etNomSh.getText().toString().trim();
        String naissanceTxt = etAnneeNaissanceSh.getText().toString().trim();
        String destinationTxt = etAnneeDestinationSh.getText().toString().trim();

        // Réinitialisation des anciens messages d'erreur
        etNomSh.setError(null);
        etAnneeNaissanceSh.setError(null);
        etAnneeDestinationSh.setError(null);

        // le nom mlzmouch ikoun vide
        if (nom.isEmpty()) {
            etNomSh.setError(getString(R.string.erreur_champ_vide));
            etNomSh.requestFocus();
            return;
        }

        // l'année de naissance lzmha tkoun mn 4 chiffres
        if (naissanceTxt.length() != 4) {
            etAnneeNaissanceSh.setError(getString(R.string.erreur_annee_invalide));
            etAnneeNaissanceSh.requestFocus();
            return;
        }

        // kif kif :)
        if (destinationTxt.length() != 4) {
            etAnneeDestinationSh.setError(getString(R.string.erreur_annee_invalide));
            etAnneeDestinationSh.requestFocus();
            return;
        }

        // twika bch n7awel les deux années mn string l int bch mb3ed n7seb 9adeh l3mor mt3 l'utilisateur fi année destination
        int anneeNaissance = Integer.parseInt(naissanceTxt);
        int anneeDestination = Integer.parseInt(destinationTxt);

        // el voyageur malzmouch ikoun mwloud fel futur
        int anneeCourante = Calendar.getInstance().get(Calendar.YEAR);
        if (anneeNaissance > anneeCourante) {
            etAnneeNaissanceSh.setError(getString(R.string.erreur_naissance_futur));
            etAnneeNaissanceSh.requestFocus();
            return;
        }

        // Récupération lel motif choisit (par defaut le motif Tourisme choisit)
        RadioButton rbChoisi = findViewById(rgMotifSh.getCheckedRadioButtonId());
        String motif = rbChoisi.getText().toString();

        // création mt3 Intent explicite vers l'Activité 2
        Intent intent = new Intent(MainActivity.this, VerificationActivity.class);

        // bch nb3th les informations mn lactivité1 au les clés sont construites avec la méthode statique .
        intent.putExtra(Cles.genererCle(Cles.MATRICULE, Cles.NOM_VOYAGEUR), nom);
        intent.putExtra(Cles.genererCle(Cles.MATRICULE, Cles.ANNEE_NAISSANCE), anneeNaissance);
        intent.putExtra(Cles.genererCle(Cles.MATRICULE, Cles.ANNEE_DESTINATION), anneeDestination);
        intent.putExtra(Cles.genererCle(Cles.MATRICULE, Cles.MOTIF), motif);
        startActivity(intent);// mayetba3th chy ken ki yexecuti startActivity(intent)
    }
}
