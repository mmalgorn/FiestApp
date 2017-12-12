var restServ = require('./serveurREST.js');
var dataBase = require('./bd.js');
/*var MongoClient = require("mongodb").MongoClient;
var { Participant } = require('./participant.js');
var { Soiree } = require('./soiree.js');
var assert = require('assert');
var url = 'mongodb://localhost:27017/fiestapp';
var ObjectId = require('mongodb').ObjectID;
*/

restServ.lancerServeur(dataBase);

/*
MongoClient.connect(url, function(err, db) {
  //  db.dropDatabase();
  assert.equal(null, err);
  var testParticipant = new Participant('testid');
  testParticipant.nom = 'Malgorn';
  testParticipant.prenom = 'Mathieu';
  //testParticipant.test();
  var testSoiree = new Soiree(testParticipant,'test','test','idTest');
  //testSoiree.testCreateur();
  var p2 = new Participant('lol');
  p2.nom = "Test";
  //testSoiree.afficheParticipant();
  testSoiree.addParticipant(p2);
  //testSoiree.afficheParticipant();

  console.log("Connected correctly to server.");
  dataBase.insertSoiree(db,testSoiree, function(err) {
    //db.close();
    console.log(err);
  });

  dataBase.findSoiree(db,'idTest', function(err) {
    console.log(err);
    if(err!= null && typeof err.obj !== 'undefined'){
      console.log("Before each");
      console.log(err.obj.participant);
      err.obj.participant.forEach(function(p,i){
        console.log(p);
      });
    }
  });

  dataBase.insertUser(db,testParticipant, function(err) {
    console.log(err);
  });

  dataBase.insertParticipantSoiree(db,'idTest','TestInsert',function(err){
    console.log(err);
  });

  dataBase.findSoiree(db,'idTest', function(err) {
    console.log("In insert");
    console.log(err);
    if(err!= null && typeof err.obj !== 'undefined'){
      console.log("Before each");
      console.log(err.obj.participant);
      err.obj.participant.forEach(function(p,i){
        console.log(p);
      });
    }
  });

  dataBase.deleteParticipantSoiree(db,'idTest','TestInsert',function(err){

    console.log(err);
  });

  dataBase.findSoiree(db,'idTest', function(err) {
    console.log(err);
    if(err!= null && typeof err.obj !== 'undefined'){
      console.log("Before each");
      console.log(err.obj.participant);
      err.obj.participant.forEach(function(p,i){
        console.log(p);
      });
    }
  });

  dataBase.updateStatusSoiree(db,'idTest','lol',"Glandage",function(err){
    console.log(err);
  })

  dataBase.findUser(db,'testid', function(err) {
    console.log(err);
  });

  //    db.close();
});
*/
