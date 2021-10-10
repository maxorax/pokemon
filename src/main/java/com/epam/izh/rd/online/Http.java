package com.epam.izh.rd.online;

import com.epam.izh.rd.online.entity.Pokemon;
import com.epam.izh.rd.online.service.PokemonFetchingServices;
import com.epam.izh.rd.online.service.PokemonFigthingClub;

import java.util.Scanner;

public class Http {

    public static void main(String[] args) {
        PokemonFetchingServices pokemonFetchingServices = new PokemonFetchingServices();
        System.out.println("Имена покемонов вы можете посмотреть на сайте https://pokeapi.co");
        System.out.println("Введите имя первого покемона");
        Scanner in = new Scanner(System.in);
        Pokemon firstPokemon = pokemonFetchingServices.fetchByName(in.next());
        System.out.println("Введите имя второго покемона");
        in = new Scanner(System.in);
        Pokemon secondPokemon = pokemonFetchingServices.fetchByName(in.next());
        System.out.println(new PokemonFigthingClub().doBattle(firstPokemon,secondPokemon).getPokemonName() + " win!");
    }
}
