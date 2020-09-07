package Blanchette.bot;


import com.sun.org.apache.xpath.internal.operations.Bool;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ErrorMessages;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.invoke.empty.Empty;

import java.io.IOException;
import java.net.URI;

public class BlanchetteBot
{

    public static void main( String[] args )
    {
        Donnees(args);
    }
    public static void Donnees(String[] args){
        System.out.println( "Bonjour Alex Blanchette!" );

        if(ErreurSpecifique(Integer.parseInt(args[0]),args[1],args[2])){
            Recherche(Integer.parseInt(args[0]),args[1],args[2]);
        }

    }
    public static void Recherche(int exploration,String url,String Repertoire){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element link = doc.select("a").first();
        String absHref = link.attr("abs:href");
        System.out.println(absHref);

    }
    public static boolean ErreurSpecifique(int exploration,String url,String Repertoire){
        if (exploration < 0 && url == null && Repertoire == null ){
            ErreurGeneral();
            return false;
        }
        if (exploration < 0 || exploration > 99) {
            System.out.println( "Le premier paramètre doit être un entier positif plus petit que 100 ou égal à zéro" );
            ErreurGeneral();
            return false;
        }
        if (url == null || UrlFonctionnel(url) == false) {
            System.out.println( "Le Deuxième paramètre doit être une url fonctionnel" );
            ErreurGeneral();
            return false;
        }
        if (Repertoire == null) {
            System.out.println( "Le troisième paramètre doit être une destination valide" );
            ErreurGeneral();
            return false;
        }
        return true;
    }

    public static boolean UrlFonctionnel(String url){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (Exception e) {
            System.out.println("L'url du site ne fonctionne pas");
            return false;
        }
            return true;
    }

    public static void ErreurGeneral(){
        String Newligne=System.getProperty("line.separator");
        System.out.println("Vous devez entrer 3 arguments pour que ça fonctionne!:" + Newligne +
                "- Une profondeur compris entre 0 et 99" + Newligne +
                "- Une url valide du site à explorer (Exemple https://departement-info-cem.github.io/3N5-Prog3/accueil.html)" + Newligne +
                "- Un dossier pour pouvoir sauvegarder les pages exploré ainsi que les autres informations (Exemple ./Temp/)");
    }
}
