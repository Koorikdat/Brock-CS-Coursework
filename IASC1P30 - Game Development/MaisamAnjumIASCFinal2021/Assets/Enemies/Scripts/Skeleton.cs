using System.Collections;
using System.Collections.Generic;
using System.Runtime.ConstrainedExecution;
using System.Xml.Serialization;
using UnityEditorInternal;
using UnityEngine;

public class Skeleton : MonoBehaviour, Hitable
{
    public Direction initialDirection = Direction.North;
    public enum InitState { Idle, Walking };
    public InitState initialState = InitState.Idle;
    public float fieldOfView = 50;
    public float rotSpeed = 20;
    public float idlePercent = 0.001f;
    public float turnPercent = 0.001f;
    public float walkPercent = 0.001f;
    public AudioClip[] clips = new AudioClip[2];

    protected const int Idle = 0;
    protected const int Walking = 1;
    protected const int Turning = 2;
    protected const int Chasing = 3;
    protected const int Attack = 4;
    protected const int Dead = 5;
    protected int State;
    protected Animator animator;
    protected AudioSource audioSource;
    protected GameObject player; 

    private int clockwise = 1;
    private float destroyTime;
    // Start is called before the first frame update
    protected virtual void Start()
    {
        transform.Rotate(Vector3.up, 90 * (int)initialDirection);
        animator = GetComponent<Animator>();
        audioSource = GetComponent<AudioSource>();
        player = GameObject.FindGameObjectWithTag("Player");
        State = (int)initialState;
        gotoState(State);
    }

    // Update is called once per frame
    protected virtual void Update()
    {
        switch (State)
        {
            case Idle:
                if (seePlayer())
                {
                    gotoState(Chasing);
                }
                if (Random.Range(0.0f, 1.0f) <= idlePercent)
                {
                    gotoState(Turning);
                }
                break;
            case Turning:
                if (seePlayer())
                {
                    gotoState(Chasing);
                }
                transform.Rotate(Vector3.up, rotSpeed * clockwise * Time.deltaTime);
                if (Random.Range(0.0f, 1.0f) <= turnPercent)
                {
                    gotoState(Walking);
                }
                break;
            case Walking:
                if (seePlayer())
                {
                    gotoState(Chasing);
                }
                if (Random.Range(0.0f, 1.0f) <= walkPercent)
                {
                    gotoState(Idle);
                }
                break;
            case Chasing:
                Vector3 direction = player.transform.position - transform.position;
                direction.y = 0;
                Vector3.Normalize(direction);
                transform.forward = direction;
                break;
            case Attack:
                break;
            case Dead:
                destroyTime -= Time.deltaTime;
                if (destroyTime < 0)
                {
                    Destroy(gameObject);
                }
                break;
        }
    }

    protected virtual void gotoState(int newState)
    {
        State = newState;
        switch (State)
        {
            case Idle:
                animator.SetBool("Walk", false);
                break;
            case Turning:
                animator.SetBool("Walk", false);
                clockwise = Random.Range(-1, 1);
                if (clockwise == 0)
                {
                    clockwise = 1;
                }
                break;
            case Walking:
                animator.SetBool("Walk", true);
                break;
            case Chasing:
                audioSource.clip = clips[0];
                audioSource.Play();
                animator.SetBool("Walk", true);
                break;
            case Attack:
                animator.SetBool("Attack", true);
                break;
            case Dead:
                audioSource.clip = clips[1];
                audioSource.Play();
                animator.SetBool("Dead", true);
                destroyTime = 5;
                break;
        }
    }

    public virtual void takeHit()
    {
        if (State != Dead)
        {
            gotoState(Dead);
        }
    }

    private void kill()
    {
        Destroy(player);
    }

    private bool seePlayer()
    {
        Vector3 direction = (player.transform.position - transform.position).normalized;
        float angle = Vector3.Angle(transform.forward,direction);
        RaycastHit hitInfo;
        return angle < fieldOfView && Physics.Raycast(transform.position, direction, out hitInfo) && hitInfo.collider.tag == "Player";
    }

    private void OnCollisionEnter(Collision collision)
    {
        if (State != Dead)
        {
            if (collision.collider.tag == "Player")
            {
                gotoState(Attack);
            }
            else
            {
                transform.Translate(-0.1f * transform.forward);
                gotoState(Turning);
            }
        }
    }
}
