"use strict";

exports.Participant = class{

  constructor(id){
    this.id = id;
    this.position = 'pos';
    this.nom = '';
    this.prenom = '';

  }

  set nom(name){
    this._nom = name.charAt(0).toUpperCase() + name.slice(1);
  }
  set prenom(name){
    this._prenom = name.charAt(0).toUpperCase() + name.slice(1);
  }
  set position(pos){
    this._position = pos;
  }
  set id(id){
    this._id = id;
  }
  get id(){
    return this._id;
  }
  get nom(){
    return this._nom;
  }
  get prenom(){
    return this._prenom;
  }
  get position(){
    return this._position;
  }

  test(){
    console.log('Nom '+this.nom+' Prenom '+ this.prenom+' ID '+this.id);
  }

}
