var http = require('http');
var url = require('url');
var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var cors = require('cors');

app.use(cors());
app.use(bodyParser.json()); // support json encoded bodies
app.use(bodyParser.urlencoded({ extended: true })); // support encoded bodiesvar bodyParser = require('body-parser');
app.use(bodyParser.json()); // support json encoded bodies
app.use(bodyParser.urlencoded({ extended: true })); // support encoded bodies


Array.prototype.remove = function(from, to) {
  var rest = this.slice((to || from) + 1 || this.length);
  this.length = from < 0 ? this.length + from : from;
  return this.push.apply(this, rest);
};




exports.lancerServeur=function(){

  var server = app.listen(3000, function () {
    var host = server.address().address;
    var port = server.address().port;
    console.log("Serveur en ecoute sur http://%s:%s", host, port);
  });

//Traitement d'une requete de creation de soiree
  app.post('/CreerSoiree', function(req,res){
    var nom_soiree = req.body.nom_soiree;
    var id_client = req.body.mon_id;
    var participants = req.body.participants;
    //Creer la soiree et y ajouter les prticipants s'il y en a
    /*
      newSoiree(nom_soiree, id_client);
      for(var i=0; i<participants.length; i++){
        soiree.addParticipant(participants[i]);
      }
    */
    req.status(200).send("Votre soiree a bien ete cree");
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
  })


//Traitement d'une requete d'actualisation de GPS
  app.post('/ActualiserGPS', function(req,res){
    var coord_recues = req.body.coordonnees;
    var id_recue = req.body.mon_id;
    var id_soiree = req.body.id_soiree;
    var tabCoordonnees = '{"Coordonnees":[]}'; //On cree un nouveau tableau de coordonnees vides a envoyer en reponse
    // On parcourt les participants de la soiree "id_soiree", si on est sur le "id_recue" alors on modifie les coordonnees GPS correspondantes a ce client
    // Sinon on push {"id_client": id_client, "coordonnees":coordonnees} dans tabCoordonnees;
    req.status(200).send(JSON.stringify(tabCoordonnees));
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
    }
  })


}
