import java.util.ArrayList;
import java.util.List;

public class Game {
    private int numberOfPlayers;
    private List<Player> players;

    public Game(int numberOfPlayers){
        this.numberOfPlayers=numberOfPlayers;
        players=new ArrayList<>();
        for(int index=0;index<numberOfPlayers;index++){
            players.add(new Player(index+1));
        }
        giveFiveCardsToEachPlayer();
    }
    private void giveFiveCardsToEachPlayer(){
        Deck deck=new Deck();
        List<Card> cards=deck.getCards();
        int index=cards.size()-1;
        for(int count=1;count<=5;count++){
            for(int playerNumber=0;playerNumber<numberOfPlayers;playerNumber++){
                players.get(playerNumber).getPlayingCards().add(cards.get(index--));
            }
        }
        printCardOfPlayers();
    }

    private void printCardOfPlayers(){
        for(Player player:players){
            player.printCards();
        }
    }
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
