using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;


public class ArrowPickUp : MonoBehaviour
{
    private AudioSource audioSource;
    private bool available = true;
    public static int numarrows = 0;


    [SerializeField] private AudioClip m_JumpSound;           // sound for when we pickup arrow


    // Start is called before the first frame update
    void Start()
    {
        audioSource = GetComponent<AudioSource>();
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.tag == "Player" && available)
        {
            other.GetComponent<Shooting>().receiveArrows();
            audioSource.Play();
            available = false;
            Destroy(gameObject, 3.6f);
        }
    }
}
