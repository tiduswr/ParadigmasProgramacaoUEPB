package com.tiduswr.model;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class CardsReader {

    public static BufferedImage cardBack(){
        try{
            return ImageIO.read(CardsReader.class.getClassLoader().getResourceAsStream("cards/back.png"));
        }catch(IOException e){
            throw new RuntimeException(e.getLocalizedMessage());
        }
    } 

    public static BufferedImage selectionIcon(){
        try{
            return ImageIO.read(CardsReader.class.getClassLoader().getResourceAsStream("hand.png"));
        }catch(IOException e){
            throw new RuntimeException(e.getLocalizedMessage());
        }
    } 

    public static List<CardData> readCardsFromCSV() {
        List<CardData> cardList = new ArrayList<>();
        String line = "";
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(CardsReader.class.getClassLoader().getResourceAsStream("cards.csv")))) {
            br.readLine();

            while ((line = br.readLine()) != null) {    
                System.out.println("Loading: " + line);
                String[] cardData = line.split(";");
                CardData card = new CardData();
                card.setName(cardData[1]);
                card.setCardId(Integer.parseInt(cardData[0]));
                card.setUp(Integer.parseInt(cardData[2]));
                card.setLeft(Integer.parseInt(cardData[3]));
                card.setDown(Integer.parseInt(cardData[4]));
                card.setRight(Integer.parseInt(cardData[5]));
                card.setImage(ImageIO.read(CardsReader.class.getClassLoader().getResourceAsStream("cards/" + cardData[0] + ".png")));
                card.setType(cardData[6]);
                switch (cardData[6]) {
                    case "WIND":
                    case "WATER":
                    case "EARTH":
                    case "THUNDER":
                    case "POISON":
                    case "ICE":
                    case "HOLY":
                    case "FIRE":
                        card.setTypeIcon(ImageIO.read(CardsReader.class.getClassLoader().getResourceAsStream("cards/" + cardData[6].toLowerCase() + ".png")));
                        break;
                    default:
                        break;
                }

                
                cardList.add(card);
            }

        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());;
        }

        return cardList;
    }
}
