package roborace.server;

import COSC3P91.xml.XMLObject;
import COSC3P91.xml.XMLReader;
import java.io.Reader;
import java.util.LinkedList;

public class CardList extends LinkedList<Card> implements XMLObject {
	
    public static CardList read(Reader input) {
        XMLReader<CardList> reader = new XMLReader<CardList>();
        reader.setXMLSchema("CardList.xsd");
        reader.setXMLNodeConverter(new CardListReader());
        return reader.readXML(input);
    }

    @Override
    public String toXMLString() {
        String result = "<CardList>\n";
        for(Card card : this) {
            result += card.toXMLString() + "\n";
        }
        return result + "</CardList>";
    }	
}