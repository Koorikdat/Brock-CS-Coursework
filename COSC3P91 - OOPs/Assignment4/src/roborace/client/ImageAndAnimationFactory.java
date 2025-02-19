package roborace.client;

import COSC3P91.graphics.ImageManager;
import COSC3P91.graphics.Animation;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import roborace.common.Direction;

public class ImageAndAnimationFactory {
    
    private static Image logo;
    private static Image musicOff;
    private static Image musicOn;
    private static Image selectDone;
    private static Image singleStepOn;
    private static Image singleStepOff;
    private static Image nextStep;
    
    private static Image noCard;
    private static Image cardBack;
    private static Image cardMove;
    private static Image cardTurn90Clockwise;
    private static Image cardTurn90CounterClockwise;
    private static Image cardTurn180;
    
    private static Image goal;
    private static Image pit;
    private static Image wallVertical;
    private static Image wallHorizontal;
    
    private static Image[] select;
    private static Image[] floor;
	
    private static Image[] beltNorth;
    private static Image[] beltEast;
    private static Image[] beltSouth;
    private static Image[] beltWest;
    private static Image[] gearClockwise;
    private static Image[] gearCounterClockwise;
    private static Image[] robots;
    private static Image[] explosion;
    private static Image[] crusher;
    private static Image[] pusherNorth;
    private static Image[] pusherEast;
    private static Image[] pusherSouth;
    private static Image[] pusherWest;

    
    private static final int SELECT_IMAGES = 5;
    private static final int FLOOR_IMAGES = 6;
    private static final int BELT_IMAGES = 38;
    private static final int GEAR_IMAGES = 18;
    private static final int ROBOT_IMAGES = 128;
    private static final int EXPLOSION_IMAGES = 23;
    private static final int PUSHER_IMAGES = 20;
    private static final int CRUSHER_IMAGES = 11;

    private static ImageAndAnimationFactory instance = null;

    public static ImageAndAnimationFactory getInstance() {
        if (instance==null) {
            instance = new ImageAndAnimationFactory();
        }
        return instance;
    }

