using System;
using System.Collections;
using System.Collections.Generic;
using UnityEditorInternal;
using UnityEngine;

public class Statue : MonoBehaviour
{
    public float Speed = 20;
    public Direction initialDirection = Direction.North;
    public Material[] mats = new Material[2];
    private enum States { Still, Rotate, Solved };
    private States State = States.Still;
    private Direction direction;
    private Direction solutionDirection;
    private bool inRange = false;
    private bool triggered = false;
    private AudioSource audioPlayer;
    // Start is called before the first frame update
    void Start()
    {
        transform.Rotate(Vector3.up,90*(int) initialDirection);
        direction = initialDirection;
        audioPlayer = GetComponent<AudioSource>();
    }

    // Update is called once per frame
    void Update()
    {
        switch (State)
        {
            case States.Still:
                if (inRange && Input.GetButtonDown("Interact"))
                {
                    State = States.Rotate;
                    direction = (Direction)(((int)direction + 1) % 4);
                    audioPlayer.Play();
                }
                break;
            case States.Rotate:
                transform.Rotate(Vector3.up, Speed * Time.deltaTime);
                if (direction != Direction.North && transform.eulerAngles.y >= 90*(int) direction 
                    || (direction == Direction.North && transform.eulerAngles.y < 270))
                {
                    if (triggered && direction == solutionDirection)
                    {
                        State = States.Solved;
                        transform.GetChild(0).GetChild(5).gameObject.GetComponent<MeshRenderer>().material = mats[1];
                    } else
                    {
                        State = States.Still;
                    }
                    audioPlayer.Stop();
                }
                break;
            case States.Solved:
                break;
        }
    }

    public void setSolution(Direction dir)
    {
        solutionDirection = dir;
    }

    public void trigger()
    {
        triggered = true;
        transform.GetChild(0).GetChild(5).gameObject.GetComponent<MeshRenderer>().material = mats[0];
    }

    public bool isSolved()
    {
        return State == States.Solved;
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
}
