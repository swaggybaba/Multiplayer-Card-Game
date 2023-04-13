import java.util.ArrayList;
import java.util.List;

public class Game {
    private int numberOfPlayers;
    private List<Player> players;
    Deck deck;
    Card lastCardOfDiscardPile;
    int playerTurn=0;
    int turnDirection=0;//0=0->1->2->3->0, 1=0->3->2->1->0
    public Game(int numberOfPlayers){
        this.numberOfPlayers=numberOfPlayers;
        this.deck=new Deck();
        players=new ArrayList<>();
        for(int index=0;index<numberOfPlayers;index++){
            players.add(new Player(index+1));
        }
        giveFiveCardsToEachPlayer();
    }

    public void startGame(){
        while(true){
            Player player=players.get(playerTurn);
            System.out.println("Turn of player : player id "+player.getPlayerId());
            player.printCards();
            System.out.println("Last Card: "+lastCardOfDiscardPile);
            int indOfMatchingCard=player.getIndexOfSameSuitOrFaceValue(lastCardOfDiscardPile);
            if(indOfMatchingCard==-1){
                if(deck.getSize()==0){
                    System.out.println("DRAW: No one win");
                    return;
                }
                System.out.println("Player don't have card of same Suit or same Face value");
                Card topCardOfDrawPile=deck.removeCardFromTop();
                System.out.println("Card pick up from Draw pile: "+ topCardOfDrawPile);
                if(topCardOfDrawPile.getSuit()==lastCardOfDiscardPile.getSuit()||topCardOfDrawPile.getFaceValue()==lastCardOfDiscardPile.getFaceValue()){
                    //if drawed card suit or face value matches with last card of discsrd pile no need to add that card to player card list
                    lastCardOfDiscardPile=topCardOfDrawPile;
                    System.out.println("Picked up card has same suit or same face value with last card of discard pile, So the card played is: "+ topCardOfDrawPile);
                    playerTurn=actionsCard(topCardOfDrawPile);
                    if(playerTurn==-1){
                        return;
                    }
                }
                else {
                    player.addCard(topCardOfDrawPile);
                    System.out.println("Card is added to player cards");
                }
            }
            else{
                Card playedCard=player.getCard(indOfMatchingCard);
                System.out.println("Played Card: "+playedCard);
                lastCardOfDiscardPile=player.getCard(indOfMatchingCard);
                player.removeCard(indOfMatchingCard);
                if(player.getPlayingCards().size()==0){
                    System.out.println("Player "+player.getPlayerId()+" wins the game");
                    return;
                }
                playerTurn=actionsCard(playedCard);
                if(playerTurn==-1){
                    return;
                }
            }
            player.printCards();
            System.out.println();
            playerTurn=getNextPlayer();
        }
    }

    private int actionsCard(Card playedCard){
        //Ace card is Played by player playerTurn
        if(playedCard.getFaceValue()==1){
            System.out.println("Ace played: Skipping Next Player");
            return getNextPlayer();
        }
        //Jack card is Played by player playerTurn
        else if(playedCard.getFaceValue()==11){
            System.out.println("Jack played");
            int nextPlayerTurn=getNextPlayer();
            Player nextPlayer=players.get(nextPlayerTurn);
            nextPlayer.printCards();
            for(int count=1;count<=2;count++){
                if(!addCardFromDrawPile(nextPlayer)){
                    System.out.println("Draw: No Card to withdraw from deck pile");
                    return -1;
                }
            }
            System.out.println("Two cards added for player id: "+nextPlayer.getPlayerId());
            nextPlayer.printCards();
            return nextPlayerTurn;
        }
        //Queen card is Played by player playerTurn
        else if(playedCard.getFaceValue()==12){
            System.out.println("Queen played");
            int nextPlayerTurn=getNextPlayer();
            Player nextPlayer=players.get(nextPlayerTurn);
            nextPlayer.printCards();
            for(int count=1;count<=4;count++){
                if(!addCardFromDrawPile(nextPlayer)){
                    System.out.println("Draw: No Card to withdraw from deck pile");
                    return -1;
                }
            }
            System.out.println("Four cards added for player id: "+nextPlayer.getPlayerId());
            nextPlayer.printCards();
            return nextPlayerTurn;
        }
        //King card is Played by player playerTurn
        else if(playedCard.getFaceValue()==13){
            System.out.println("King played");
            System.out.print("Direction Changed from "+turnDirection);
            turnDirection=1-turnDirection;
            System.out.print(" To "+turnDirection);
            System.out.println();
        }
        return playerTurn;
    }
    private boolean addCardFromDrawPile(Player player){
        Card topCard=deck.removeCardFromTop();
        if(topCard==null){
            return false;
        }
        player.addCard(topCard);
        return true;
    }
    private int getNextPlayer(){
        if(turnDirection==0){
            playerTurn++;
        }
        else{
            playerTurn--;
        }
        return (playerTurn+numberOfPlayers)%numberOfPlayers;
    }
    private void giveFiveCardsToEachPlayer(){
        Deck deck=new Deck();
        int index=deck.getCards().size()-1;
        for(int count=1;count<=5;count++){
            for(int playerNumber=0;playerNumber<numberOfPlayers;playerNumber++){
                players.get(playerNumber).addCard(deck.getCard(index--));
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