    private ImageAndAnimationFactory() {
        ImageManager manager = ImageManager.getInstance();
        
        logo = manager.loadImage("Miscellaneous/logo.gif");
        musicOff = manager.loadImage("Miscellaneous/MusicOff.png");
        musicOn = manager.loadImage("Miscellaneous/MusicOn.png");
        selectDone = manager.loadImage("Miscellaneous/DoneSelect.png");
        singleStepOn = manager.loadImage("Miscellaneous/SingleStepOn.png");
        singleStepOff = manager.loadImage("Miscellaneous/SingleStepOff.png");
        nextStep = manager.loadImage("Miscellaneous/NextStep.png");      
        noCard = manager.loadImage("Cards/NoCard.png");
        cardBack = manager.loadImage("Cards/Back.png");
        cardMove = manager.loadImage("Cards/Move.png");
        cardTurn90Clockwise = manager.loadImage("Cards/Turn90Clockwise.png");
        cardTurn90CounterClockwise = manager.loadImage("Cards/Turn90CounterClockwise.png");
        cardTurn180 = manager.loadImage("Cards/Turn180.png");        
        goal = manager.loadImage("Goal/Goal.png");
        pit = manager.loadImage("Pit/Pit.png");	
        wallVertical = manager.loadImage("Walls/WallVertical.png");
        wallHorizontal = manager.loadImage("Walls/WallHorizontal.png");       
        select = new Image[SELECT_IMAGES];
        for(int i=0; i<SELECT_IMAGES; i++) {
            select[i] = manager.loadImage("Cards/Select" + (i+1) + ".png");
        }
        floor = new Image[FLOOR_IMAGES];
        for(int i=0; i<FLOOR_IMAGES; i++) {
            floor[i] = manager.loadImage("Floor/Floor" + i + ".png");
        }       
        beltNorth = new Image[BELT_IMAGES];
        for(int i=0; i<BELT_IMAGES; i++) {
            beltNorth[i] = manager.loadImage("BeltNorth/Belt" + (i<10?"0"+i:i) + ".png");
        }
        beltEast = new Image[BELT_IMAGES];
        for(int i=0; i<BELT_IMAGES; i++) {
            beltEast[i] = manager.loadImage("BeltEast/Belt" + (i<10?"0"+i:i) + ".png");
        }
        beltSouth = new Image[BELT_IMAGES];
        for(int i=0; i<BELT_IMAGES; i++) {
            beltSouth[i] = manager.loadImage("BeltSouth/Belt" + (i<10?"0"+i:i) + ".png");
        }
        beltWest = new Image[BELT_IMAGES];
        for(int i=0; i<BELT_IMAGES; i++) {
            beltWest[i] = manager.loadImage("BeltWest/Belt" + (i<10?"0"+i:i) + ".png");
        }
        gearClockwise = new Image[GEAR_IMAGES];
        for(int i=0; i<GEAR_IMAGES; i++) {
            gearClockwise[i] = manager.loadImage("GearClockwise/Gear" + (i<10?"0"+i:i) + ".png");
        }
        gearCounterClockwise = new Image[GEAR_IMAGES];
        for(int i=0; i<GEAR_IMAGES; i++) {
            gearCounterClockwise[i] = manager.loadImage("GearCounterClockwise/Gear" + (i<10?"0"+i:i) + ".png");
        }
        robots = new Image[ROBOT_IMAGES];
        for(int i=0; i<ROBOT_IMAGES; i++) {
            robots[i] = manager.loadImage("Robot/robot" + i + ".png");
        }
        explosion = new Image[EXPLOSION_IMAGES];
        for(int i=0; i<EXPLOSION_IMAGES; i++) {
            explosion[i] = manager.loadImage("Explosion/Explosion" + i + ".png");
        }
        pusherNorth = new Image[PUSHER_IMAGES];
        for(int i=0; i<PUSHER_IMAGES; i++) {
            pusherNorth[i] = manager.loadImage("PusherNorth/Pusher" + (i<10?"0"+i:i) + ".png");
        }
        pusherEast = new Image[PUSHER_IMAGES];
        for(int i=0; i<PUSHER_IMAGES; i++) {
            pusherEast[i] = manager.loadImage("PusherEast/Pusher" + (i<10?"0"+i:i) + ".png");
        }
        pusherSouth = new Image[PUSHER_IMAGES];
        for(int i=0; i<PUSHER_IMAGES; i++) {
            pusherSouth[i] = manager.loadImage("PusherSouth/Pusher" + (i<10?"0"+i:i) + ".png");
        }
        pusherWest = new Image[PUSHER_IMAGES];
        for(int i=0; i<PUSHER_IMAGES; i++) {
            pusherWest[i] = manager.loadImage("PusherWest/Pusher" + (i<10?"0"+i:i) + ".png");
        }
        crusher = new Image[CRUSHER_IMAGES];
        for(int i=0; i<CRUSHER_IMAGES; i++) {
            crusher[i] = manager.loadImage("Crusher/Crusher" + i + ".png");
        }
    }
    
    public Image getLogoImage() {
        return logo;
    }
    
    public Image getMusicOffImage() {
        return musicOff;
    }
    
    public Image getMusicOnImage() {
        return musicOn;
    }
    
    public Image getSelectDoneImage() {
        return selectDone;
    }
    
    public Image getSingleStepOnImage() {
        return singleStepOn;
    }
    
    public Image getSingleStepOffImage() {
        return singleStepOff;
    }
    
    public Image getNextStepImage() {
        return nextStep;
    }
    
    public Image getNoCardImage() {
        return noCard;
    }
    
    public Image createCardBack(int priority) {
        Image result = cloneImage(cardBack);
        Graphics g = result.getGraphics();
        g.drawString("Priority: "+ priority,15,15);
        g.dispose();
        return result;
    }
    
    public Image createCardMove(int priority, int steps) {
        Image result = cloneImage(cardMove);
        Graphics g = result.getGraphics();
        g.drawString("Priority: "+ priority,15,15);
        g.drawString("Move: " + steps,25,120);
        g.dispose();
        return result;
    }
    
    public Image createCardTurn90(int priority, boolean clockwise) {
        Image result;
        if (clockwise) {
            result = cloneImage(cardTurn90Clockwise);
        } else {
            result = cloneImage(cardTurn90CounterClockwise);
        }
        Graphics g = result.getGraphics();
        g.drawString("Priority: "+ priority,15,15);
        g.dispose();
        return result;
    }
    
    public Image createCardTurn180(int priority) {
        Image result = cloneImage(cardTurn180);
        Graphics g = result.getGraphics();
        g.drawString("Priority: "+ priority,15,15);
        g.dispose();
        return result;
    }
    
