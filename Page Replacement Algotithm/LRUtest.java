import java.util.Scanner;
import java.util.Arrays;

class LRUtest{
    public static boolean containElement(int [] arr,int element) {
        for(int i:arr) {
            if(i==element) {
                return true;
            }
        }
        return false;
    }
    public static void main(String [] args) {
        Scanner sc=new Scanner(System.in);

        System.out.print("Enter number of pages in refrence string: ");
        int n=sc.nextInt();
        int []pages=new int [n];
        System.out.println("Enter refrence string: ");
        for(int i=0;i<n;i++) {
            pages[i]=sc.nextInt();
        }

        System.out.println("Enter number of frames");
        int m=sc.nextInt();
        int []frames=new int[m];
        for(int i=0;i<m;i++) {
            frames[i]=-1;
        }

        int pageFault=0;
        int count=0;

        System.out.println("Page\tFrame\tPage Fault");

        for(int i=0;i<n;i++) {
            int current=pages[i];
            if(containElement(frames, current)) {
                System.out.println(current+"\t"+Arrays.toString(frames)+"\tNo");
            }else{
                pageFault++;
                if(count>=m) {
                    int farthest=Integer.MAX_VALUE;
                    int index=-1;
                    for(int j=0;j<m;j++) {
                        int lastUse=-1;
                        for(int k=i-1;k>=0;k--) {
                            if(frames[j]==pages[k]) {
                                lastUse=k;
                                break;
                            }   
                        }

                        if(lastUse==-1) {
                            index=j;
                            break;
                        }

                        if(farthest>lastUse) {
                            farthest=lastUse;
                            index=j;
                        }
                    }
                    frames[index]=current;
                }else{
                    frames[count]=current;
                    count++;
                }
            }

            System.out.println(current+"\t"+Arrays.toString(frames)+"\tYes");
        }  

        System.out.println("Page Faults: "+pageFault);
        System.out.println("Page Faults Ratio: "+((float)pageFault/n)*100);


    }
}