var mongoose = require("mongoose");
mongoose.connect('mongodb://localhost/fiestapp', { useMongoClient: true });
var Schema = mongoose.Schema;
var Q = require("q");
var ObjectId = mongoose.Schema.Types.ObjectId;


var soireeSchema = new Schema({

  idCreateur : {type : ObjectId, ref:'User'},
  date : Number,
  datefin : Number,
  nom_soiree : String,
  participants : [{type : ObjectId,ref:'User'}]
});

var USoiree = mongoose.model('Soiree',soireeSchema);

User.findSoiree = function(id){

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


User.insertSoiree = function(soiree){
  console.log("INSERT SOIREE");
  var deferred = Q.defer();
  var taille = user.participants.length;

  var cursor ="";

  for(var i=0; i<taille; i++){
    if(soiree.participants[i]!=','){
      cursor+=user.participants[i];
    }
    else if(user.participants[i]==','){
      cursor = "";
    }
  }

  var soireeToAdd = new Soiree({
    date : soiree.date,
    datefin : soiree.dateFin,
    nom_soiree : soiree.nom,
  });
  console.log(userToAdd);

  User.findSoireeByName(soireeToAdd)
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
