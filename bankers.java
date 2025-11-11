import java.util.Scanner;

class bankers{

    // public static boolean isSafe(int n,int m,int [] available,int [][]allocation,int [][]max) {
    //     int [][]need=new int[n][m];
    //     int [] work=new int[m];
    //     int [] finished=new int [n];

    //     for(int i=0;i<n;i++) {
    //         for(int j=0;j<m;j++) {
    //             need[i][j]=max[i][j]-allocation[i][j];
    //         }
    //     }

    //     for(int i=0;i<m;i++) {
    //         work[i]=available[i];
    //     }

    //     int count=0;
    //     String safeSeq="";

    //     while(count<n) {
    //         boolean found=false;
    //         for(int i=0;i<n;i++) {
    //             if(finished[i]==0) {
    //                 int j;
    //                 for(j=0;j<m;j++) {
    //                     if(need[i][j]>work[j]) {
    //                         break;
    //                     }
    //                 }

    //                 if(j==m) {
    //                     for(int k=0;k<m;k++) {
    //                         work[k]=work[k]+allocation[i][k];
    //                     }
    //                     safeSeq=safeSeq+"P"+i+"";
    //                     finished[i]=1;
    //                     found=true;
    //                     count++;
    //                 }
    //             }
    //         }

    //         if(!found) {
    //             System.out.println("Process is not safe");
    //             return false;
    //         }
    //     }

    //     System.out.println("System is safe");
    //     System.out.println("safe seq: "+safeSeq);
    //     return true;
    // }


    public static boolean isSafe(int n,int m,int [][]allocation,int [][]max,int []available) {
        int [][]need=new int[n][m];
        int []work=new int[m];
        int []finished=new int[n];

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                need[i][j]=max[i][j]-allocation[i][j];
            }
        }

        for(int i=0;i<m;i++) {
            work[i]=available[i];
        }

        int count=0;
        String safeSeq="";

        while(count<n) {
            boolean found=false;

            for(int i=0;i<n;i++) {
                if(finished[i]==0) {
                    int j;
                    for(j=0;j<m;j++) {
                        if(need[i][j]>work[j]) {
                            break;
                        }
                    }
                    if(j==m) {
                        for(int k=0;k<m;k++) {
                            work[k]=work[k]+allocation[i][k];
                        }
                        safeSeq=safeSeq+"P"+(i+1)+" ";
                        found=true;
                        count++;
                        finished[i]=1;
                    }
                }
            }

            if(!found) {
                System.out.println("System is not safe");
                return false;
            }
        }

        System.out.println("System is safe");
        System.out.println("Safe Sequence: "+safeSeq);
        return true;
    }
    public static void main(String [] args) {
        Scanner sc=new Scanner(System.in);

        System.out.print("Enter number of Process: ");
        int n=sc.nextInt();

        System.out.print("Enter number of resources");
        int m=sc.nextInt();

        int [][] allocation=new int[n][m];
        int [][] max=new int[n][m];
        int []available=new int [m];

        System.out.println("Enter allocation matrix");
        for(int i=0;i<n;i++) {
            System.out.print("Allocation for process P"+(i+1)+": ");
            for(int j=0;j<m;j++) {
                allocation[i][j]=sc.nextInt();
            }
        }

        System.out.println("Enter max need matrix");
        for(int i=0;i<n;i++) {
            System.out.print("max need for process P"+(i+1)+": ");
            for(int j=0;j<m;j++) {
                max[i][j]=sc.nextInt();
            }
        }
        
        System.out.println("Enter available resources");
        for(int i=0;i<m;i++) {
            available[i]=sc.nextInt();
        }

        isSafe(n,m,allocation,max,available);
        
    }
}