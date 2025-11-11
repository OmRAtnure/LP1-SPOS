import java.util.concurrent.Semaphore;

class mutex{
    static Semaphore readLock=new Semaphore(1);
    static Semaphore writeLock=new Semaphore(1);
    static int count=0;

    static class Read implements Runnable{
        public void run() {
            try{
                readLock.acquire();
                count++;
                if(count==1) {
                    writeLock.acquire();
                }
                readLock.release();

                System.out.println("Thread"+Thread.currentThread().getName()+" is reading");
                Thread.sleep(1500);
                System.out.println("Thread"+Thread.currentThread().getName()+" has done reading");

                readLock.acquire();
                count--;
                if(count==0) {
                    writeLock.release();
                }
                readLock.release();
            }catch(InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        
    }

    static class Write implements Runnable{
        public void run() {
            try{
                writeLock.acquire();

                System.out.println("Thread "+Thread.currentThread().getName()+" is wriiting");
                Thread.sleep(2000);
                System.out.println("Thread "+Thread.currentThread().getName()+" has completed writing");

                writeLock.release();
            }catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String args[]) {
        Read read=new Read();
        Write write=new Write();

        Thread t1=new Thread(read);

        Thread t2=new Thread(read);

        Thread t3 =new Thread(write);

        Thread t4=new Thread(write);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}