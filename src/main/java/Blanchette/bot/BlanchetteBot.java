package Blanchette.bot;


import com.sun.org.apache.xpath.internal.operations.Bool;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ErrorMessages;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.jsoup.helper.Validate;
import sun.awt.image.ImageWatched;
import sun.invoke.empty.Empty;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class BlanchetteBot
{

    public static void main( String[] args )
    {

         Donnees(args);
        //UrlMalforme(args);
    }


    public static void Donnees(String[] args){
        System.out.println( "Bonjour Alex Blanchette!" );

        if(ErreurSpecifique(Integer.parseInt(args[0]),args[1],args[2])){
            String Newligne=System.getProperty("line.separator");
            System.out.println(Newligne +"Tout semble en ordre, commençons la recherche! " );
            Dossier(args[2]);
            Recherche(Integer.parseInt(args[0]),args[1]);
        }

    }


    public static void Recherche(int exploration,String url){

        Set<Web> Listedesites = new HashSet<Web>();
        TestdeRecherche(Listedesites,url,exploration);


        for (Web Site:Listedesites )
             {
                if(Site.toString() != ""){
                     Document doc = null;
                     try {
                         if (isURL(Site.WebAdress)) {
                             doc = Jsoup.connect(Site.toString()).get();
                             System.out.println("Exploration de la page >> " + Site);
                             //Elements newsHeadlines = doc.select("html");
                             //System.out.println(newsHeadlines);
                         }
                         else
                             System.out.println("L'url " + Site + " ne fonctionne pas");


                     } catch (IOException e) {

                     }
                     catch (NullPointerException f)
                     {

                     }






                 }


        }
        System.out.println("Nombre de pages explorées : " + Listedesites.stream().count() );
    }







    public static Set<Web> TestdeRecherche(Set<Web> Listedesites,String url, int exploration){

        Set<Web> Listedesites2 = new HashSet<Web>();

        try {
            Document doc = null;

            if (isURL(url)) {
                doc = Jsoup.connect(url).get();
                }
            Elements links = doc.select("a[href]");

            for (Web Site: Listedesites)
            {
                if (Site.toString() != "" ) {
                    Listedesites2.add(Site);
                }
            }
            if (exploration > 0){
                for (Element link : links) {
                    if (!Listedesites.contains(link) && link.toString() != "") {
                        Listedesites.add(new Web(link.attr("abs:href")));
                    }
                    for (Web Site: Listedesites)
                    {
                        if (Site.toString() != "") {
                                Listedesites2.add(Site);
                        }
                    }
                    if (exploration > 1){
                        for (int i = 1; i < exploration; i++) {
                            if (!Listedesites.contains(link) && link.toString() != null) {

                                for (Web Site: Listedesites)
                                {
                                    if (Site.toString() != "") {
                                        Listedesites.add(new Web(link.attr("abs:href")));
                                        Listedesites2.add(Site);
                                    }
                                }
                                TestdeRecherche(Listedesites,link.attr("abs:href"),i);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {

        }


        return Listedesites2;
    }

    public static void UrlMalforme(String[] args){
        try {
            String url= args[1];
            HashMap<String, String> h = new HashMap<>();
            Document doc = Jsoup.connect(url).get();


            //  Links in page
            List url_array = new ArrayList();
            int i=0;
            url_array.add(url);

            while((i<=h.size())){
                try{
                    url = url_array.get(i).toString();
                    doc = Jsoup.connect(url).get();
                    Elements links = doc.select("a[href]");
                    Elements links2 = doc.select("body");

                    for (Element link : links) {
                        try {
                            URL url2 = new URL(link.toString());
                            URLConnection conn = url2.openConnection();
                            conn.connect();
                        } catch (MalformedURLException e) {
                            System.out.println(e);
                            // the URL is not in a valid form
                        } catch (IOException e) {
                            e.printStackTrace();
                            // the connection couldn't be established
                        }
                       String res= h.putIfAbsent(link.attr("href"), link.text());
                        for (Element link2 : links2){
                            //if (res==null && Jsoup.isValid(link2.empty().text(), Whitelist.none())){
                                url_array.add(link.attr("href"));
                                System.out.println("\nURL: " + link.attr("href"));
                           // }
                        }
                    }
                }catch(Exception e){

                }

                i++;

            }
        } catch (Exception e) {

        }



    }

    public static boolean isURL(String url) {
        try {
            (new java.net.URL(url)).openStream().close();
            return true;
        }
        catch (Exception ex) {
            return false;
        }
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
        if (url == null || !UrlFonctionnel(url)) {
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
            System.out.println("L'url du site ne fonctionne pas " + url);
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

    public static void Dossier(String Repertoire){
        File Dossier = new File(Repertoire);

        if (Dossier.exists()) {
            Dossier.delete();
        }
        Dossier.mkdir();
    }
}
