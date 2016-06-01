package DataBase;

import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Thibault on 29/05/2016.
 */

// DownloadXML AsyncTask

/*
 * Telecharge de facon asynchrone les données depuis un server WFS (geo DataBase)
 */
public class DownloadXML extends AsyncTask<String, Void, Document> {

    Document output;



    public AsyncReponse delegate = null;

    public DownloadXML(AsyncReponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        output=null;

    }

    @Override
    protected Document doInBackground(String... Url) {
        try {
            URL url = new URL(Url[0]);
            DocumentBuilderFactory dbf = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            // Download the XML file
            Document doc = db.parse(new InputSource(url.openStream()));
            doc.getDocumentElement().normalize();

            output = doc;

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return output;

    }

    @Override
    protected void onPostExecute(Document result)
    {

        delegate.processFinish(result);

    }

}
