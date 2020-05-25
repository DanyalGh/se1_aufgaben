package servlets;

import classes.Auto;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import javax.json.*;

@WebServlet("/Parkhaus")
public class DemoServlet extends HttpServlet
{
    List<Auto> carsIn = new ArrayList<>();
    List<Auto> carsOut = new ArrayList<>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] requestParamString = request.getQueryString().split("=");
        String command = requestParamString[0];
        String param = requestParamString[1];
        response.setContentType("text/plaintext");
        PrintWriter out = response.getWriter();

        System.out.println(request.getQueryString());

        if (command.equals("cmd")){
            switch(param){
                case "Summe" :
                    out.println( getPersistentSum() + " Euro" );
                    break;

                case "Durchschnitt" :
                    out.println( getAveragePrice() + " Euro | " + getAverageDuration() + " ms");
                    break;

                case "Steuer" :
                    out.println( getTax() + " Euro");
                    break;

                case "chart" :
                    JsonObject root = Json.createObjectBuilder().add("data", Json.createArrayBuilder().add(Json.createObjectBuilder().add("x", carsOutIDArray()).add("y", carsOutDurationArray()).add("type", "bar").add("name", "Duration"))).build();
                    out.println(root.toString());
                    System.out.println(root.toString());
                    break;

                default:
                    System.out.println( "Invalid Command: " + request.getQueryString() );
            }
        }
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        Float sum = getPersistentSum();
        int duration = getTotalDuration();
        int carsLeftCounter = getCarsLeftCounter();
        String body = getBody( request ); System.out.println( body );
        String[] params = body.split(",");
        String event = params[0];

        if(event.equals("enter")) {
            carsIn.add(new Auto(Integer.parseInt(params[1])));
        }

        if(event.equals("occupied")) {
            for (Auto car : carsIn) {
                if (car.getID() == Integer.parseInt(params[1].substring(4, (params[1].length() - 1))));{
                    carsIn.remove(car);
                    break;
                }
            }
        }

        if(event.equals("leave")) {
            getApplication().setAttribute("carsLeftCounter", ++carsLeftCounter );

            for (Auto car : carsIn) {
                if (car.getID() == Integer.parseInt(params[1])){
                    carsIn.remove(car);
                    car.setDuration(Integer.parseInt(params[3]));
                    carsOut.add(car);
                    break;
                }
            }

            String priceString = params[4];
                int euro = 0;
                if(priceString.length() > 2) {
                    euro = Integer.parseInt(priceString.substring(0, priceString.length() - 2));
                }
                int cent = Integer.parseInt(priceString.substring(priceString.length() - 2, priceString.length()));
                float price = Float.parseFloat(euro + "." + cent);

                // strip € in front, parse the number behind
                //float price = Float.parseFloat( priceString.split(" ")[0] );
                duration += Integer.parseInt(params[3]);
                sum += price;
                // store sum persistently in ServletContext
                getApplication().setAttribute("totalDuration", duration);
                getApplication().setAttribute("avgDuration", duration/carsLeftCounter);
                getApplication().setAttribute("sum", sum);
                getApplication().setAttribute("tax", sum*0.19f);
                getApplication().setAttribute("avgPrice", sum/carsLeftCounter);
        }
    }

    private JsonArrayBuilder carsOutIDArray() {
        JsonArrayBuilder tmp = Json.createArrayBuilder();

        for (Auto car : carsOut) {
            tmp.add(car.getID());
        }

        return tmp;
    }

    private JsonArrayBuilder carsOutDurationArray() {
        JsonArrayBuilder tmp = Json.createArrayBuilder();

        for (Auto car : carsOut) {
            tmp.add(car.getDuration());
        }

        return tmp;
    }

    private ServletContext getApplication(){
        return getServletConfig().getServletContext();
    }

    private Float getPersistentSum(){
        Float sum;
        ServletContext application = getApplication();
        sum = (Float)application.getAttribute("sum");
        if ( sum == null ) sum = 0.0f;
        return sum;
    }

    private int getCarsLeftCounter(){
        Integer counter;
        ServletContext application = getApplication();
        counter = (Integer)application.getAttribute("carsLeftCounter");
        if(counter == null) counter = 0;
        return counter;
    }

    private Float getAveragePrice(){
        Float avg;
        ServletContext application = getApplication();
        avg = (Float)application.getAttribute("avgPrice");
        if ( avg == null ) avg = 0.0f;
        return avg;
    }

    private Integer getAverageDuration(){
        Integer avg;
        ServletContext application = getApplication();
        avg = (Integer)application.getAttribute("avgDuration");
        if ( avg == null ) avg = 0;
        return avg;
    }

    private Float getTax(){
        Float tax;
        ServletContext application = getApplication();
        tax = (Float)application.getAttribute("tax");
        if ( tax == null ) tax = 0.0f;
        return tax;
    }

    private Integer getTotalDuration(){
        Integer totalDuration;
        ServletContext application = getApplication();
        totalDuration = (Integer)application.getAttribute("totalDuration");
        if ( totalDuration == null ) totalDuration = 0;
        return totalDuration;
    }


    private static String getBody(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return stringBuilder.toString();
    }
}