    public Animation createGoalAnimation() {
        Animation animation = new Animation(true);
        animation.addFrame(goal,1000);
        return animation;
    }
    
    public Animation createPitAnimation() {
        Animation animation = new Animation(true);
        animation.addFrame(pit,1000);
        return animation;
    }
    
    public Image getVerticalWallImage() {
        return wallVertical;
    }
    
    public Image getHorizontalWallImage() {
        return wallHorizontal;
    }
    
    public Image[] getSelectImages() {
        return select;
    }
    
    public Animation createFloorAnimation() {
        Animation animation = new Animation(true);
        int rand = (int) Math.floor(6*Math.random());
        animation.addFrame(floor[rand],1000);
        return animation;
    };

    public Animation createBeltAnimation(Direction direction) {
        Animation animation = new Animation(true);
        Image[] images = null;
        switch(direction) {
            case North: 
                images = beltNorth;
                break;
            case East:  
                images = beltEast;
                break;
            case South:	
                images = beltSouth;
                break;
            case West:  
                images = beltWest;
        }
        for(int i=0; i<BELT_IMAGES; i++) {
            if (i==19) {
                animation.addFrame(images[i],200);
            } else { 
                animation.addFrame(images[i],20);
            }
        }
        animation.setStartFrame((int) Math.floor(BELT_IMAGES*Math.random()));
        return animation;
    }

    public Animation createGearAnimation(boolean clockwise) {
        Animation animation = new Animation(true);
        Image[] images;
        if (clockwise) { 
            images = gearClockwise;
        } else {
            images = gearCounterClockwise;
        }
        for(int i=0; i<GEAR_IMAGES; i++) {
            animation.addFrame(images[i],20);
        }
        animation.setStartFrame((int) Math.floor(GEAR_IMAGES*Math.random()));
        return animation;
    }
    
    public Animation[] createRobotAnimations(String name) {
        Image[] images = new Image[ROBOT_IMAGES];
        for(int i=0; i<ROBOT_IMAGES; i++)
            images[i] = addName(robots[i],name);
        Animation[] result = new Animation[28];
        for(int i=0; i<28; i++)
            result[i] = new Animation((i>=12 && i<=19) || (i>=24));
        for(int i=0; i<=18; i++) {
            int len = i==0||i==18?30:25;
            result[0].addFrame(images[i],len);
            result[1].addFrame(images[i+18],len);
            result[2].addFrame(images[i+36],len);
            if (i==18) {
                result[3].addFrame(images[0],len);
            } else {
                result[3].addFrame(images[i+54],len);
            }
            if (i==0) {
                result[4].addFrame(images[0],len);
            } else {
                result[4].addFrame(images[72-i],len);
            }
            result[5].addFrame(images[54-i],len);	
            result[6].addFrame(images[36-i],len);
            result[7].addFrame(images[18-i],len);
        }
        for(int i=0; i<=36; i++) {
            int len = i==0||i==36?30:25;
            result[8].addFrame(images[i],len);
            result[9].addFrame(images[i+18],len);
            if (i==36) {
                result[10].addFrame(images[0],len);
            } else {
                result[10].addFrame(images[i+36],len);
            }
            if (i<18) {
                result[11].addFrame(images[i+54],len);
            } else {
                result[11].addFrame(images[i-18],len);
            }
        }
        result[12].addFrame(images[0],5000);
        result[12].addFrame(images[72],20);
        result[12].addFrame(images[73],1000);
        result[12].addFrame(images[72],20);
        result[12].addFrame(images[0],20);
        result[12].addFrame(images[74],20);
        result[12].addFrame(images[75],1000);
        result[12].addFrame(images[74],20);
        result[12].addFrame(images[0],5000);      
        result[13].addFrame(images[18],5000);
        result[13].addFrame(images[76],20);
        result[13].addFrame(images[77],1000);
        result[13].addFrame(images[76],20);
        result[13].addFrame(images[18],20);
        result[13].addFrame(images[78],20);
        result[13].addFrame(images[79],1000);
        result[13].addFrame(images[78],20);
        result[13].addFrame(images[18],5000);       
        result[14].addFrame(images[36],5000);
        result[14].addFrame(images[80],20);
        result[14].addFrame(images[81],1000);
        result[14].addFrame(images[80],20);
        result[14].addFrame(images[36],20);
        result[14].addFrame(images[82],20);
        result[14].addFrame(images[83],1000);
        result[14].addFrame(images[82],20);
        result[14].addFrame(images[36],5000);     
        result[15].addFrame(images[54],5000);
        result[15].addFrame(images[84],20);
        result[15].addFrame(images[85],1000);
        result[15].addFrame(images[84],20);
        result[15].addFrame(images[54],20);
        result[15].addFrame(images[86],20);
        result[15].addFrame(images[87],1000);
        result[15].addFrame(images[86],20);
        result[15].addFrame(images[54],5000);     
        result[16].addFrame(images[0],50);
        result[16].addFrame(images[88],385);
        result[16].addFrame(images[0],50);      
        result[17].addFrame(images[18],50);
        result[17].addFrame(images[89],385);
        result[17].addFrame(images[18],50);      
        result[18].addFrame(images[36],50);
        result[18].addFrame(images[90],385);
        result[18].addFrame(images[36],50);     
        result[19].addFrame(images[54],50);
        result[19].addFrame(images[91],385);
        result[19].addFrame(images[54],50);
        for(int i=0; i<=22; i++) {
            result[20].addFrame(addExplosion(images[0],explosion[i]),20);
            result[21].addFrame(addExplosion(images[18],explosion[i]),20);
            result[22].addFrame(addExplosion(images[36],explosion[i]),20);
            result[23].addFrame(addExplosion(images[54],explosion[i]),20);
        }
        for(int i=0; i<9; i++) {
            result[24].addFrame(images[92+i],100);
            result[25].addFrame(images[101+i],100);
            result[26].addFrame(images[110+i],100);
            result[27].addFrame(images[119+i],100);
        }
        return result;
    }
    
