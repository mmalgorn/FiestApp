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
var User = require('./bdUser.js');
var Soiree = require('./bdSoiree.js');



Array.prototype.remove = function(from, to) {
  var rest = this.slice((to || from) + 1 || this.length);
  this.length = from < 0 ? this.length + from : from;
  return this.push.apply(this, rest);
};

exports.lancerServeur=function(){

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


//INSCRIPTION USAGER
  app.post('/AddUser', function(req, res){
    User.insertUser(req.body)
    .then(function(user){
      //console.log(user);
      res.status(200).send("Add User "+ user.prenom +" "+user.nom +" OK");
    })
    .catch(function(err){
      res.status(500).send("Erreur lors de l'insertion \n"+err)
    })
    .done();
  });

//CREATION DE SOIREE
  app.post('/AddSoiree', function(req, res){
    Soiree.insertSoiree(req.body)
    .then(function(soiree){
      //console.log(user);
      res.status(200).send("Add soiree "+ soiree.nom_soiree +" OK");
    })
    .catch(function(err){
      res.status(500).send("Erreur lors de l'insertion \n"+err)
    })
    .done();
  });

//FIN DE SOIREE
  app.post('/FinSoiree',function(req,res){
    Soiree.removeSoiree(req.body)
    .then(function(soiree){
      //console.log(user);
      res.status(200).send("Votre soiree "+ soiree.nom_soiree +" a bien ete annulee ou supprimee");
    })
    .catch(function(err){
      res.status(500).send("Erreur lors de la suppression de la soiree: \n"+err)
    })
    .done();
});

//ACTUALISATION GPS
app.post('/UpdatePosition', function(req,res){
  User.UpdateGPS(req.body)
  .then(function(user){
    //console.log(user);
    res.status(200).send("Votre position a bien ete actualisee");
  })
  .catch(function(err){
    res.status(500).send("Erreur lors de l'actualisation: \n"+err)
  })
  .done();
});


app.post('/PosParticipants', function(req,res){

});

//AJOUT PARTICIPANT A UNE SOIREE
app.post('/NewPart', function(req,res){
  Soiree.addPart(req.body)
  .then(function(soiree){
    //console.log(user);
    res.status(200).send("Votre soiree "+ soiree.nom_soiree +" a bien ete annulee ou supprimee");
  })
  .catch(function(err){
    res.status(500).send("Erreur lors de la suppression de la soiree: \n"+err)
  })
  .done();
});

//RETRAIT PARTICIPANT D'UNE SOIREE
app.post('/DeadFriend', function(req,res){
  Soiree.removePart(req.body)
  .then(function(soiree){
    //console.log(user);
    res.status(200).send("Votre soiree "+ soiree.nom_soiree +" a bien ete annulee ou supprimee");
  })
  .catch(function(err){
    res.status(500).send("Erreur lors de la suppression de la soiree: \n"+err)
  })
  .done();
});

//ACTUALISATION STATUT PARTICIPANT D'UNE SOIREE
app.post('/UpdateStatus', function(req,res){
  Soiree.updateStatusPart(req.body)
  .then(function(soiree){
    //console.log(user);
    res.status(200).send("Votre soiree "+ soiree.nom_soiree +" a bien ete annulee ou supprimee");
  })
  .catch(function(err){
    res.status(500).send("Erreur lors de la suppression de la soiree: \n"+err)
  })
  .done();
});

};
