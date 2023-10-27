// Our own HashNode class

class HashNode {

    int key;
    int value;

    public HashNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

// Our own Hashmap class
class HashMap {
    // hash element array

    int capacity;
    int size;
    HashNode[] arr;
    // dummy node
    HashNode dummy;

    public HashMap() {
        this.capacity = 10;
        this.size = 0;
        this.arr = new HashNode[this.capacity];
        // initialize with dummy node
        this.dummy = new HashNode(-1, -1);
    }

    // This implements hash function to find index for a key
    public int hashCode(int key) {
        return key % this.capacity;
    }

    // Function to add key value pair
    public void insertNode(int key, int value) {
        HashNode temp = new HashNode(key, value);
        // Apply hash function to find index for given key
        int hashIndex = hashCode(key);
        // find next free space
        while (this.arr[hashIndex] != null && this.arr[hashIndex].key != key && this.arr[hashIndex].key != -1) {
            hashIndex++;
            hashIndex %= this.capacity;
        }
        // if new node to be inserted, increase the current size
        if (this.arr[hashIndex] == null || this.arr[hashIndex].key == -1) {
            this.size++;
        }
        this.arr[hashIndex] = temp;
    }

    // Function to delete a key value pair
    public int deleteNode(int key) {
        // Apply hash function to find index for given key
        int hashIndex = hashCode(key);
        // finding the node with given key
        while (this.arr[hashIndex] != null) {
            // if node found
            if (this.arr[hashIndex].key == key) {
                HashNode temp = this.arr[hashIndex];
                // Insert dummy node here for further use
                this.arr[hashIndex] = this.dummy;
                // Reduce size
                this.size--;
                return temp.value;
            }
            hashIndex++;
            hashIndex %= this.capacity;
        }
        // If not found return -1
        return -1;
    }

    // Function to search the value for a given key
    public int get(int key) {
        // Apply hash function to find index for given key
        int hashIndex = hashCode(key);
        int counter = 0;
        // finding the node with given key
        while (this.arr[hashIndex] != null) {
            // If counter is greater than capacity to avoid infinite loop
            if (counter > this.capacity) {
                return -1;
            }
            // if node found return its value
            if (this.arr[hashIndex].key == key) {
                return this.arr[hashIndex].value;
            }
            hashIndex++;
            hashIndex %= this.capacity;
            counter++;
        }
        // If not found return 0
        return 0;
    }

    // Return current size
    public int sizeofMap() {
        return this.size;
    }

    // Return true if size is 0
    public boolean isEmpty() {
        return this.size == 0;
    }

    // Function to display the stored key value pairs
    public void display() {
        for (int i = 0; i < this.capacity; i++) {
            if (this.arr[i] != null && this.arr[i].key != -1) {
                System.out.println("key = " + this.arr[i].key + " value = " + this.arr[i].value);
            }
        }
    }
}

public class Main {

    public static void main(String[] args) {
        HashMap h = new HashMap();
        h.insertNode(1, 1);
        h.insertNode(2, 2);
        h.insertNode(22, 2);
        h.display();

    }
}
