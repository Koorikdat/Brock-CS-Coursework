using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Shooting : MonoBehaviour
{
    public int numArrows = 10;
    public AudioClip clip;    
    
    private GameObject arrow;
    private AudioSource audioSource;
    // Start is called before the first frame update
    void Start()
    {
        arrow = Resources.Load<GameObject>("prefabs/Arrow");
        audioSource = GetComponent<AudioSource>();
    }

    // Update is called once per frame
    void Update()
    {
        if (Input.GetButtonDown("Fire1") && numArrows > 0)
        {
            Quaternion quad = transform.rotation;
            //quad *= Quaternion.Euler(new Vector3(90, 0, 0));
            Instantiate(arrow, transform.position + transform.forward + 0.5f * transform.up, quad);
            audioSource.clip = clip;
            audioSource.Play();
            numArrows--;
        }
    }

    public void receiveArrows()
    {
        numArrows += 10;
    }

    private void OnGUI()
    {
        GUI.Label(new Rect(10, 10, 100, 20), "Arrows : " + numArrows.ToString());
    }
}
