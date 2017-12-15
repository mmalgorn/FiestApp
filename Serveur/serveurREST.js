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
    // OK
    app.post('/AddUser', function(req, res){
      console.log("ADD USER");
      User.insertUser(req.body)
      .then(function(user){
        //console.log(user);
        res.status(200).send({result:"OK"});
      })
      .catch(function(err){
        res.status(500).send({result:"NOK"})
      })
      .done();
    });


    //TROUVER USAGER PAR SON ID
    // OK
    app.post('/FindUserById', function(req, res){
      console.log("FIND USER");
      console.log(req.body);
      User.findUser(req.body)
      .then(function(user){
        console.log(user);
        res.status(200).send(user);
      })
      .catch(function(err){
        console.log(err);
        res.status(500).send({result:"NOK"});
      })
      .done();
    });

    //TROUVER USAGER PAR SON ET NOM PRENOM
    // OK
    app.post('/FindUser', function(req, res){
      console.log("FIND USER");
      console.log(req.body);
      User.findUserByName(req.body)
      .then(function(user){
        console.log(user);
        res.status(200).send(user);
      })
      .catch(function(err){
        console.log(err);
        res.status(500).send("Erreur lors de la recherche: \n"+err)
      })
      .done();
    });


    // ACTUALISATION GPS
    // OK
    app.post('/UpdatePosition', function(req,res){
      User.UpdateGPS(req.body)
      .then(function(user){
        res.status(200).send({result:"OK"});
      })
      .catch(function(err){
        res.status(500).send({result:"NOK"})
      })
      .done();
    });



    // CREATION DE SOIREE
    // OK
    app.post('/AddSoiree', function(req, res){
      Soiree.insertSoiree(req.body)
      .then(function(soiree){
        //console.log(user);
        res.status(200).send({result:"OK"});
      })
      .catch(function(err){
        res.status(500).send({result:"NOK"})
      })
      .done();
    });

    //TROUVER UNE SOIREE PAR SES NOM, DATE, IDCREATEUR
    // OK
    app.post('/GetSoiree', function(req, res){
      Soiree.findSoireeByName(req.body)
      .then(function(soiree){
        // var newSoiree = soiree;
        // newSoiree.participants = JSON.parse(soiree.participants);
        // res.status(200).send(newSoiree);
        res.status(200).send(soiree);
      })
      .catch(function(err){
        console.log(err);
        res.status(500).send({result:"NOK"});
      })
      .done();
    });

    // FIN DE SOIREE
    // OK
    app.post('/FinSoiree',function(req,res){
      Soiree.removeSoiree(req.body)
      .then(function(soiree){
        res.status(200).send({result:"OK"});
      })
      .catch(function(err){
        res.status(500).send({result:"NOK"})
      })
      .done();
    });


    //AJOUT PARTICIPANT A UNE SOIREE
    //OK
    app.post('/NewPart', function(req,res){
      Soiree.updateParts(req.body,0)
      .then(function(soiree){
        res.status(200).send({result:"OK"});
      })
      .catch(function(err){
        res.status(500).send({result:"NOK"})
      })
      .done();
    });

    //RETRAIT PARTICIPANT D'UNE SOIREE
    //OK
    app.post('/DeadFriend', function(req,res){
      Soiree.updateParts(req.body,1)
      .then(function(soiree){
        res.status(200).send({result:"OK"});
      })
      .catch(function(err){
        res.status(500).send({result:"NOK"})
      })
      .done();
    });

    //ACTUALISATION STATUT PARTICIPANT D'UNE SOIREE
    //OK
    app.post('/UpdateStatus', function(req,res){
      Soiree.updateStatusPart(req.body)
      .then(function(soiree){
        res.status(200).send({result:"OK"});
      })
      .catch(function(err){
        res.status(500).send({result:"NOK"})
      })
      .done();
    });

    app.post('/MySoirees',function(req,res){
      console.log("MY SOIREES");
      Soiree.findSoireesOuPresent(req.body.idUser)
      .then(function(soirees){
        // console.log("ON PARCOURT LES SOIREES");
        var listeSoirees = soirees;
        var listeSoireesPresent =[];
        var cursor=0;
        // console.log("longueur:"+listeSoirees.length);
        // console.log('\n');
        for(var i=0; i<listeSoirees.length;i++){
          var parts = JSON.parse(listeSoirees[i].participants);
          // console.log("soiree "+i+", participants: ");
          // console.log(parts);
          // console.log('\n');
          if(findElement(JSON.parse(listeSoirees[i].participants),req.body.idUser)){
            // console.log("user present dans la soiree");
            // console.log(listeSoirees[i]);
            // console.log('\n');
            // console.log("Insertion de cette soiree a l'indice"+cursor);
            listeSoireesPresent[cursor]=listeSoirees[i];
            cursor++;
          }
        }
        // console.log("L'user"+req.body.idUser+" est present dans les soirees suivantes:");
        // console.log(listeSoireesPresent);
        res.status(200).send(listeSoireesPresent);
      })
      .catch(function(err){
        console.log("NOK");
      })
      .done();
    });

    function findElement(tab,id){
      for(var i=0; i<tab.length;i++){
        if(tab[i].id===id){return true;}
      }
      return false;
    }

  };
