package bloomfilter;

public class BloomFilter {

    int [] memoryBits = new int[10];

    static final  int val = 7;
    int hashFunction(String ip) {
        int hash = val;
        for (int i = 0; i < ip.length(); i++) {
            hash = hash * 31 + ip.charAt(i);
        }
        return Math.abs(hash%10);
    }

    public static void main(String args[])
    {

        BloomFilter bloomFilter
                = new BloomFilter();
        bloomFilter.saveKeys("128.45.34.12");
        bloomFilter.saveKeys("128.61.34.12");
        bloomFilter.saveKeys("128.76.34.12");
        bloomFilter.saveKeys("128.44.34.12");

        // will return false
        boolean res = bloomFilter.checkPresenceInBloomFilter("128.44.34.13");
        System.out.println(res);
        System.out.println("maybe the key is present in the bloom filter" + " " + res);


        // will return maybe present (true value)
        res = bloomFilter.checkPresenceInBloomFilter("128.76.34.12");
        System.out.println("maybe the key is present in the bloom filter" + " " + res);

    }

    private void saveKeys(String ip)
    {
        int bitNumber = hashFunction(ip);
        memoryBits[bitNumber] = 1;
    }

    boolean checkPresenceInBloomFilter(String testIP)
    {
        int tesRes = hashFunction(testIP);
        if(memoryBits[tesRes] == 1)
        {
            //means maybe this key is present in bloomfilter
            return true;
        }
        else
            return false; // key not present in bloom filter

    }





}
