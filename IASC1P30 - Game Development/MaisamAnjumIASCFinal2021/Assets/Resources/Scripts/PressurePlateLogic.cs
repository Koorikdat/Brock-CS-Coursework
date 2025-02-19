using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PressurePlateLogic : MonoBehaviour
{

    [SerializeField] GameObject Target;


    bool activate = false;


    void Activated(Collider col)
    {
        if (!activate)
        {
          //  Target.transform += new Vector3(0, 5, 0);
        }
    }
}