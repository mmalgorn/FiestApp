var mongoose = require("mongoose");
mongoose.connect('mongodb://localhost/fiestapp', { useMongoClient: true });
var Schema = mongoose.Schema;
var Q = require("q");
var ObjectId = mongoose.Schema.Types.ObjectId;
var User = require('./bdUser.js');

var soireeSchema = new Schema({

  idCreateur : {type : ObjectId, ref:'User'},
  date : Number,
  datefin : Number,
  nom_soiree : String,
  participants : [{type : ObjectId,ref:'User', status:String}]
});

var Soiree = mongoose.model('Soiree',soireeSchema);

Soiree.findSoiree = function(id){
  var deferred = Q.defer();
  // Find a single department and return in
  this.findOne({_id: id}, function(error, user){
    if (error) {
      // Throw an error
      deferred.reject(new Error(error));
    }
    else {
      // No error, continue on
      deferred.resolve(user);
    }
  });
  // Return the promise that we want to use in our chain
  return deferred.promise;
}

Soiree.findSoireeByName = function(soiree){
  var deferred = Q.defer();
  // Find a single department and return in
  this.findOne({nom_soiree: soiree.nom_soiree, date: soiree.date, idCreateur:soiree.idCreateur}, function(error, soiree){
    if (error) {
      // Throw an error
      deferred.reject(new Error(error));
    }
    else {
      // No error, continue on
      deferred.resolve(soiree);
    }
  });
  // Return the promise that we want to use in our chain
  return deferred.promise;
}

Soiree.insertSoiree = function(soiree){
  console.log("INSERT SOIREE");
  var deferred = Q.defer();

  var soireeToAdd = new Soiree({
    date : soiree.date,
    datefin : soiree.dateFin,
    nom_soiree : soiree.nom,
    idCreateur : soiree.idCreateur,
    participants : []
  });

  //Ajout du createur dans les participants
  var j=0;
  var part = {
    id : soireeToAdd.idCreateur,
    ref:'User',
    status : "Preparation"
  };
  soireeToAdd.participants[j]=part;
  j++;

  //Recuperation et ajout des participants (IDs) passes dans la requete
  var taille = soiree.participants.length;
  var cursor ="";
  for(var i=0; i<taille; i++){
    if(soiree.participants[i]==','){

      var part = {
        id : cursor,
        ref:'User',
        status : "Preparation"
      };
      soireeToAdd.participants[j]=part;

      j++;
      cursor = "";
    }
    else{
      cursor+=soiree.participants[i];
    }
  }
  var part = {
    id : cursor,
    ref:'User',
    status : "Preparation"
  };
  soireeToAdd.participants[j]=part;

  console.log("soiree a ajouter: ");
  console.log(soireeToAdd);

  Soiree.findSoireeByName(soireeToAdd)
  .then(function(soiree){
    if(soiree==null){
      soireeToAdd.save(function(err,soiree){
        console.log("IN SAVE");
        if (err) {
          // Throw an error
          console.log("ERROR");
          console.log(err);
          deferred.reject(new Error(err));
        }
        else {
          // No error, continue on
          console.log("NO ERROR");
          deferred.resolve(soiree);
        }
      })
    }else{
      deferred.reject(new Error("Soiree deja existante"));
    }
  })
  .catch(console.log)
  .done();
  console.log("RETURN");
  return deferred.promise;
}


Soiree.removeSoiree = function(soiree){
  console.log("TRYING TO DELETE A SOIREE");
  var deferred = Q.defer();
  console.log(soiree);

  Soiree.findOneAndRemove({nom_soiree: soiree.nom, date: soiree.date, idCreateur:soiree.idCreateur}, function(error, soiree){
    if (error) {
      console.log("ERROR");
      deferred.reject(new Error("Cette soiree n'existe pas dans notre base de donnees"));
    }
    else {
      console.log("NO ERROR");
      deferred.resolve(soiree);
    }
  });
  // Return the promise that we want to use in our chain
  return deferred.promise;
};


module.exports = Soiree;
