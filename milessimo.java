
package cronometroaps;

public class milessimo implements Runnable {
    private static boolean pausado = false;
    private static int i;
    @Override
    public void run() {
        i = Integer.parseInt(telateste.tmilessimo.getText());
        while (!Thread.currentThread().isInterrupted()) {
            if (!pausado){
                telateste.tmilessimo.setText(i + "");
                i++;
                if (i == 999) {
                    i = 0;
                }
            }

            try {
                Thread.sleep(1);
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
    static void retomar() {
        pausado = false;
    }
}
