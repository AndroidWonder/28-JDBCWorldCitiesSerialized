/*
 * This example uses JDBC to read data from  an external DB or from localhost (which needs ip-address: 10.0.2.2)
 * to an ArrayList of a custom class. The data is passed on an intent object to another Activity.
 * The second Activity reads it from the intent object and writes it to the UI.
 * The custom class must implement the Serializable interface for the data to be placed 
 * in the intent object. The ArrayList class already implements Serializable.
 * There is an Internet permission in the Manifest.
 * If the database is on your localhost, you need to use 10.0.2.2 as your ip-address.
*/

package com.example.jdbcworldcities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    private Thread t = null;
    private ArrayList<City> list;
    private String name, code, district;
    private int population;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<City>();
        t = new Thread(background);
        t.start();
    }

    //JDBC is run on a background thread.
    private Runnable background = new Runnable() {
        public void run() {
            String URL = "jdbc:mysql://frodo.bentley.edu:3306/world";
            String username = "Android";
            String password = "android";

            try { //load driver into VM memory
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (Exception e) {
                Log.e("JDBC", "Did not load driver");
            }


            Statement stmt = null;
            Connection con = null;
            try { //create connection to database
                con = DriverManager.getConnection(
                        URL,
                        username,
                        password);
                stmt = con.createStatement();

                ResultSet result = stmt.executeQuery(
                        "SELECT * FROM City ORDER BY Name LIMIT 30 OFFSET 10;");

                //for each record in City table add City object to ArrayList and add city data to log
                while (result.next()) {
                    name = result.getString("Name");
                    code = result.getString("CountryCode");
                    district = result.getString("District");
                    population = result.getInt("Population");
                    City city = new City(name, code, district, population);
                    list.add(city);
                    Log.e("City", name + " " + code);
                }


                //Create intent to start another Activity and put ArrayList on the intent object
                Intent intent = new Intent(getApplicationContext(), UseData.class);
                intent.putExtra("list", list);
                startActivity(intent);

            } catch (SQLException e) {
                Log.e("City", "SQL Error");
                e.printStackTrace();
            }
            finally {
                try { //close may throw checked exception
                    if (con != null)
                        con.close();
                } catch(SQLException e) {
                    Log.e("JDBC", "close connection failed");
                }
            };
        } //run
    }; //background

}
