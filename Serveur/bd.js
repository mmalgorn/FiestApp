var MongoClient = require("mongodb").MongoClient;
var { Participant } = require('./participant.js');
var { Soiree } = require('./soiree.js');
var assert = require('assert');
var url = 'mongodb://localhost:27017/fiestapp';
var ObjectId = require('mongodb').ObjectID;


var insertSoiree = function(db,obj, callback) {
  db.collection('soiree').insertOne( {
    _id : obj._id,
    obj
  }, function(err, result) {
    //assert.equal(err, null);
    console.log("Inserted a document into the restaurants collection.");
    callback(err);
  });
};

var insertParticipantSoiree = function(db,idSoiree,idPart,callback){
  var cursor = db.collection('soiree').find({_id:idSoiree});
  cursor.each(function(err,doc){
    if(doc!= null){
      if(doc._id == idSoiree){
        var present = false;
        doc.obj.participant.forEach(function(p,i){
          if(p.id==idPart){
            present = true;
          }
        })
        if(!present){
          doc.obj.participant.push({id:idPart,status:"Preparation"});
          var query = {_id:idSoiree};
          var newValues = {obj:{participant:doc.obj.participant}};
          db.collection('soiree').updateOne(query,newValues,function(err,res){
            if (err) throw err;
            console.log("Participant inseré");
          })

        }
      }
    }
  })
};

var deleteParticipantSoiree = function(db,idSoiree,idPart,callback){
  var cursor = db.collection('soiree').find({_id:idSoiree});
  cursor.each(function(err,doc){
    if(doc!= null){
      if(doc._id == idSoiree){
        var present = false;
        doc.obj.participant.forEach(function(p,i){
          if(p.id==idPart){
            present = true;
          }
        })
        if(present){
          doc.obj.participant = doc.obj.participant.filter(item => item.id !== idPart);
          var query = {_id:idSoiree};
          var newValues = {obj:{participant:doc.obj.participant}};
          console.log("NEW VALUES");
          console.log(newValues);
          db.collection('soiree').updateOne(query,newValues,function(err,res){
            if (err) throw err;
            console.log("Participant Suprimé");
          })

        }
      }
    }
  })
};

var updateStatusSoiree = function(db,idSoiree,idPart,status,callback){
  var cursor = db.collection('soiree').find({_id:idSoiree});
  cursor.each(function(err,doc){
    if(doc!=null){
      if(doc._id == idSoiree){
        doc.obj.participant.forEach(function(p,i){
          if(p.id==idPart){
            doc.obj.participant[i].status = status;
          }
        })
        var query = {_id:idSoiree};
        var newValues = {obj:{participant:doc.obj.participant}};
        console.log("NEW VALUES");
        console.log(newValues);
        db.collection('soiree').updateOne(query,newValues,function(err,res){
          if (err) throw err;
          console.log("Participant Modifiée");
        })
      }
    }
  });

};



var findSoiree = function(db,id, callback) {
  var cursor =db.collection('soiree').find({_id:id} );
  cursor.each(function(err, doc) {
    //assert.equal(err, null);
    if (doc != null) {
      //console.dir(doc);
      //console.dir(doc._id.id);
      if(doc._id ==id){
        //   console.log("find doc");
        //  console.log(doc);
        callback(doc);
      }

    } else {
      callback(err);
    }
  });
};

var insertUser = function(db,obj,callback){
  db.collection('user').insertOne( {
    _id : obj._id,
    obj
  }, function(err, result) {
    //assert.equal(err, null);
    console.log("Inserted a document into the restaurants collection.");
    callback(err);
  });
};


var findUser = function(db,id,callback){
  var cursor =db.collection('user').find({},{_id:id} );
  cursor.each(function(err, doc) {
    //assert.equal(err, null);
    if (doc != null) {
      //console.dir(doc);
      //console.dir(doc._id.id);
      if(doc._id ==id){
        callback(doc);
      }
    } else {
      callback(err);
    }
  });
};

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
  insertSoiree(db,testSoiree, function(err) {
    //db.close();
    console.log(err);
  });

  findSoiree(db,'idTest', function(err) {
    console.log(err);
    if(err!= null && typeof err.obj !== 'undefined'){
      console.log("Before each");
      console.log(err.obj.participant);
      err.obj.participant.forEach(function(p,i){
        console.log(p);
      });
    }
  });

  insertUser(db,testParticipant, function(err) {
    console.log(err);
  });

  insertParticipantSoiree(db,'idTest','TestInsert',function(err){
    console.log(err);
  });

  findSoiree(db,'idTest', function(err) {
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

  deleteParticipantSoiree(db,'idTest','TestInsert',function(err){

    console.log(err);
  });

  findSoiree(db,'idTest', function(err) {
    console.log(err);
    if(err!= null && typeof err.obj !== 'undefined'){
      console.log("Before each");
      console.log(err.obj.participant);
      err.obj.participant.forEach(function(p,i){
        console.log(p);
      });
    }
  });

  updateStatusSoiree(db,'idTest','lol',"Glandage",function(err){
    console.log(err);
  })

  findUser(db,'testid', function(err) {
    console.log(err);
  });

  //    db.close();
});
