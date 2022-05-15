package it.polimi.ingsw.clientModels;

import it.polimi.ingsw.client.View;
import it.polimi.ingsw.model.CharactersEnum;
import it.polimi.ingsw.model.Colour;
import it.polimi.ingsw.server.ModelSerializer;

import java.util.HashMap;

public class ClientCharacter implements ClientModel{

    int ID;
    private final CharactersEnum card;
    private int price;
    private final HashMap<Colour, Integer> students;

    public ClientCharacter(int ID, CharactersEnum card, int price)
    {
        this.ID = ID;
        this.card = card;
        this.price = price;
        this.students = null;
    }

    public ClientCharacter(int ID, CharactersEnum card, int price,HashMap<Colour, Integer> students)
    {
        this.ID = ID;
        this.card = card;
        this.price = price;
        this.students = students;
    }

    public int getID() {
        return ID;
    }

    public CharactersEnum getCard() {
        return card;
    }

    public int getPrice() {
        return price;
    }

    public HashMap<Colour, Integer> getStudents() {
        return students;
    }

    @Override
    public void accept(View visitor) {
        visitor.visit(this);
    }

    @Override
    public String serialize(){
        return ModelSerializer.serialize(this);
    }
}
