package org.example.Services;

import org.example.Entities.News;
import org.example.Special.PostgreSQLSpace;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsService {
    
    private PostgreSQLSpace postgres;

    public NewsService(){
        postgres = new PostgreSQLSpace();
    }
    
    public List<News> getListNews(){
        return FormListNews();
    }
    
    private List<News> FormListNews(){
        List<News> listNews = new ArrayList<>();
        String SELECT_NEWS = "SELECT * FROM news;";

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(SELECT_NEWS);

            while (resultSet.next()){
                News news = new News();

                String title = resultSet.getString("title");
                List<String> tags = CreateListTags(resultSet.getInt("id_news"));
                String descr = resultSet.getString("description");
                String date = resultSet.getString("date_new");

                news.setDate(date);
                news.setDescription(descr);
                news.setTagsList(tags);
                news.setTitle(title);
                listNews.add(news);

            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        return listNews;
    }

    private List<String> CreateListTags(Integer id_new){
        List<String> listTags = new ArrayList<>();

        String SELECT_TAGS = String.format("SELECT tag.name_tag FROM " +
                "tag_news JOIN tag " +
                "ON tag_news.id_tag = tag.id_tag " +
                "WHERE tag_news.id_new = %d;", id_new);

        try(Connection conn = DriverManager.getConnection(
                postgres.getUrlAdress(), postgres.getPostgresUser(), postgres.getPasssword());
            Statement statement = conn.createStatement()){

            ResultSet resultSet = statement.executeQuery(SELECT_TAGS);


            while (resultSet.next()){

                listTags.add(resultSet.getString("name_tag"));

            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        return listTags;
    }


}
