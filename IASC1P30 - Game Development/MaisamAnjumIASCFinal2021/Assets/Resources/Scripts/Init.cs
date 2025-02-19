using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;



public class Init : MonoBehaviour
{
    public TextAsset dungeonFile;                                                           // we input the required file into the Init script

    // Start is called before the first frame update
    void Start()
    {
        string[] content = dungeonFile.text.Split(',');
        int xSize = int.Parse(content[0]);
        int ySize = int.Parse(content[1]);
        int numLinks = int.Parse(content[2]);
        string[,] dungeonText = new string[ySize, xSize];
        GameObject[] targets = new GameObject[numLinks];
        GameObject[] sources = new GameObject[numLinks];


        for (int i = 0; i < ySize; i++)
        {
            for (int j = 0; j < xSize; j++)
            {
                dungeonText[i, j] = content[i * xSize + j + 3].Trim();
            }
        }
        GameObject wall = Resources.Load<GameObject>("prefabs/Wall");
        GameObject pillar = Resources.Load<GameObject>("prefabs/Pillar");
        GameObject player = Resources.Load<GameObject>("prefabs/Player");
        GameObject door = Resources.Load<GameObject>("prefabs/Door");
        GameObject skeleton = Resources.Load<GameObject>("prefabs/Skeleton");
        GameObject arrowPickUp = Resources.Load<GameObject>("prefabs/ArrowPickUp");
        GameObject key = Resources.Load<GameObject>("prefabs/Key");

        GameObject treasure = Resources.Load<GameObject>("prefabs/Treasure_Chest");       // setting up treasure chest
        GameObject giganticRock = Resources.Load<GameObject>("giganticRock");             // setting up gigantic rock which we created a prefab for
        GameObject pressurePlate = Resources.Load<GameObject>("prefabs/pressureplate");   //setting up pressure plate which we created a prefab for
        GameObject statue = Resources.Load<GameObject>("prefabs/Statue");                 //setting up Statue
        GameObject KingSkeleton = Resources.Load<GameObject>("prefabs/KingSkeleton");



        Material floorMat = Resources.Load<Material>("Materials/FloorMaterial");                 // we need to create the ceiling and floor materials for this to load
        Material ceilingMat = Resources.Load<Material>("Materials/CeilingMaterial");



        floorMat.mainTextureScale = new Vector2(xSize, ySize);
        ceilingMat.mainTextureScale = new Vector2(xSize, ySize);
        GameObject floor = GameObject.CreatePrimitive(PrimitiveType.Plane);
        floor.transform.localScale = new Vector3(xSize / 2.5f, 1, ySize / 2.5f);
        floor.GetComponent<Renderer>().material = floorMat;
        floor.transform.position = new Vector3(2 * (xSize - 1), 0, 2 * (ySize - 1));
        GameObject ceiling = GameObject.CreatePrimitive(PrimitiveType.Plane);
        ceiling.transform.localScale = new Vector3(xSize / 2.5f, 1, ySize / 2.5f);
        ceiling.GetComponent<Renderer>().material = ceilingMat;
        ceiling.transform.position = new Vector3(2 * (xSize - 1), 4, 2 * (ySize - 1));
        ceiling.transform.Rotate(new Vector3(180, 0, 0));
        for (int i = 0; i < dungeonText.GetLength(0); i++)
        {
            for (int j = 0; j < dungeonText.GetLength(1); j++)
            {
                string[] entry = dungeonText[i, j].Split(':');
                int x = 4 * j;
                int y = 4 * (ySize - i - 1);
                switch (entry[0])
                {
                    case "W":                                                                // created the wall prefab (4*4*4 with wall texture)
                        Instantiate(wall, new Vector3(x, 2, y), Quaternion.identity);
                        break;
                    case "P":
                        Instantiate(pillar, new Vector3(x, 2, y), Quaternion.identity);
                        break;
                    case "PL":
                        Direction direction = (Direction)System.Enum.Parse(typeof(Direction), entry[1]);
                        Quaternion rotation = Quaternion.identity;
                        switch (direction)
                        {
                            case Direction.East:
                                rotation = Quaternion.Euler(new Vector3(0, 90, 0));
                                break;
                            case Direction.South:
                                rotation = Quaternion.Euler(new Vector3(0, 180, 0));
                                break;
                            case Direction.West:
                                rotation = Quaternion.Euler(new Vector3(0, -90, 0));
                                break;
                            case Direction.North:
                                rotation = Quaternion.Euler(new Vector3(0, 0, 0));            // add a direction north such that we have a full range of motion
                                break;
                        }
                        Instantiate(player, new Vector3(x, 2, y), rotation);
                        break;
                    case "D":                                                                 // added sound capabilities to door, arrow, pick up arrow 
                        GameObject doorGameObject = Instantiate(door, new Vector3(x, 2, y), Quaternion.identity);
                        Door myDoor = doorGameObject.GetComponent<Door>();
                        myDoor.initialDirection = (Direction)System.Enum.Parse(typeof(Direction), entry[1]);
                        bool locked = bool.Parse(entry[2]);
                        myDoor.isInitiallyLocked = locked;
                        if (locked)
                        {
                            int link = int.Parse(entry[3]);
                            Door.UnlockAction action = (Door.UnlockAction)System.Enum.Parse(typeof(Door.UnlockAction), entry[4]);
                            string message = entry[5];
                            targets[link] = doorGameObject;
                            myDoor.lockAction = action;
                            myDoor.lockedMessage = message;
                        }
                        break;
                    case "S":
                        Instantiate(skeleton, new Vector3(x, 0, y), Quaternion.identity);
                        break;

                    case "KS":
                        Instantiate(KingSkeleton, new Vector3(x, 0, y), Quaternion.identity);                                   // set up king skeleton
                        break;

                    case "A":
                        Instantiate(arrowPickUp, new Vector3(x, 0, y), Quaternion.identity);                                    // added audio for arrowPickup
                        break;
                    case "K":
                        GameObject keyGameObject = Instantiate(key, new Vector3(x, 0, y), Quaternion.identity);
                        int link2 = int.Parse(entry[1]);
                        sources[link2] = keyGameObject;
                        break;
                    case "T":
                        GameObject treasureGameObject = Instantiate(key, new Vector3(x, 0, y), Quaternion.identity);           // place treasure
                        break;

                    case "R":
                        GameObject giganticRockGamObject = Instantiate(key, new Vector3(x, 0, y), Quaternion.identity);        // place Gigantic Rock

                         break;

                    case "PP":
                        GameObject pressurePlateGameObject = Instantiate(key, new Vector3(x, 0, y), Quaternion.identity);      // place pressure plate
                        break;


                    case "ST":
                        GameObject PuzzleStatueGameObject = Instantiate(key, new Vector3(x, 0, y), Quaternion.identity);       // place puzzle statue
                        break;

                    case "PM":
                        GameObject PuzzleManagerGameObject = Instantiate(key, new Vector3(x, 0, y), Quaternion.identity);      // set up puzzle manager
                        break;

                    case "EX":                                                             
                        break;

                    case "E":                                                               
                        break;
                }
            }
        }
        for (int i = 0; i < numLinks; i++)
        {
            sources[i].GetComponent<Trigger>().setTriggeredObject(targets[i]);
        }
    }
}