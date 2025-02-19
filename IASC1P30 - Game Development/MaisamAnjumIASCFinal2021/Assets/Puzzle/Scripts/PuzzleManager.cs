using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PuzzleManager : MonoBehaviour
{
    public Statue[] statues;
    public Direction[] solution;
    public GameObject triggeredObject;

    private int currentStatue = 0;
    private bool isSolved = false;
    void Start()
    {
        statues[0].trigger();
        for(int i = 0; i < statues.Length; i++)
        {
            statues[i].setSolution(solution[i]);
        }
    }

    // Update is called once per frame
    void Update()
    {
        if (!isSolved && statues[currentStatue].isSolved())
        {
            if (currentStatue == statues.Length-1)
            {
                triggeredObject.GetComponent<TriggeredObject>().doAction1();
                isSolved = true;
                GetComponent<AudioSource>().Play();
            } else
            {
                currentStatue++;
                statues[currentStatue].trigger();
            }
        }
    }
}
