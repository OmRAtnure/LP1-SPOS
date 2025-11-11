import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

class FIFO{
    public static void main(String [] args) {
        Scanner sc=new Scanner(System.in);

        System.out.println("Enter number of pages in refrence string");
        int n=sc.nextInt();

        int []pages=new int[n];
        System.out.println("Enter page refrence string:");
        for(int i=0;i<n;i++) {
            pages[i]=sc.nextInt();
        }

        System.out.println("Enter number of frames");
        int m=sc.nextInt();

        Queue<Integer> frames=new LinkedList<>();
        int pageFaults=0;
        int pageHit=0; 

        System.out.println("Page\tFrames\tPage Fault");
        for(int i=0;i<n;i++) {
            int current=pages[i];
            if(frames.contains(current)) {
                System.out.println(current+"\t" + frames +"\tNo");
                pageHit++;
            }else{
                if(frames.size()==m) {
                    frames.poll();
                }
                pageFaults++;
                frames.add(current);
                System.out.println(current+"\t"+frames+"\tYes");
            }
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);
        System.out.println("Page Fault Ratio: "+((float)pageFaults/n) *100);

    }
}