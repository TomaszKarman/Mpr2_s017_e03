<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="domain.model.EnumDictionary" %>
<%@page import="EnumDictionary" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Formularz waluty</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div>
            <p>Cześć</p>
            <form  enctype="text/plain">
                <div>
                    Wybierz walutę</br>
                    <select name="currency">
                        <%
                        try {
                           RepositoryCatalog rp = new RepositoryCatalog(App.CONNECTION_STRING);
                           List<EnumDictionary> currency = rp.Dictionaries().withDictionaryName("Currency");
                           for(EnumDictionary ed:currency){
                        %><option value="<%= ed.getStringKey()%>"
                                ><%= ed.getValue()%></option>
                        <%
                           }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        %>

                    </select> </br>
                    Podaj wartosc</br>
                    <input name="value" /></br>
                </div>
                <input type="submit" formaction="walletServlet" value="Wyslij formularz" />
                <input type="submit"  formaction="AddAccountServlet" value="Dodaj konto"/>
            </form>              


        </div>
    </body>
</html>
