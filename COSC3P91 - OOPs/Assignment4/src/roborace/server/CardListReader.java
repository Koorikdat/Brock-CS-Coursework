package roborace.server;

import COSC3P91.xml.XMLNodeConverter;
import java.util.List;
import org.w3c.dom.Node;

import static COSC3P91.xml.XMLTools.getChildNodes;

public class CardListReader implements XMLNodeConverter<CardList> {
	
    private final CardReader cardReader;

    public CardListReader() {
        this.cardReader = new CardReader();
    }

    @Override
    public CardList convertXMLNode(Node node) {
        CardList result = new CardList();
        if (node.getNodeName().equals("CardList")) {
            List<Node> list = getChildNodes(node);
            for(Node n : list) {
                result.add(cardReader.convertXMLNode(n));
            }
        }
        return result;	
    }	
}