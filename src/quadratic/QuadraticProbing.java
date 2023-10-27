/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quadratic;

/**
 *
 * @author MINH TUAN
 */
class HashNode {

    int key;
    int value;

    public HashNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class HashTable {

    private int hsize;
    private int size;
    private HashNode[] buckets;

    public HashTable(int hsize) {
        this.hsize = hsize;
        this.size = 0;
        buckets = new HashNode[this.hsize];
    }

    public int hash(int key) {
        return key % hsize;
    }

    public void insertNode(int key, int value) {
        if (size == hsize) {
            System.out.println("Hash Table is full. Cannot insert new node.");
            return;
        }

        HashNode node = new HashNode(key, value);
        int idx = hash(key);
        int i = idx, h = 1;
        while (buckets[i] != null && buckets[i].key != -1) {
            i = (idx + h * h) % hsize;
            h++;
        }
        buckets[i] = node;
        if (buckets[i] == null || buckets[i].key == -1) {
            size++;
        }
    }

    public int deleteNode(int key) {
        int idx = hash(key);
        int i = idx, h = 1;
        do {
            if (buckets[i] == null) {
                return -1;
            }
            if (buckets[i].key == key) {
                int val = buckets[i].value;
                buckets[i] = null;
                size--;
                return val;
            }
            i = (idx + h * h) % hsize;
            h++;
        } while (i != idx && h <= hsize); 
        return -1;
    }

    public int get(int key, int value) {
        int idx = hash(key);
        int i = idx, h = 1;
        do {
            if (buckets[i] == null) {
                return -1;
            }
            if (buckets[i].key == key) {
                return buckets[i].value;
            }
            i = (idx + h * h) % hsize;
            h++;
        } while (i != idx);
        return -1;
    }

    public int sizeofMap() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;

    }

    public void display() {
        System.out.println("\nHash Table: ");
        for (int i = 0; i < hsize; i++) {
            if (buckets[i] != null) {
                System.out.println(i + " : " + buckets[i].key);
            }
        }
    }
}

public class QuadraticProbing {

    public static void main(String[] args) {
        HashTable h = new HashTable(10);

        h.insertNode(2, 2);
        h.insertNode(12, 2);
        h.insertNode(22, 2);
        h.insertNode(32, 2);

        h.display();

        System.out.println("Search 22: " + h.get(22, 2));
        System.out.println("\nSize of Map: " + h.sizeofMap());

        System.out.println("\nDelete Node 18: " + h.deleteNode(22));

        System.out.println("\nSize of Map: " + h.sizeofMap());

        System.out.println("\nIs Map Empty? " + h.isEmpty());

    }
}
