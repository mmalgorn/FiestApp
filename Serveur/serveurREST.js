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

  app.post('/AddUser', function(req, res){
    User.insertUser(req.body)
    .then(function(user){
      //console.log(user);
      res.status(200).send("Add User OK");
    })
    .catch(function(err){
      res.status(500).send("Erreur lors de l'insertion \n"+err)
    })
    .done();

  });


};
