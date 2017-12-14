var mongoose = require("mongoose");
mongoose.connect('mongodb://localhost/fiestapp', { useMongoClient: true });
var Schema = mongoose.Schema;
var Q = require("q");
var ObjectId = mongoose.Schema.Types.ObjectId;
var User = require('./bdUser.js');


Array.prototype.remove = function(from, to) {
  var rest = this.slice((to || from) + 1 || this.length);
  this.length = from < 0 ? this.length + from : from;
  return this.push.apply(this, rest);
};


var soireeSchema = new Schema({

  idCreateur : {type : ObjectId, ref:'User'},
  date : Number,
  datefin : Number,
  nom_soiree : String,
  participants : String
});

var Soiree = mongoose.model('Soiree',soireeSchema);

Soiree.findSoiree = function(id){
  var deferred = Q.defer();
  // Find a single department and return in
  this.findOne({_id: id}, function(error, soiree){
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
  });

  var participants = [];
  //Ajout du createur dans les participants
  var j=0;
  var part = {
    id : soireeToAdd.idCreateur,
    ref:'User',
    status : "Preparation"
  };
  participants[j]=part;
  j++;

  if(soiree.participants.length>0){
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
        participants[j]=part;

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
    participants[j]=part;
    var stringParts = JSON.stringify(participants);
    console.log("ARTICIPANTS A AJOUTER:");
    console.log(stringParts);
    soireeToAdd.participants = stringParts;
  }
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
          console.log("NO ERROR");
          deferred.resolve(soiree);
        }
      });
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


Soiree.getPositions=function(block){
  console.log("RECHERCHE DES POSITIONS DES PARTICIPANTS");
  var deferred = Q.defer();
  var positions = [];
  var j =0;
  Soiree.findSoireeByName(block)
  .then(function(soiree){
    console.log("SOIREE TROUVEE");
    console.log(soiree);
    var jsonParts = JSON.parse(soiree.participants);
    console.log("Participants soiree: ");
    console.log(jsonParts);

    var array = [];
    for(var j=0; j<jsonParts.length; j++){
      array[j]=jsonParts[j].id;
    }
    console.log("Tableau d'IDs:");
    console.log(array);

    for(var i=0; i<array.length; i++){
      console.log("je recherche l'user "+array[i]);
      User.findUser(array[i])
      .then(function(user){
        console.log("j'ai trouve l'user:"+ user);
        retour = "{prenom:"+ user.prenom+",position:"+user.position+"}";
        positions[j]=retour;
        console.log("positions a renvoyer:");
        console.log(positions);
        console.log('\n');
        j++;
        if(positions.length == array.length){
          // console.log("positions a renvoyer:");
          // console.log(positions);
          // console.log('\n');
          deferred.resolve(positions);
        }
        else{}
      })
      .catch(console.log)
      .done();
    }
  })
  .catch(console.log)
  .done()
  return deferred.promise;
}


Soiree.updateStatusPart=function(block){
  //block comprend: idSoiree, idUser, status
  console.log("MISE A JOUR STATUT PARTICIPANT");
  console.log("block de modification:"+ block);
  console.log('\n');
  var deferred = Q.defer();
  var soiree;
  Soiree.findSoiree(block.idSoiree)
  .then(function(soiree){
    console.log("SOIREE TROUVEE:");
    console.log(soiree);
    console.log('\n');

    var jsonParts = JSON.parse(soiree.participants);
    console.log("Participants soiree: ");
    console.log(jsonParts);
    console.log('\n');

    var array = [];
    for(var j=0; j<jsonParts.length; j++){
      array[j]=jsonParts[j]
      if(jsonParts[j].id.equals(block.idUser)){
        array[j].status=block.status;
      }
      else

    }


    console.log(newStatus);
    var conditions = { _id: block.idSoiree }
    , update = { $set: { participants: newStatus }}
    , options = { multi: true };
    Soiree.update(conditions, update, options, function(err) {
      if(err){
        console.log("ERROR");
        console.log(err);
        deferred.reject(new Error(err));
      }
      else{
        console.log("NO ERROR");
      }
    });
    deferred.resolve(newStatus);
  })
  .catch(console.log)
  .done();
  return deferred.promise;
}


module.exports = Soiree;
