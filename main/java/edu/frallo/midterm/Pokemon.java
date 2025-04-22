package edu.frallo.midterm;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.stream.Collectors;


/**
 *  get Pokemons from json data
 *  - no constructor because 'jackson' librairy is used to parse json -> Pokemon
 *  - tuto to use jackson lib: <a href="https://stackoverflow.com/questions/60750796/how-to-convert-this-json-to-object-in-java-android">...</a>
 *  - add in Gradle -->  implementation 'com.google.code.gson:gson:2.8.6'
 *
 *  data : <a href="https://raw.githubusercontent.com/fanzeyi/pokemon.json/17d33dc111ffcc12b016d6485152aa3b1939c214/pokedex.json">...</a>
 *  sprites : <a href="https://github.com/fanzeyi/pokemon.json/tree/master/sprites">...</a>
 *  images : <a href="https://github.com/fanzeyi/pokemon.json/tree/master/images">...</a>
 *
 * @author Frédéric RALLO - March 2023
 */
public class Pokemon implements Comparable<Pokemon> {
    public static String language;
    public static List<Pokemon> completeList =new ArrayList<Pokemon>();
    private final String TAG = "fredrallo "+getClass().getSimpleName();


    private int id;
    private Map<String, String> name; //depends of settings language
    private final List<Types> type = new ArrayList<>();
    private Map<String, Integer> base;  //json name is base
    private String pictureURL;


    public Pokemon(){
        super();
        completeList.add(this);
    }

    /**
     * change speed of all NORMAL Type Pokemon
     * @param boost
     */
    public static void boost(int boost) {
        completeList.forEach( pokemon -> {
            if(pokemon.type.contains(Types.NORMAL)) {
                pokemon.base.put(Stats.Speed.toString(), pokemon.base.get(Stats.Speed.toString()) + boost);
            }
        });
    }


    public void setId(int id) {
        pictureURL = "https://github.com/fanzeyi/pokemon.json/blob/master/images/" + String.format("%03d", id) +".png?raw=true";
        this.id = id;
    }

    public void setName(Map<String, String> name) {
        this.name = name;
    }

    public void setBase(Map<String, Integer> base) {
        this.base = base;
    }

    public void setType(List<String> stringType) {
        stringType.forEach( t -> type.add(Types.valueOf(t.toUpperCase())) );
    }


    public int getId() {
        return id;
    }
    public String getPictureURL() {
        return pictureURL;
    }

    public String getName() {
        return name.get(Pokemon.language);
    }
    public List<Types> getTypes() {
        return type;
    }
    public int getBaseValue(Stats stat){
        return base.get(stat.toString());
    }

    /**
     * the best pokemon are those with the highest rank value
     **/
    public Integer getRank(){
        return 4*base.get("Speed") + 3*base.get("Attack") + 2*base.get("Defense")  + base.get("HP");
    }





    @Override
    public String toString() {
        return "Pokemon{ id=" + id + ", name=" + getName() + ", type=" + type + ", features=" + base + '}';
    }


    @Override
    public int compareTo(Pokemon other) {
        if (base==null || other.base==null) return 0;
        return getRank().compareTo(other.getRank());
    }
}


class Podium extends Observable{
    private List<Pokemon> champions = new ArrayList<Pokemon>();
    public Podium(){

        champions.addAll( Pokemon.completeList.stream()
                .sorted(Comparator.reverseOrder()) // trier la liste en utilisant la méthode compareTo de la classe Pokemon
                .limit(3) // limiter le flux aux 3 premiers éléments
                .collect(Collectors.toList()) ); // collecter les éléments dans une liste
    }


    public void updateWinners (){
        champions = new ArrayList<Pokemon>();
        champions.addAll( Pokemon.completeList.stream()
                .sorted(Comparator.reverseOrder()) // trier la liste en utilisant la méthode compareTo de la classe Pokemon
                .limit(3) // limiter le flux aux 3 premiers éléments
                .collect(Collectors.toList()) ); // collecter les éléments dans une liste

        setChanged();
        notifyObservers(this);
    }

    @NonNull
    @Override
    public String toString() {
        return champions.toString();
    }

    public List<Pokemon> getChampions() {
        return champions;
    }
}

//TODO: constants are not uppercase....
enum Stats {
    HP(0),
    Attack(0),
    Defense(0),
    SP_ATTACK(0),
    SP_DEFENSE(0),
    Speed(0);
    private int value;


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    Stats(int value) {
        this.value=value;
    }

}

enum Types{
    NORMAL,
    FIGHTING,
    FLYING,
    POISON,
    GROUND,
    ROCK,
    BUG,
    GHOST,
    STEEL,
    FIRE,
    WATER,
    GRASS,
    ELECTRIC,
    PSYCHIC,
    ICE,
    DRAGON,
    DARK,
    FAIRY
}

