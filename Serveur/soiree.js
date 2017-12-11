"use strict";
var { Participant } = require('./participant.js');
// Array Remove - By John Resig (MIT Licensed)
Array.prototype.remove = function(from, to) {
  var rest = this.slice((to || from) + 1 || this.length);
  this.length = from < 0 ? this.length + from : from;
  return this.push.apply(this, rest);
};
exports.Soiree = class{

<<<<<<< HEAD
  constructor(createur,date,dateFin,id){
    this.id = id;
=======
  constructor(nom, createur, date){
    this.id = 'id';
>>>>>>> ffe00e2db1329efae17c749055aa8d8ad55ee4e6
    this.idCreateur = createur.id;
    this.date = date;
    this.datefin = dateFin;
    this.participant = [];
<<<<<<< HEAD

    var part = {
      id : createur.id,
      status : "Preparation"
    }
    this.participant.push(part);

=======
    this.nom_soiree = nom;
    this.participant.push(createur.id);
>>>>>>> ffe00e2db1329efae17c749055aa8d8ad55ee4e6
  }

  set id(id){
    this._id = id;
  }
  set idCreateur(createur){
    this._idCreateur = createur;
  }
  set date(date){
    this._date = date;
  }
  set dateFin(date){
    this._dateFin = date;
  }
  get id(){
    return this._id;
  }
  get idCreateur(){
    return this._idCreateur;
  }
  get date(){
    return this._date;
  }
  get dateFin(){
    return this._dateFin;
  }

<<<<<<< HEAD
  addParticipant(participant,status){
    var part = {
      id : participant.id,
      status : "Preparation"
    }
    this.participant.push(part);
=======

  addParticipant(participant){
    this.participant.push(participant.id);
>>>>>>> ffe00e2db1329efae17c749055aa8d8ad55ee4e6
  }

  removeParticipant(participant){
    for(var i=0;i<this.participant.length;i++){
      if(this.participant[i].id == participant.id){
        this.participant.remove(i);
      }
    }
  }

  testCreateur(){
    console.log('Le créateur est '+this.idCreateur);
  }

  afficheParticipant(){
      for(var i=0;i<this.participant.length;i++){
        console.log(this.participant[i].nom);
      }
  }

}
/*
var testParticipant = new Participant('testid');
testParticipant.nom = 'Malgorn';
testParticipant.prenom = 'Mathieu';
testParticipant.test();
<<<<<<< HEAD
var testSoiree = new Soiree(testParticipant,'test','test','testid');
=======
var testSoiree = new Soiree("LaSoireeAmbiancee",testParticipant,'test');
>>>>>>> ffe00e2db1329efae17c749055aa8d8ad55ee4e6
testSoiree.testCreateur();
var p2 = new Participant('lol');
p2.nom = "Test";
testSoiree.afficheParticipant();
testSoiree.addParticipant(p2);
testSoiree.afficheParticipant();
testSoiree.removeParticipant(p2);
testSoiree.afficheParticipant();
*/
