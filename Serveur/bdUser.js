var mongoose = require("mongoose");
mongoose.connect('mongodb://localhost/fiestapp', { useMongoClient: true });
var Schema = mongoose.Schema;
var Q = require("q");
var ObjectId = mongoose.Schema.Types.ObjectId;



var userSchema = new Schema({

  nom : String,
  position : [[Number]],
  prenom : String
});

var User = mongoose.model('User',userSchema);

User.findUser = function(id){
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


User.findUserByName = function(user){
  var deferred = Q.defer();
  // Find a single department and return in
  this.findOne({nom: user.nom,prenom: user.prenom}, function(error, user){
    if (error) {
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


User.insertUser = function(user){
  console.log("INSERT USER");
  var deferred = Q.defer();
  var taille = user.position.length;
  var pos = true;
  var pos1 ="";
  var pos2="";
  for(var i=0; i<taille; i++){
    if(user.position[i]!=',' && pos){
      pos1+=user.position[i];
    }
    else if(user.position[i]==','){
      pos = false;
    }
    else if(user.position[i]!=','){
      pos2+=user.position[i];
    }
  }
  var userToAdd = new User({
    nom: user.nom,
    prenom:user.prenom,

    position :[pos1,pos2]
  });
  console.log("user a ajouter: ");
  console.log(userToAdd);

  User.findUserByName(userToAdd)
  .then(function(user){
    if(user==null){
      userToAdd.save(function(err,user){
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
          deferred.resolve(user);
        }
      })
    }else{
      deferred.reject(new Error("User déjà présent"));
    }
  })
  .catch(console.log)
  .done();
  console.log("RETURN");
  return deferred.promise;
}


User.UpdateGPS=function(user){
  console.log("MISE A JOUR POSITION USER");
  var deferred = Q.defer();

  var taille = user.position.length;
  var pos = true;
  var pos1 ="";
  var pos2="";
  for(var i=0; i<taille; i++){
    if(user.position[i]!=',' && pos){
      pos1+=user.position[i];
    }
    else if(user.position[i]==','){
      pos = false;
    }
    else if(user.position[i]!=','){
      pos2+=user.position[i];
    }
  }

  var query = { name: user.nom, prenom:user.prenom };
  User.findOneAndUpdate(query, {$set:{position:[pos1,pos2]}}, {new: true}, function(error, doc){
    if(error){
      console.log("ERROR");
      console.log(err);
      deferred.reject(new Error(err));
    }
    else{
      console.log(doc);
      console.log("NO ERROR");
      deferred.resolve(doc);
    }
  });
  return deferred.promise;
}



module.exports = User;
