package Blanchette.bot;


public class BlanchetteBot
{
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );
        Erreur();
        Recherche(Integer.parseInt(args[0]),args[1],args[2]);
    }

    public static void Recherche(int exploration,String url,String Repertoire){

    }

    public static void Erreur(){
        String Newligne=System.getProperty("line.separator");
        System.out.println( "Bonjour Alex Blanchette!" + Newligne +
                "Vous devez entrer 3 arguments pour que ça fonctionne!:" + Newligne +
                "- Une profondeur compris entre 1 et 99" + Newligne +
                "- Une url valide du site à explorer (Exemple https://departement-info-cem.github.io/3N5-Prog3/accueil.html)" + Newligne +
                "- Un dossier pour pouvoir sauvegarder les pages exploré ainsi que les autres informations (Exemple ./Temp/)");
    }
}
