"use strict";

exports.insertSoiree = function(db,obj, callback) {
  db.collection('soiree').insertOne( {
    _id : obj._id,
    obj
  }, function(err, result) {
    //assert.equal(err, null);
    console.log("Soiree "+ obj.id +" bien inseree");
    callback(err);
  });
};

exports.insertParticipantSoiree = function(db,idSoiree,idPart,callback){
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

exports.deleteParticipantSoiree = function(db,idSoiree,idPart,callback){
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

exports.updateStatusSoiree = function(db,idSoiree,idPart,status,callback){
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



exports.findSoiree = function(db,id, callback) {
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

exports.insertUser = function(db,obj,callback){
  db.collection('user').insertOne( {
    _id : obj._id,
    obj
  }, function(err, result) {
    //assert.equal(err, null);
    console.log(obj);
    console.log("Utilisateur ajoute a la base de donnee");
    callback(err);
  });
};


exports.findUser = function(db,id,callback){
  var cursor =db.collection('user').find({_id:id} );
  cursor.each(function(err, doc) {
    //assert.equal(err, null);
    if (doc != null) {
      console.dir(doc);
      //console.dir(doc._id.id);
      if(doc._id ==id){
        callback(doc);
      }
    } else {
      callback(err);
    }
  });
};
