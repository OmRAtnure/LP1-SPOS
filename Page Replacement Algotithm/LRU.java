import java.util.Arrays;
import java.util.Scanner;

class LRU{
    public static boolean containElement(int [] arr,int element) {
        for(int i:arr) {
            if(i==element) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        System.out.print("Enter number of pages in refrence string: ");
        int n=sc.nextInt();
        System.out.println("Enter refrence string:");
        int []pages=new int[n];

        for(int i=0;i<n;i++) {
            pages[i]=sc.nextInt();
        }
        System.out.print("Enter number of Frames: ");
        int m=sc.nextInt();

        int [] frames=new int[m];
        for(int i=0;i<m;i++) {
            frames[i]=-1;
        }

        int pageFault=0;
        int count=0;
        System.out.println("Page\tFrame\tPage Fault");
        for(int i=0;i<n;i++) {
            int current=pages[i];
            if(containElement(frames,current)) {
                System.out.println(current+"\t"+Arrays.toString(frames)+"\tNo");
            } else{
                pageFault++;
                if(count>=m) {
                    int furthest=Integer.MAX_VALUE;
                    int index=-1;
                    for(int j=0;j<m;j++) {
                        int lastuse=-1;
                        for(int k=i;k>=0;k--) {
                            if(pages[k]==frames[j]) {
                                lastuse=k;
                                break;
                            }
                        }   
                        if(lastuse==-1) {
                            index=j;
                            break;
                        }
                        if(furthest>lastuse) {
                            furthest=lastuse;
                            index=j;
                        }
                    }

                    frames[index]=current;
                }else{
                    frames[count]=current;
                    count++;
                }
                System.out.println(current+"\t"+Arrays.toString(frames)+"\tYes");
                
            }

        }

        System.out.println("Page Fault: "+pageFault);
        System.out.println("Page Fault Ratio: "+((float)pageFault/n)*100);
    }
}