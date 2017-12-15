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

function filterArray(test_array) {
    var index = -1,
        arr_length = test_array ? test_array.length : 0,
        resIndex = -1,
        result = [];

    while (++index < arr_length) {
        var value = test_array[index];

        if (value) {
            result[++resIndex] = value;
        }
    }

    return result;
}

var soireeSchema = new Schema({

  idCreateur : {type : ObjectId, ref:'User'},
  date : Number,
  datefin : Number,
  nom_soiree : String,
  participants : String,
  position : [Number]
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

  var taille = soiree.position.length;
  var pos = true;
  var pos1 ="";
  var pos2="";
  for(var i=0; i<taille; i++){
    if(soiree.position[i]!=',' && pos){
      pos1+=soiree.position[i];
    }
    else if(soiree.position[i]==','){
      pos = false;
    }
    else if(soiree.position[i]!=','){
      pos2+=soiree.position[i];
    }
  }
  var soireeToAdd = new Soiree({
    date : soiree.date,
    datefin : soiree.dateFin,
    nom_soiree : soiree.nom,
    idCreateur : soiree.idCreateur,
    position :[pos1,pos2]
  });

  var participants = [];
  //Ajout du createur dans les participants
  var j=0;
  var part = {
    id : soireeToAdd.idCreateur,
    status : "Preparation"
  };
  participants[j]=part;


  if(soiree.participants != null &&soiree.participants.length>0){
  //Recuperation et ajout des participants (IDs) passes dans la requete
  var taille = soiree.participants.length;
  var cursor ="";
  for(var i=0; i<taille; i++){
    if(soiree.participants[i]==','){
        var part = {
          id : cursor,
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
      status : "Preparation"
    };
    participants[j]=part;
  }

  var stringParts = JSON.stringify(participants);
  console.log("PARTICIPANTS A AJOUTER:");
  console.log(stringParts);
  soireeToAdd.participants = stringParts;

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
      var chaine = jsonParts[j].id.toString();
      console.log(chaine);
      if(chaine===block.idUser){
        array[j].status=block.status;
      }
      else{}
    }
    console.log("NOUVEAUX STATUTS:");
    console.log(array);
    var stringParts=JSON.stringify(array);
    console.log("NOUVEAU STRING PARTS:");
    console.log(stringParts);
    var conditions = { _id: block.idSoiree }
    , update = { $set: { participants:stringParts}}
    , options = { multi: true };
    Soiree.update(conditions, update, options, function(err) {
      console.log("MISE A JOUR SOIREE");
      if(err){
        console.log("ERROR");
        console.log(err);
        deferred.reject(new Error(err));
      }
      else{
        console.log("NO ERROR");
      }
    });
    deferred.resolve(array);
  })
  .catch(console.log)
  .done();
  return deferred.promise;
}

Soiree.updateParts=function(block, addRemove){
  //block comprend: idSoiree, idUser, status
  console.log("MISE A JOURDES PARTICIPANTS");
  console.log("block de modification:");
  console.log(block);
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
    if(addRemove==1){
    for(var j=0; j<jsonParts.length; j++){
      array[j]=jsonParts[j]
      var chaine = jsonParts[j].id.toString();
      console.log(chaine);
      if(chaine===block.idUser){
        array.remove(j);
      }
      else{}
      }
    }
    else{
      for(var j=0; j<jsonParts.length; j++){
        array[j]=jsonParts[j]
        var chaine = jsonParts[j].id.toString();
        console.log(chaine);
        if(chaine===block.idUser){
          deferred.reject(new Error("Cet user participe deja a la soiree..."));
        }
        else{}
      }
      var newPart = {
        id:block.idUser,
        status:"Preparation"
      }
      array[j+1]=newPart;
    }
    array = filterArray(array);

    console.log("NOUVEAUX PARTICIPANTS:");
    console.log(array);
    var stringParts=JSON.stringify(array);
    console.log("NOUVEAU STRING PARTS:");
    console.log(stringParts);
    var conditions = { _id: block.idSoiree }
    , update = { $set: { participants:stringParts}}
    , options = { multi: true };
    Soiree.update(conditions, update, options, function(err) {
      console.log("MISE A JOUR SOIREE");
      if(err){
        console.log("ERROR");
        console.log(err);
        deferred.reject(new Error(err));
      }
      else{
        console.log("NO ERROR");
      }
    });
    deferred.resolve(array);
  })
  .catch(console.log)
  .done();
  return deferred.promise;
}

module.exports = Soiree;
