"use strict";
var { Participant } = require('./participant.js');
// Array Remove - By John Resig (MIT Licensed)
Array.prototype.remove = function(from, to) {
  var rest = this.slice((to || from) + 1 || this.length);
  this.length = from < 0 ? this.length + from : from;
  return this.push.apply(this, rest);
};
exports.Soiree = class{


  constructor(id_createur,date,dateFin,id,nom){
    this.id = id;
    this.idCreateur = id_createur;
    this.date = date;
    this.datefin = dateFin;
    this.nom_soiree = nom;
    this.participant = [];

    var part = {
      id : id_createur,
      status : "Preparation"
    }
    this.participant.push(part);
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
  set nom_soiree(nom){
    this._nom_soiree=nom;
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
  get nom_soiree(){
    return this._nom_soiree;
  }


  addParticipant(id_part,status){
    var part = {
      id : id_part,
      status : "Preparation"
    }
    this.participant.push(part);
  }

  removeParticipant(participant){
    for(var i=0;i<this.participant.length;i++){
      if(this.participant[i].id == participant){
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

var testSoiree = new Soiree(testParticipant,'test','test','testid');

testSoiree.testCreateur();
var p2 = new Participant('lol');
p2.nom = "Test";
testSoiree.afficheParticipant();
testSoiree.addParticipant(p2);
testSoiree.afficheParticipant();
testSoiree.removeParticipant(p2);
testSoiree.afficheParticipant();
*/
