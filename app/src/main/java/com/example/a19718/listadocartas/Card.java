package com.example.a19718.listadocartas;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 19718 on 21/10/16.
 */

public class Card implements Serializable {

    private String name;
    private String rarity;
    private String text;
    private String type;
    private String color;
    private String urlImage;
    private ArrayList<String> cards;

    public Card() {
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public ArrayList<String> getCards() {
        return cards;
    }

    public void setCards(ArrayList<String> cards) {
        this.cards = cards;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", rarity='" + rarity + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", cards=" + cards +
                '}';
    }
}
