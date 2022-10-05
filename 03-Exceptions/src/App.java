public class App {
    public static void methode1() throws MonException {
        int i = 1;
        int j = 0;
        if(j == 0){
            throw new MonException("Ne divise pas par zéro ");
        } else {
            i = i / j;
        }
    }

    public static void main(String[] args) {
        try {
            methode1();
        } catch (MonException e){
            System.out.println(e.getMessage());
        }

        System.out.println("Au revoir");

    }
}