    public DecorationAnimation createCrusherAnimation(int x, int y, int phase1, int phase2) {
        DecorationAnimation animation = new DecorationCrusherAnimation(x,y,phase1,phase2);
        for(int i=0; i<CRUSHER_IMAGES; i++) {
            animation.addFrame(crusher[i],20);
        }
        for(int i=0; i<CRUSHER_IMAGES; i++) {
            animation.addFrame(crusher[10-i],10);
        }
        return animation;
    }
     
    public DecorationAnimation createPusherAnimation(int x, int y, Direction direction, int phase1, int phase2, int phase3) {
        DecorationAnimation animation = new DecorationPusherAnimation(x,y,phase1,phase2,phase3);
        switch (direction) {
            case North: 
                for(int i=0; i<PUSHER_IMAGES; i++) {
                    animation.addFrame(pusherNorth[i],20);
                }
                for(int i=0; i<PUSHER_IMAGES; i++) {
                    animation.addFrame(pusherNorth[PUSHER_IMAGES-1-i],20);
                }
                break;
            case East:  
                for(int i=0; i<PUSHER_IMAGES; i++) {
                    animation.addFrame(pusherEast[i],20);
                }
                for(int i=0; i<PUSHER_IMAGES; i++) {
                    animation.addFrame(pusherEast[PUSHER_IMAGES-1-i],20);
                }
                break;
            case South: 
                for(int i=0; i<PUSHER_IMAGES; i++) {
                    animation.addFrame(pusherSouth[i],20);
                }
                for(int i=0; i<PUSHER_IMAGES; i++) {
                    animation.addFrame(pusherSouth[PUSHER_IMAGES-1-i],20);
                }
                break;
            case West:  
                for(int i=0; i<PUSHER_IMAGES; i++) {
                    animation.addFrame(pusherWest[i],20);
                }
                for(int i=0; i<PUSHER_IMAGES; i++) {
                    animation.addFrame(pusherWest[PUSHER_IMAGES-1-i],20);
                }
        }
        return animation;
    }
    
    private Image addName(Image image, String name) {
        Image result = cloneImage(image);
        Graphics graphics = result.getGraphics();
        graphics.setColor(Color.red);
        graphics.drawString(name,10,64);
        graphics.dispose();
        return result;
    }
    
    private Image addExplosion(Image robotImage, Image explosionImage) {
        Image result = cloneImage(robotImage);
        Graphics graphics = result.getGraphics();
        graphics.drawImage(explosionImage,0,0,null);
        graphics.dispose();
        return result;
    }

    private Image cloneImage(Image image) {
        BufferedImage result = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = result.createGraphics();
        graphics.drawImage(image,0,0,null);
        graphics.dispose();
        return result;
    }
}