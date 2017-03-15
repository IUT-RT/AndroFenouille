package com.example.vince.androfenouille;

/**
 * Created by gc500085 on 15/03/17.
 */

public class Undo {
    /*Prend en parametre une liste String Json
     *initialise un tableau json
     *parcours le tableau
     *Ajoute à une liste un objet de type User
     *retourne la liste<User>
     */
    /*public ArrayList<User> newList(String jsonText){
        try {
            ArrayList<User> List = new ArrayList<User>();
            JSONArray jsonArray = new JSONArray(jsonText);
            for (int i=0 ; i<jsonArray.length() ; i++){
                JSONObject j = jsonArray.getJSONObject(i);
                List.add(new User(j.getInt("idEtu"), j.getString("nom"), j.getString("prenom"), j.getString("formation"), j.getInt("annee")));
            }
            return  List;
        } catch (JSONException e) {
            return null;
        }
    }*/
}
