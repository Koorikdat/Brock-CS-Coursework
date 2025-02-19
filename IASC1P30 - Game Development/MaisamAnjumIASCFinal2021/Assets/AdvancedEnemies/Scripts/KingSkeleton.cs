using NUnit.Framework.Constraints;
using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class KingSkeleton : Skeleton
{
    public int numLives = 5;

    protected const int Spawning = 6;
    protected int numSpawns = 3;
    protected GameObject skeleton;
    // Start is called before the first frame update
    protected override void Start()
    {
        base.Start();
        skeleton = Resources.Load<GameObject>("prefabs/Skeleton");
    }

    // Update is called once per frame
    protected override void Update()
    {
        switch (State)
        {
            case Spawning:
                Vector3 pos = Vector3.MoveTowards(player.transform.position, transform.position, 5);
                pos.y = 0;
                GameObject newSkeleton = Instantiate(skeleton, pos, Quaternion.identity);
                newSkeleton.transform.forward = player.transform.position - pos;
                numSpawns--;
                gotoState(Chasing);
                break;
            default:
                base.Update();
                break;
        }
    }

    protected override void gotoState(int newState)
    {
        switch (newState)
        {
            case Chasing:
                if (State != Spawning && numSpawns > 0)
                {
                    State = Spawning;
                } else
                {
                    base.gotoState(newState);
                }
                break;
            default:
                base.gotoState(newState);
                break;
        }
    }

    public override void takeHit()
    {
        if (State != Dead)
        {
            numLives--;
            if (numLives == 0)
            {
                base.takeHit();
            }
            else
            {
                audioSource.clip = clips[1];
                audioSource.Play();
            }
        }
    }
}
