/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

/**
 *
 * @author Usuario
 */
public class HashTable {
    private LinkedList<Entry>[] table;
    private int size;

    public HashTable(int capacity) {
        table = new LinkedList[capacity];
        size = 0;
    }

    private int hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash += key.charAt(i) * i;
        }
        return hash % table.length;
    }

    public void put(String key, int value) {
        int index = hash(key);
        if (table[index] == null) {
            table[index] = new LinkedList<Entry>();
        }
       // for (Entry entry : table[index]) {
       //     if (entry.key.equals(key)) {
       //         entry.value = value;
       //         return;
       //     }
        
        //table[index].add(new Entry(key, value));
        size++;
    }

    public Integer get(String key) {
        int index = hash(key);
        if (table[index] != null) {
            //for (Entry entry : table[index]) {
            //    if (entry.key.equals(key)) {
             //       return entry.value;
             //   }
           // }
        }
        return null;
    }

    public void remove(String key) {
        int index = hash(key);
        if (table[index] != null) {
           // for (Entry entry : table[index]) {
               // if (entry.key.equals(key)) {
                //    table[index].delete(entry);
                    size--;
                    return;
                }
            }
        
    

    public boolean containsKey(String key) {
        int index = hash(key);
        if (table[index] != null) {
            //for (Entry entry : table[index]) {
            //    if (entry.key.equals(key)) {
            //        return true;
            //    }
            }
        
        return false;
    }

    public int size() {
        return size;
    }

    private static class Entry {
        private String key;
        private int value;

        public Entry(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
