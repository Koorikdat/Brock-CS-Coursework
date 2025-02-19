package roborace.server;

public class CardFactory {

    public Card createCard(int playerID) {
        Card result = null;
        int r = (int) Math.floor(84*Math.random()+1);
        if (r<=18) {
            result = new CardTurn90(r,playerID,true);
        }
        if (19<=r && r<=36) {
            result = new CardTurn90(r,playerID,false);
        }
        if (37<=r && r<=42) {
            result = new CardTurn180(r,playerID);
        }
        if (43<= r && r<=48) {
            result = new CardBack(r,playerID);
        }
        if (49<=r && r<=66) {
            result = new CardMove(r,playerID,1);
        }
        if (67<=r && r<=78) {
            result = new CardMove(r,playerID,2);
        }
        if (79<=r && r<=84) {
            result = new CardMove(r,playerID,3);
        }
        return result;
    }
}