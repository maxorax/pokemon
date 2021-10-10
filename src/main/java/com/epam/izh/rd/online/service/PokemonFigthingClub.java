package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Pokemon;

import java.io.FileOutputStream;
import java.io.IOException;

public class PokemonFigthingClub implements PokemonFightingClubService {

    @Override
    public Pokemon doBattle(Pokemon p1, Pokemon p2) {
        if(p1.getPokemonId() > p2.getPokemonId()){
            while(p2.getHp()>0){
                doDamage(p1, p2);
                if(p2.getHp()>0){
                    doDamage(p2,p1);
                }
                else{ showWinner(p1); return p1; }
            }
            showWinner(p2);
            return p2;
        }
        else{
            while(p1.getHp()>0){
                doDamage(p2, p1);
                if(p1.getHp()>0){
                    doDamage(p1,p2);
                }
            else{ showWinner(p2); return p2; }
        }
            showWinner(p1);
            return p1;
        }
    }

    @Override
    public void showWinner(Pokemon winner) {
        try {
            new FileOutputStream("/Users/maksim/IdeaProjects/http-template/"+winner.getPokemonName()+".png").write(new PokemonFetchingServices().getPokemonImage(winner.getPokemonName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doDamage(Pokemon from, Pokemon to) {
        to.setHp(Short.parseShort(String.valueOf(to.getHp() -(from.getAttack()- from.getAttack()*to.getDefense()/100))));
    }
}
