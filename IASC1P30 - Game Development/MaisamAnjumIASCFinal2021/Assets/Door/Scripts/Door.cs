using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Door : MonoBehaviour, TriggeredObject
{
    public float Speed = 20;
    public float lockSpeedFactor = 3;
    public string lockedMessage = "Door is locked.";
    public float messageDisplayTime = 10;
    public Direction initialDirection = Direction.South;
    public bool isInitiallyLocked = false;
    public enum UnlockAction { Enable, OpenEnable };
    public UnlockAction lockAction = UnlockAction.Enable;
    public AudioClip[] clips = new AudioClip[2];
    private enum States { Closed, Closing, Open, Opening, Locked, LockedClosing };
    private States State;
    private Vector3 pivot;
    private Transform doorPanelTrans;
    private Vector3 closedPosition, closedAngles, openPosition, openAngles;
    private float remainingDisplayTime = 0;
    private bool inRange = false;
    private AudioSource audioPlayer;

    [SerializeField] private AudioClip Door_slamming;           // plays when the door shuts\
    [SerializeField] private AudioClip Door_moving;           // plays when the door shuts


    // Start is called before the first frame update
    void Start()
    {
        transform.Rotate(new Vector3(0,90*(((int) initialDirection - 2) % 4), 0));
        doorPanelTrans = transform.GetChild(0);
        pivot = doorPanelTrans.position + doorPanelTrans.right;
        closedPosition = doorPanelTrans.position;
        closedAngles = doorPanelTrans.eulerAngles;
        doorPanelTrans.RotateAround(pivot, -Vector3.up, 80);
        openPosition = doorPanelTrans.position;
        openAngles = doorPanelTrans.eulerAngles;
        doorPanelTrans.position = closedPosition;
        doorPanelTrans.eulerAngles = closedAngles;
        if (isInitiallyLocked)
        {
            State = States.Locked;
        } else
        {
            State = States.Closed;
        }
        audioPlayer = GetComponent<AudioSource>();      
    }

    // Update is called once per frame
    void Update()
    {
        switch (State)
        {
            case States.Closed:
                if (inRange && Input.GetButtonDown("Interact"))
                {
                    State = States.Opening;
                    playDoorMoving();
                }
                break;
            case States.Closing:
                doorPanelTrans.RotateAround(pivot,Vector3.up,Speed*Time.deltaTime);
                if (!inBetween(doorPanelTrans.eulerAngles.y))
                {
                    State = States.Closed;
                    playDoorSlamming();
                }
                if (inRange && Input.GetButtonDown("Interact"))
                {
                    State = States.Opening;
                    playDoorMoving();
                }
                break;
            case States.Open:
                if (inRange && Input.GetButtonDown("Interact"))
                {
                    State = States.Closing;
                    playDoorMoving();
                }
                break;
            case States.Opening:
                doorPanelTrans.RotateAround(pivot,-Vector3.up,Speed*Time.deltaTime);
                if (!inBetween(doorPanelTrans.eulerAngles.y))
                {
                    State = States.Open;
                    audioPlayer.Stop();
                }
                if (inRange && Input.GetButtonDown("Interact"))   
                {
                    State = States.Closing;
                    playDoorMoving();
                }
                break;
            case States.Locked:
                if (inRange && Input.GetButtonDown("Interact"))
                {
                    remainingDisplayTime = messageDisplayTime;
                }
                break;
            case States.LockedClosing:
                doorPanelTrans.RotateAround(pivot,Vector3.up, lockSpeedFactor * Speed * Time.deltaTime);
                if (!inBetween(doorPanelTrans.eulerAngles.y))
                {
                    State = States.Locked;
                    playDoorSlamming();
                }
                if (inRange && Input.GetButtonDown("Interact"))
                {
                    remainingDisplayTime = messageDisplayTime;
                }
                break;
        }
    }

    public void doAction1()             // Unlocking
    {
        if (State == States.Locked || State == States.LockedClosing)
        {
            switch (lockAction)
            {
                case UnlockAction.Enable:
                    if (State == States.Locked)
                    {
                        State = States.Closed;
                    }
                    else
                    {
                        State = States.Closing;
                    }
                    break;
                case UnlockAction.OpenEnable:
                    State = States.Opening;
                    playDoorMoving();
                    break;
            }
        }
    }

    public void doAction2()             // Locking
    {
        if (State != States.Locked && State != States.LockedClosing)
        {
            if (State == States.Closed)
            {
                State = States.Locked;
            }
            else
            {
                State = States.LockedClosing;
                playDoorMoving();
            }
        }
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.tag == "Player")
        {
            inRange = true;
        }
    }

    private void OnTriggerExit(Collider other)
    {
        if (other.tag == "Player")
        {
            inRange = false;
        }
    }

    private void OnGUI()
    {
        if (State == States.Locked && remainingDisplayTime > 0)
        {
            GUI.Label(new Rect(Screen.width/2-50,Screen.height/2,100,20),lockedMessage);
            remainingDisplayTime -= Time.deltaTime;
        }
    }

    private bool inBetween(float angle)
    {
        float openAngle = openAngles.y;
        float closedAngle = closedAngles.y;
        if (openAngle < closedAngle)
        {
            return openAngle <= angle && angle <= closedAngle;
        }
        else
        {
            return openAngle <= angle || angle <= closedAngle;
        }
    }


    private void playDoorMoving()
    {
        if (audioPlayer.clip != clips[0])
        {
            audioPlayer.Stop();
            audioPlayer.clip = clips[0];
            audioPlayer.loop = true;
        }
        if (!audioPlayer.isPlaying)
        {
            audioPlayer.Play();
        }
    }

    private void playDoorSlamming()
    {
        if (audioPlayer.clip != clips[1])
        {
            audioPlayer.Stop();
            audioPlayer.clip = clips[1];
            audioPlayer.loop = false;
        }
        if (!audioPlayer.isPlaying)
        {
            audioPlayer.Play();
        }
    }
}
