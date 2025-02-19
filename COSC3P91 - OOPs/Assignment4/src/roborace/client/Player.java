package roborace.client;

import COSC3P91.midi.MidiManager;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.StringReader;
import javax.swing.JFrame;
import roborace.common.GameDialogs;
import roborace.common.Port;

public class Player extends JFrame implements Runnable {
    
    private final int playerID;
    private final Port port;
    private AnimatedBoard board;
    private BoardCanvas boardCanvas;
    private CardPane cardPane;
    private InfoPane infoPane;
    private final MidiManager midiManager;

    public Player(int playerID, Port port) {
        this.playerID = playerID;
        board = null;
        this.port = port;
        midiManager = MidiManager.getInstance();
        midiManager.setMidiPath("./Sounds&Midi/");
        super.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close();
            }
        });
    }

    private void init() {
        String playerName = board.getRobotByID(playerID).getName();
        setTitle("Player: " + playerName);
        boardCanvas = new BoardCanvas(board);
        SelectDoneButton okButton = new SelectDoneButton();
        MusicButton musicButton = new MusicButton();
        cardPane = new CardPane(okButton,this);
        infoPane = new InfoPane(240,boardCanvas.getHeight(),playerName);
        SingleStepButton singleStepButton = new SingleStepButton(infoPane);
        NextStepButton nextStepButton = new NextStepButton(infoPane);
        Container content = getContentPane();
        GridBagLayout layout = new GridBagLayout();
        content.setLayout(layout);
        content.setBackground(Color.GRAY);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        layout.setConstraints(boardCanvas, constraints);
        content.add(boardCanvas);
        constraints.gridwidth = 3;
        constraints.gridx = 2;
        constraints.gridy = 0;
        layout.setConstraints(infoPane, constraints);
        content.add(infoPane);
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        layout.setConstraints(cardPane, constraints);
        content.add(cardPane);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        layout.setConstraints(okButton, constraints);
        content.add(okButton);     
        constraints.gridx = 2;
        constraints.gridy = 1;
        layout.setConstraints(musicButton, constraints);
        content.add(musicButton);      
        constraints.gridx = 3;
        constraints.gridy = 1;
        layout.setConstraints(singleStepButton, constraints);
        content.add(singleStepButton);      
        constraints.gridx = 4;
        constraints.gridy = 1;
        layout.setConstraints(nextStepButton, constraints);
        content.add(nextStepButton);
        pack();
        setResizable(false);
        setVisible(true);
        boardCanvas.start();
    }

    @Override
    public void run() {
        board = AnimatedBoard.read(new StringReader(port.receive()));
        init();
        CardList cards;
        EventList events = new EventList();
        while (!events.containsVictory()) {
            cards = CardList.read(new StringReader(port.receive()));
            cards = cardPane.selectCards(cards);
            port.send(cards.toXMLString());
            events = EventList.read(new StringReader(port.receive()));
            infoPane.resetPhase();
            events.execute(board,infoPane);
        }
        int winnerID = events.getWinnerID();
        if (winnerID == playerID) {
            GameDialogs.showMessageDialog("End of Game","You have won the game!!!",this);
        } else {
            GameDialogs.showMessageDialog("End of Game","You have lost.\n The winner is " + board.getRobotByID(winnerID).getName() + "!!!",this);	
        }
        close();
    }
    
    private void close() {
        if (boardCanvas != null) {
            boardCanvas.stop();
        }
        midiManager.close();
        try {
            port.close();
        } catch (IOException e) {}
        setVisible(false);
        dispose();
        System.exit(0);
    }
}