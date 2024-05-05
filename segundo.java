
package cronometroaps;


public class segundo implements Runnable {
    private static boolean pausado = false;
    private static int i;
    @Override
    public void run() {
        i = Integer.parseInt(telateste.tsegundo.getText());
        while (!Thread.currentThread().isInterrupted()){
                if(!pausado){
                    telateste.tsegundo.setText(i + "");
                    i++;
                    if (i == 59) {
                        i = 0;
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }
    static void zerar(){
       i = 0;
    }
    static void pausar(){
        pausado = true;
    }
    static void retomar(){
        pausado = false;
    }
}
