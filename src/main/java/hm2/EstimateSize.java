public class EstimateSize {

    public static int N = 10_000_000;

    public static long estimateInteger() throws InterruptedException {
        System.gc();
        Thread.sleep(10);

        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = i;
        }

        long total = Runtime.getRuntime().totalMemory();
        long free = Runtime.getRuntime().freeMemory();
        long used = total - free;

        return used;
    }

    public static long estimateRefrence() throws InterruptedException {
        System.gc();
        Thread.sleep(10);

        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++) {
            a[i] = null;
        }

        long total = Runtime.getRuntime().totalMemory();
        long free = Runtime.getRuntime().freeMemory();
        long used = total - free;

        return used;
    }

    public static long estimateObject() throws InterruptedException {
        System.gc();
        Thread.sleep(10);

        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++) {
            a[i] = Integer.valueOf(i);
        }

        long total = Runtime.getRuntime().totalMemory();
        long free = Runtime.getRuntime().freeMemory();
        long used = total - free;

        return used;
    }

    public static long estimateString() throws InterruptedException {
        System.gc();
        Thread.sleep(10);

        String[] a = new String[N];
        for (int i = 0; i < N; i++) {
            a[i] = String.valueOf(i);
        }

        long total = Runtime.getRuntime().totalMemory();
        long free = Runtime.getRuntime().freeMemory();
        long used = total - free;

        return used;
    }

    public static void main(String[] args) throws InterruptedException {


        long integerSize = (long) estimateInteger();
        long referenceSize = (long) estimateRefrence();
        long objectSize = (long) estimateObject();
        long stringSize = (long) estimateString();

        System.out.println("Integer Size: " + integerSize / Float.valueOf(N) + " Bytes");
        System.out.println("Reference Size: " + referenceSize / Float.valueOf(N) + " Bytes");
        System.out.println("Object Size: " + objectSize / Float.valueOf(N) + " Bytes");
        System.out.println("String Size: " + stringSize / Float.valueOf(N) + " Bytes");
    }
}