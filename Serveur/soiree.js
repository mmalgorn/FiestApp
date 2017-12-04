"use strict";
var { Participant } = require('./participant.js');
// Array Remove - By John Resig (MIT Licensed)
Array.prototype.remove = function(from, to) {
  var rest = this.slice((to || from) + 1 || this.length);
  this.length = from < 0 ? this.length + from : from;
  return this.push.apply(this, rest);
};
class Soiree{

  constructor(nom, createur, date){
    this.id = 'id';
    this.idCreateur = createur.id;
    this.date = date;
    this.participant = [];
    this.nom_soiree = nom;
    this.participant.push(createur);

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
  get id(){
    return this._id;
  }
  get idCreateur(){
    return this._idCreateur;
  }
  get date(){
    return this._date;
  }


  addParticipant(participant){
    this.participant.push(participant);
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

var testParticipant = new Participant('testid');
testParticipant.nom = 'Malgorn';
testParticipant.prenom = 'Mathieu';
testParticipant.test();
var testSoiree = new Soiree("LaSoireeAmbiancee",testParticipant,'test');
testSoiree.testCreateur();
var p2 = new Participant('lol');
p2.nom = "Test";
testSoiree.afficheParticipant();
testSoiree.addParticipant(p2);
testSoiree.afficheParticipant();
testSoiree.removeParticipant(p2);
testSoiree.afficheParticipant();
