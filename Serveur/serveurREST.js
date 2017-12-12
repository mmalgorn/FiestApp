"use strict";
var http = require('http');
var url = require('url');
var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var cors = require('cors');
var { Soiree } = require('./soiree.js');
var MongoClient = require("mongodb").MongoClient;
var { Participant } = require('./participant.js');
var { Soiree } = require('./soiree.js');
var assert = require('assert');
var url = 'mongodb://localhost:27017/fiestapp';
var ObjectId = require('mongodb').ObjectID;


Array.prototype.remove = function(from, to) {
  var rest = this.slice((to || from) + 1 || this.length);
  this.length = from < 0 ? this.length + from : from;
  return this.push.apply(this, rest);
};

exports.lancerServeur=function(dataBase){

  app.use(cors());
  app.use(bodyParser.json()); // support json encoded bodies
  app.use(bodyParser.urlencoded({ extended: true })); // support encoded bodiesvar bodyParser = require('body-parser');
  app.use(bodyParser.json()); // support json encoded bodies
  app.use(bodyParser.urlencoded({ extended: true })); // support encoded bodies
  this.base

  //LANCEMENT DU SERVEUR
  var server = app.listen(3000, function () {
    var host = server.address().address;
    var port = server.address().port;
    console.log("Serveur en ecoute sur http://%s:%s", host, port);
  });

  //CONNECTION A LA BASE DE DONNEES
  MongoClient.connect(url, function(err, db) {

    assert.equal(null, err);

    console.log("Connected correctly to server.");



    app.post('/Inscription', function(req, res){

      //var participant = req.body.part;
      var participant = new Participant(req.body.part_id, 'Preparation');
      participant.nom = req.body.part_nom;
      participant.prenom = req.body.part_prenom;

      //dataBase.insertUser(db,part, function(err) {
      dataBase.insertUser(db,participant, function(err) {
        console.log(err);
      });
      console

      dataBase.findUser(db,participant.id, function(err) {
        console.log(err);
      });

      res.status(200).send("Merci "+participant.prenom+" "+participant.nom+" de rejoindre notre application");
    });


    //Traitement d'une requete de creation de soiree
    app.post('/CreerSoiree', function(req,res){
      var nom_soiree = req.body.nom_soiree;
      var participants = req.body.participants;
      var date = req.body.date;
      var id = parseInt(Math.random()*1435);
      var dateFin = req.body.dateFin;
      var createur={};
      /*    var testParticipant = new Participant(req.body.createur);
      testParticipant.prenom="Michel";
      testParticipant.nom="Polnaref";

      dataBase.insertUser(db,testParticipant, function(err) {
      console.log(err);
    });
    */
    dataBase.findUser(db,req.body.createur, function(err) {
      createur = err;
      console.log("BONJOUR");
      console.log(createur);

      //    var nouvelleSoiree = new Soiree(testParticipant,date,dateFin,id,nom_soiree);
      var nouvelleSoiree = new Soiree(500,date,dateFin,id,nom_soiree);
      console.log("NOUVELLE SOIREE");
      console.log(nouvelleSoiree);

      var jsonParticipants = JSON.parse(participants);
      for(var i=0; i<jsonParticipants.length; i++){
        nouvelleSoiree.addParticipant(jsonParticipants[i],"Preparation");
      }

      dataBase.insertSoiree(db,nouvelleSoiree, function(err) {
        //db.close();
        console.log(err);
        console.log("Id soiree: "+id);

        dataBase.findSoiree(db,id, function(err) {
          console.log(err);
          if(err!= null && typeof err.obj !== 'undefined'){
            console.log("Before each");
            console.log(err.obj.participant);
            err.obj.participant.forEach(function(p,i){
              console.log(p);
            });
          }
          res.status(200).send("Votre soiree a bien ete cree");
        });
      });
    });
    setTimeout(function(){}, 1500);
  });







  app.post('/FinSoiree',function(req,res){
    var id_soiree = req.body.id_soiree;

    //Acceder a la bonne soiree: var soiree = id_soiree correspondante
    /*
    var data = {titre: "C'est la fin!", contenu: 'la soiree '+soiree.nom+' est terminee ou a ete annulee'};
    for(var i=0; i<soiree.participants.length; i++){
    id_client = soiree.participants[i].id;
    io.emit(id_client, data);
  }
  */
  res.status(200).send("Votre soiree a bien ete signalee comme terminee");
});






//Traitement d'une requete d'actualisation de GPS
app.post('/ActualiserGPS',function(req,res){
  var coord_recues = req.body.coordonnees;
  var id_recue = req.body.mon_id;
  var id_soiree = req.body.id_soiree;
  var tabCoordonnees = '{"Coordonnees":[]}'; //On cree un nouveau tableau de coordonnees vides a envoyer en reponse
  // On parcourt les participants de la soiree "id_soiree", si on est sur le "id_recue" alors on modifie les coordonnees GPS correspondantes a ce client
  // Sinon on push {"id_client": id_client, "coordonnees":coordonnees} dans tabCoordonnees;
  res.status(200).send(JSON.stringify(tabCoordonnees));
});





app.post('/ChangeStatus', function(req,res){
  var nouveau_statut = req.body.nouveau_statut;
  var id_soiree = req.body.id_soiree;
  var mon_id = req.body.mon_id;
  var nom = tabParticipants[mon_id];
  var data = {titre: "Nouvelle info!", contenu: JSON.stringify(nom)};
  for(var i=0; i<soiree.participants.length; i++){
    id_client = soiree.participants[i].id;
    io.emit(id_client, data);
  };
});


app.post('/AjouterPart', function(req,res){

});

app.post('/SupprimerPart', function(req,res){

});

});

};
