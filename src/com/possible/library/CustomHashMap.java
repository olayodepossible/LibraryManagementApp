package com.possible.library;

    public class CustomHashMap <K, V> {

        private Entry<K,V>[] table; // Arrays that stores the entry data
        private int capacity = 4; // initial Capacity of the hashMap

   /*    Inner Class   */
        public static class Entry<K, V>{
            K key;
            V value;
            Entry<K,V> next;

            public Entry(K key, V value, Entry<K, V>next){ // Constructor
                this.key = key;
                this.value = value;
                this.next = next;
            }
        }

        /* Outter Class Constructor */
        public CustomHashMap(){ // Constructor
            table = new Entry[capacity]; // the array is initialise here
        }

        private int hash(K key){
            return Math.abs(key.hashCode())% capacity;
        }  // Hashing method

        public void put(K newKey, V data){
            if(newKey == null) return;  // does not allow to store Null

            int hash = hash(newKey);   // calculate Hash of key

            Entry<K,V> newEntry = new Entry<K,V>(newKey, data, null);   //create new entry object which is to be added to the bucket

            //  if table location does not contain any entry, store the new entry there
            if(table[hash] == null){
                table[hash] = newEntry;
            }
            else {
                Entry<K, V> previous = null;
                Entry<K, V> current = table[hash];

                while (current != null) { //the loop will stop when we have reach last entry of bucket
                    if (current.key.equals(newKey)) {
                        if (previous == null) { //node has to be insert on first of bucket
                            newEntry.next = current.next;
                            table[hash] = newEntry;
                            return;
                        } else {
                            newEntry.next = current.next;
                            previous.next = newEntry;
                            return;
                        }
                    }
                    previous = current;
                    current = current.next;
                }

                previous.next = newEntry;
            }
        }

        public V get(K key){
            int hash = hash(key);
            if(table[hash] == null){
                return null;
            }
            else{
                Entry<K,V> temp = table[hash];
                while(temp != null){
                    if(temp.key.equals(key)) return temp.value;
                    temp = temp.next; //return value correspond to key
                }
                return null; // if Key is not found
            }
        }

        public boolean remove(K deleteKey){
            int hash  = hash(deleteKey);

            if(table[hash] == null){
                return false;
            }
            else{
                Entry<K,V> previous = null;
                Entry<K,V> current = table[hash];

                while(current != null){ // stop when reach last entry node of the bucket
                    if(current.key.equals(deleteKey)){
                        if(previous == null){  // delete first entry node
                            table[hash] = table[hash].next;
                            return true;
                        }
                        else{
                            previous.next = current.next;
                            return true;
                        }
                    }
                    previous = current;
                    current = current.next;
                }
                return false;
            }
        }

        public void display(){
            for(int i = 0; i < capacity; i++){
                if(table[i] != null){
                    Entry<K, V> entry = table[i];
                    while(entry != null){
                        System.out.println("{"+entry.key+ " ="+ entry.value+"}"+" ");
                        entry = entry.next;
                    }
                }
            }
        }


    }

