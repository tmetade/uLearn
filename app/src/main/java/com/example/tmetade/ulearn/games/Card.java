package com.example.tmetade.ulearn.games;

public class Card
{
    private String name;
    private String imgCard;
    private String pair;

    public Card (String name, String imgCard, String pair)
    {
        this.name = name;
        this.imgCard = imgCard;
        this.pair = pair;
    }

    public String getName()
    {
        return name;
    }

    public String getImgCard()
    {
        return imgCard;
    }

    public String getPair()
    {
        return pair;
    }

    public void setImgCard(String imgCard)
    {
        this.imgCard = imgCard;
    }

    public void setPair(String pair)
    {
        this.pair = pair;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
