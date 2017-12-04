var MongoClient = require("mongodb").MongoClient;
var { Participant } = require('./participant.js');
var { Soiree } = require('./soiree.js');
var assert = require('assert');
var url = 'mongodb://localhost:27017/fiestapp';
var ObjectId = require('mongodb').ObjectID;


var insertSoiree = function(db,obj, callback) {
   db.collection('soiree').insertOne( {
      obj
   }, function(err, result) {
    assert.equal(err, null);
    console.log("Inserted a document into the restaurants collection.");
    callback();
  });
};
var findSoiree = function(db, callback) {
   var cursor =db.collection('soiree').find( );
   cursor.each(function(err, doc) {
      assert.equal(err, null);
      if (doc != null) {
         console.dir(doc);
         console.dir(doc._id.id);
         doc.obj.participant.forEach(function(p,i){
           if (p!=null){
             console.dir(p);

           }else{
             callback();
           }
         })
      } else {
         callback();
      }
   });
};


MongoClient.connect(url, function(err, db) {
//  db.dropDatabase();
  assert.equal(null, err);
  var testParticipant = new Participant('testid');
  testParticipant.nom = 'Malgorn';
  testParticipant.prenom = 'Mathieu';
  testParticipant.test();
  var testSoiree = new Soiree(testParticipant,'test');
  testSoiree.testCreateur();
  var p2 = new Participant('lol');
  p2.nom = "Test";
  testSoiree.afficheParticipant();
  testSoiree.addParticipant(p2);
  testSoiree.afficheParticipant();

  console.log("Connected correctly to server.");
  insertSoiree(db,testSoiree, function() {
     db.close();
 });

 findSoiree(db, function() {
      db.close();
  });


});
