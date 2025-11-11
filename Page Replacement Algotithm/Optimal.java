import java.util.Scanner;
import java.util.Arrays;

class Optimal{
    public static boolean containElemetn(int []arr,int element) {
        for(int i:arr) {
            if(i==element) {
                return true;
            }
        }
        return false;
    }


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

        int [] frames=new int [m];
        for(int i=0;i<m;i++) {
            frames[i]=-1;
        }
        int pageFaults=0;
        int pageHit=0; 
        int count=0;

        System.out.println("Page\tFrames\tPage Fault");
        for(int i=0;i<n;i++) {
            int current=pages[i];
            if(containElemetn(frames,current)) {
                System.out.println(current + "\t" + Arrays.toString(frames) + "\tNo");
                pageHit++;
            }else{
                if(count>=m) {
                   int farthest=Integer.MIN_VALUE;
                   int index=-1;
                   for(int j=0;j<m;j++) {
                        int nextuse=-1;
                        for(int k=i+1;k<n;k++) {
                            if(pages[k]==frames[j]) {
                                nextuse=k;
                                break;
                            }
                        }

                        if(nextuse==-1) {
                            index=j;
                            break;
                        }
                        if(nextuse>farthest) {
                            farthest=nextuse;
                            index=j;
                        }
                   }
                   frames[index]=current; 

                }else{
                    frames[count]=current;
                    count++;
                }
                pageFaults++;
                
                System.out.println(current + "\t" + Arrays.toString(frames) + "\tYes");
            }
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);
        System.out.println("Page Fault Ratio: "+((float)pageFaults/n) *100);

    }
}