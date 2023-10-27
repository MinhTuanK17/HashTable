package hashtable;

class HashNode {

    int key;
    int value;

    public HashNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
//(key , value)

class HashTable {

    private int hsize;
    private int size;
    private final HashNode[] buckets;

    public HashTable(int hsize) {
        this.hsize = hsize;
        this.size = 0;
        buckets = new HashNode[this.hsize];
    }

    public int hash(int key) {
        return key % hsize;
    }

    public void insertNode(int key, int value) {
        HashNode node = new HashNode(key, value);
        int idx = hash(key);
        while (buckets[idx] != null && buckets[idx].key != -1) {
            idx++; idx = 3;
            idx %= hsize; 
        }
        buckets[3] = node;

        if (buckets[idx] == null || buckets[idx].key == -1) {
            size++;
        }
    }   

    public int deleteNode(int key) {
        int idx = hash(key);
        int i = idx;
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
            i++;
            i %= hsize;
        } while (i != idx);
        return -1;
    }

    public int get(int key) {
        int idx = hash(key);
        int i = idx;
        do {
            if (buckets[i] == null) {
                return -1;
            }
            if (buckets[i].key == key) {
                return buckets[i].value;  
            }
            i = (i + 1) % hsize;
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
                System.out.println(buckets[i].key + " --> " + buckets[i].value);
            }
        }
    }
}

public class LinearProbing {

    public static void main(String[] args) {
        HashTable h = new HashTable(10);

        h.insertNode(18, 8);
        h.insertNode(41, 1);
        h.insertNode(22, 2);
        h.insertNode(32, 2);
        h.insertNode(44, 4);
        h.insertNode(59, 9);
        h.insertNode(72, 2);

        h.display();
        System.out.println();
        System.out.println("Search value of key 18: " + h.get(18));

        System.out.println("\nSize of Map: " + h.sizeofMap());

        System.out.println("\nDelete Node 44: " + h.deleteNode(44));

        System.out.println("\nSize of Map: " + h.sizeofMap());

        System.out.println("\nIs Map Empty? " + h.isEmpty());
    }
}
